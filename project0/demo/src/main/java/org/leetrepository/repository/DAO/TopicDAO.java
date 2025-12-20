package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicDAO implements DAOInterface<TopicEntity> {
    Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(TopicEntity entity) throws SQLException {
        String sql = "INSERT INTO topic (name) values (?) RETURNING id;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    public Integer create(String name) throws SQLException {
        String sql = "INSERT INTO topic (name) values (?) RETURNING id;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Optional<TopicEntity> findById(int id) throws SQLException {
        String sql = "SELECT * FROM topic WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(constructEntity(rs));
                }
            }
        }

        return Optional.empty();
    }

    public List<TopicEntity> findByName(String name) throws SQLException {
        List<TopicEntity> topicEntities = new ArrayList<>();
        String sql = "SELECT * FROM topic WHERE name ILIKE ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TopicEntity entity = constructEntity(rs);
                    topicEntities.add(entity);
                }
            }
        }

        return topicEntities;
    }

    @Override
    public List<TopicEntity> findAll() throws SQLException {
        List<TopicEntity> topicEntities = new ArrayList<>();
        String sql = "SELECT * FROM topic;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { //could also use createStatement here. when to use which: https://www.baeldung.com/java-statement-preparedstatement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TopicEntity entity = constructEntity(rs);
                    topicEntities.add(entity);
                }
            }
        }

        return topicEntities;
    }

    public List<TopicEntity> findTopicsGivenProblem(ProblemEntity problem) throws SQLException {
        List<TopicEntity> topicEntities = new ArrayList<>();
        String sql = "SELECT t.* " +
                "FROM problem p, topic t, problem_topic pt " +
                "WHERE p.id = ? " +
                "AND p.id = pt.problem_id " +
                "AND t.id = pt.topic_id;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, problem.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    topicEntities.add(constructEntity(rs));
                }
            }
        }
        return topicEntities;
    }

    @Override
    public Optional<TopicEntity> updateById(TopicEntity entity) throws SQLException {
        String sql = "UPDATE topic SET name = ? WHERE id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { //return TopicEntity constructed from rs to verify
                    return Optional.of(constructEntity(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<TopicEntity> deleteById(int id) throws SQLException {
        String sql = "DELETE FROM topic where id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(constructEntity(rs));
            }
        }
        return Optional.empty();
    }

    private TopicEntity constructEntity(ResultSet rs) throws SQLException {
        TopicEntity TopicEntity = new TopicEntity();
        TopicEntity.setId(rs.getInt("id"));
        TopicEntity.setName(rs.getString("name"));
        return TopicEntity;
    }
}
