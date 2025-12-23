package org.leetrepository.service;

import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.interfaces.ServiceInterface;
import org.leetrepository.service.model.Problem;
import org.leetrepository.service.model.Topic;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TopicService implements ServiceInterface<TopicEntity, Topic> {
    private TopicDAO topicDAO;// = new TopicDAO();

    //FOR TESTING ONLY
    public TopicService(TopicDAO topicDAO, ProblemService problemService) {
        this.topicDAO = topicDAO;
    }

    @Override
    public Integer createEntity(TopicEntity entity) {
        try {
            int id = topicDAO.create(entity);
            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<TopicEntity> getEntityById(Integer id) {
        try {
            Optional<TopicEntity> topicEntity = topicDAO.findById(id);
            if(topicEntity.isEmpty()){
                throw new RuntimeException("Topic not found");
            }

            return topicEntity;
        }
        catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Set<TopicEntity> getAllEntities() {
        Set<TopicEntity> topicEntities;
        try {
            topicEntities = topicDAO.findAll();
            return topicEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashSet<TopicEntity>();
        }
    }

    @Override
    public TopicEntity updateEntity(TopicEntity newEntity) {
        try {
            Optional<TopicEntity> returnEntity = topicDAO.updateById(newEntity);
            if (returnEntity.isEmpty())
                throw new RuntimeException("Topic not found");
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
            return topicDAO.deleteById(id).isPresent();
        }
        catch (SQLException e) {
            System.err.println("Failed to delete");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Topic> convertEntityToModel(TopicEntity entity) {
        Topic topic = new Topic();
        topic.setId(entity.getId());
        topic.setName(entity.getName());
        return Optional.of(topic);
    }

    @Override
    public Optional<Topic> getModelById(Integer id) {
        Optional<TopicEntity> topicEntity = getEntityById(id);
        try {
            if (topicEntity.isPresent()) {
                Optional<Topic> topic = convertEntityToModel(topicEntity.get());
                if (topic.isPresent()){
                    return topic;
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

    public Optional<Topic> getModelByName(String name) {
        try {
            Set<TopicEntity> topicEntities = topicDAO.findByName(name);
            if (!topicEntities.isEmpty()) {
                Optional<Topic> topic = convertEntityToModel(topicEntities.iterator().next());
                if (topic.isPresent()) {
                    return topic;
                }
                else {
                    throw new RuntimeException("Unable to convert topic entity to model");
                }
            }
            else {
                throw new RuntimeException("Topic not found");
            }
        }
        catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Set<Topic> getTopicsGivenProblemEntity(ProblemEntity entity) {
        Set<Topic> topics = new HashSet<>();
        try {
            Set<TopicEntity> topicEntities = topicDAO.findTopicsGivenProblem(entity);
            for (TopicEntity tEntity : topicEntities) {
                Optional<Topic> topic = convertEntityToModel(tEntity);
                if (topic.isPresent()) {
                    topics.add(topic.get());
                }
            }

            return topics;
        }
        catch (SQLException e) {
            //log: no topics associated with given problem
            return topics;
        }
    }
}
