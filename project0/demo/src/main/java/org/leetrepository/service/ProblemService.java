package org.leetrepository.service;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Solution;
import org.leetrepository.service.model.Topic;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProblemService implements ServiceInterface<ProblemEntity, Problem> {
    ProblemDAO problemDAO;// = new ProblemDAO();

    //USED ONLY FOR TESTING, DO NOT USE
    public ProblemService(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public Integer createEntity(ProblemEntity entity) {
        try {
            int id = problemDAO.create(entity);
            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<ProblemEntity> getEntityById(Integer id) {
        try {
            Optional<ProblemEntity> problemEntity = problemDAO.findById(id);
            if(problemEntity.isEmpty()){
                throw new RuntimeException("Problem not found");
            }

            return problemEntity;
        }
        catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Set<ProblemEntity> getAllEntities() {
        Set<ProblemEntity> problemEntities;
        try {
            problemEntities = problemDAO.findAll();
            return problemEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashSet<ProblemEntity>();
        }
    }

    @Override
    public ProblemEntity updateEntity(ProblemEntity newEntity) {
        try {
            Optional<ProblemEntity> returnEntity = problemDAO.updateById(newEntity);
            if (returnEntity.isEmpty())
                throw new RuntimeException("Problem not found");
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
            return problemDAO.deleteById(id).isPresent();
        }
        catch (SQLException e) {
            System.err.println("Failed to delete");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Problem> convertEntityToModel(ProblemEntity entity) {
        Problem problemModel = new Problem();
        problemModel.setId(entity.getId());
        problemModel.setName(entity.getName());
        problemModel.setDescription(entity.getDescription());
        problemModel.setDifficulty(entity.getDifficulty());
        problemModel.setUrl(entity.getUrl());
        return Optional.of(problemModel);
    }

    @Override
    public Optional<Problem> getModelById(Integer id) {
        Optional<ProblemEntity> problemEntity = getEntityById(id);
        try {
            if (problemEntity.isPresent()) {
                Optional<Problem> problem = convertEntityToModel(problemEntity.get());
                if (problem.isPresent()){
                    return problem;
                }
                else {
                    throw new RuntimeException("Failed to convert Problem entity to model");
                }
            }
            else {
                throw new RuntimeException("Problem not found");
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Set<Problem> getProblemsGivenTopicEntity(TopicEntity entity) {
        Set<Problem> problems = new HashSet<>();
        try {
            Set<ProblemEntity> problemEntities = problemDAO.findProblemsGivenTopic(entity);
            for (ProblemEntity pEntity : problemEntities) {
                Optional<Problem> problem = convertEntityToModel(pEntity);
                if (problem.isPresent()) {
                    problems.add(problem.get());
                }
            }

            return problems;
        }
        catch (SQLException e) {
            //log: no problems associated with given topic
            return problems;
        }
    }
}
