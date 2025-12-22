package org.leetrepository.service;

import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Topic;

import java.util.List;
import java.util.Optional;

public class TopicService implements ServiceInterface<TopicEntity, Topic> {

    @Override
    public Integer createEntity(TopicEntity entity) {
        return null;
    }

    @Override
    public Optional<TopicEntity> getEntityById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<TopicEntity> getAllEntities() {
        return null;
    }

    @Override
    public TopicEntity updateEntity(TopicEntity newEntity) {
        return null;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return false;
    }

    @Override
    public Optional<Topic> convertEntityToModel(TopicEntity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Topic> getModelById(Integer id) {
        return Optional.empty();
    }
}
