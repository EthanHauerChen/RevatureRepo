package org.leetrepository.service;

import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Solution;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SolutionService implements ServiceInterface<SolutionEntity, Solution> {
    SolutionDAO solutionDAO;// = new SolutionDAO();

    ProblemService problemService;// = new ProblemService();

    //use for tests ONLY, DO NOT USE IN NORMALS SITUATION
    public SolutionService(SolutionDAO solutionDAO, ProblemService problemService) {
        this.solutionDAO = solutionDAO;
        this.problemService = problemService;
    }

    @Override
    public Integer createEntity(SolutionEntity entity) {
        try {
            int id = solutionDAO.create(entity);
            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<SolutionEntity> getEntityById(Integer id) {
        try {
            Optional<SolutionEntity> solutionEntity = solutionDAO.findById(id);
            if(solutionEntity.isEmpty()){
                throw new RuntimeException("Solution not found");
            }

            return solutionEntity;
        }
        catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Set<SolutionEntity> getAllEntities() {
        Set<SolutionEntity> solutionEntities;
        try {
            solutionEntities = solutionDAO.findAll();
            return solutionEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashSet<SolutionEntity>();
        }
    }

    @Override
    public SolutionEntity updateEntity(SolutionEntity newEntity) {
        try {
            Optional<SolutionEntity> returnEntity = solutionDAO.updateById(newEntity);
            if (returnEntity.isEmpty())
                throw new RuntimeException("Solution not found");
            return returnEntity.get();
        }
        catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEntity(Integer id) {
        try {
            return solutionDAO.deleteById(id).isPresent();
        }
        catch (SQLException e) {
            System.err.println("Failed to delete Solution");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Solution> convertEntityToModel(SolutionEntity entity) {
        Solution solutionModel = new Solution();
        solutionModel.setId(entity.getId());
        Problem problem = problemService.convertEntityToModel(problemService.getEntityById(entity.getProblemId()).get()).get();
        solutionModel.setProblem(problem); //can be null
        solutionModel.setName(entity.getName());
        solutionModel.setDescription(entity.getDescription());
        solutionModel.setSolutionCode(entity.getSolutionCode());
        return Optional.of(solutionModel);
    }

    @Override
    public Optional<Solution> getModelById(Integer id) {
        Optional<SolutionEntity> solutionEntity = getEntityById(id);
        try {
            if (solutionEntity.isPresent()) {
                Optional<Solution> solution = convertEntityToModel(solutionEntity.get());
                if (solution.isPresent()){
                    return solution;
                }
                else {
                    throw new RuntimeException("Failed to convert Solution entity to model");
                }
            }
            else {
                throw new RuntimeException("Solution not found");
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Set<Solution> getSolutionsGivenProblemId(int id) {
        Set<Solution> solutions = new HashSet<>();
        try {
            Set<SolutionEntity> solutionEntities = solutionDAO.findSolutionsGivenProblemId(id);
            for (SolutionEntity sEntity : solutionEntities) {
                Optional<Solution> solution = convertEntityToModel(sEntity);
                if (solution.isPresent()) {
                    solutions.add(solution.get());
                }
            }

            return solutions;
        }
        catch (SQLException e) {
            //log: no solutions associated with given problem
            return solutions;
        }
    }

    public Optional<Solution> getSolutionByNameGivenProblemId(String name, int id) {
        try {
            Optional<Solution> solution = solutionDAO.findSolutionsByNameGivenProblemId(name, id).iterator().next();
            if (solution.isPresent())
                return solution;
            else
                Optional.empty();
        }
        catch (SQLException | RuntimeException e) {
            Optional.empty();
        }
    }
}
