package org.leetrepository.service;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Problem;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProblemService implements ServiceInterface<ProblemEntity, Problem> {
    ProblemDAO problemDAO = new ProblemDAO();

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
    public List<ProblemEntity> getAllEntities() {
        try {
            List<ProblemEntity> problemEntities = problemDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProblemEntity updateEntity(Integer id, ProblemEntity newEntity) {
        return null;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return false;
    }

    @Override
    public Optional<Problem> convertEntityToModel(ProblemEntity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Problem> getModelById(Integer id) {
        return Optional.empty();
    }
}
