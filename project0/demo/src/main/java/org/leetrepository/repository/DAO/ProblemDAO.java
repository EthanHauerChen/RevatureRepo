package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProblemDAO implements DAOInterface<ProblemEntity> {
    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(ProblemEntity entity) throws SQLException {
        String sql = "INSERT INTO problem (id, name, description, difficulty, url) values (?, ?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.setObject(4, entity.getDifficulty(), Types.OTHER); //can't use setString because difficulty is an ENUM type
            stmt.setString(5, entity.getUrl());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Optional<ProblemEntity> findById(int id) throws SQLException {
        String sql = "SELECT * FROM problem WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProblemEntity entity = new ProblemEntity();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setDescription(rs.getString("description"));
                    entity.setDifficulty(rs.getString("difficulty"));
                    entity.setUrl(rs.getString("url"));
                    return Optional.of(entity);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<ProblemEntity> findAll() throws SQLException {
        List<ProblemEntity> problemEntities = new ArrayList<>();
        String sql = "SELECT * FROM problem;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { //could also use createStatement here. when to use which: https://www.baeldung.com/java-statement-preparedstatement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProblemEntity entity = new ProblemEntity();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setDescription(rs.getString("description"));
                    entity.setDifficulty(rs.getString("difficulty"));
                    entity.setUrl(rs.getString("url"));
                    problemEntities.add(entity);
                }
            }
        }

        return problemEntities;
    }

    @Override
    public Optional<ProblemEntity> updateById(ProblemEntity entity) throws SQLException {
        String sql = "UPDATE problem SET name = ?, description = ?, difficulty = ?, url = ? WHERE id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setObject(3, entity.getDifficulty(), Types.OTHER);
            stmt.setString(4, entity.getUrl());
            stmt.setInt(5, entity.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { //return ProblemEntity constructed from rs to verify
                    return Optional.of(constructEntity(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<ProblemEntity> deleteById(int id) throws SQLException {
        String sql = "DELETE FROM problem where id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(constructEntity(rs));
            }
        }
        return Optional.empty();
    }

    private ProblemEntity constructEntity(ResultSet rs) throws SQLException {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(rs.getInt("id"));
        problemEntity.setName(rs.getString("name"));
        problemEntity.setDescription(rs.getString("description"));
        problemEntity.setDifficulty(rs.getString("difficulty"));
        problemEntity.setUrl(rs.getString("url"));
        return problemEntity;
    }
}
