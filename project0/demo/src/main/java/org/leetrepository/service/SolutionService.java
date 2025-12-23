package org.leetrepository.service;

import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Solution;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SolutionService implements ServiceInterface<SolutionEntity, Solution> {
    @Override
    public Integer createEntity(SolutionEntity entity) {
        return null;
    }

    @Override
    public Optional<SolutionEntity> getEntityById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Set<SolutionEntity> getAllEntities() {
        return null;
    }

    @Override
    public SolutionEntity updateEntity(SolutionEntity newEntity) {
        return null;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return false;
    }

    @Override
    public Optional<Solution> convertEntityToModel(SolutionEntity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Solution> getModelById(Integer id) {
        return Optional.empty();
    }
}
