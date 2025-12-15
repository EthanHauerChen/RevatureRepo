package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.TopicEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TopicDAO implements DAOInterface<TopicEntity> {

    @Override
    public Integer create(TopicEntity entity) throws SQLException {
        return null;
    }

    @Override
    public Optional<TopicEntity> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<TopicEntity> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<TopicEntity> updateById(TopicEntity entity) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<TopicEntity> deleteById(int id) throws SQLException {
        return Optional.empty();
    }
}
