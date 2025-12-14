package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.util.ConnectionHandler;

import java.sql.*;
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
            if (entity.getDescription() == null)
                stmt.setNull(3, Types.VARCHAR)
            else
                stmt.setString(3, entity.getDescription());
            if (entity.getDifficulty() == null)
                stmt.setNull(4, Types.VARCHAR);
            else
                stmt.setString(4, entity.getDifficulty());
            if (entity.getUrl() == null)
                stmt.setNull(5, Types.VARCHAR);
            else
                stmt.setString(5, entity.getUrl());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Optional<ProblemEntity> findById(int id) throws SQLException {
        String sql = "SELECT * FROM problem WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProblemEntity entity = new ProblemEntity();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    if (rs.getString("description") == null)
                        entity.getName
                }
            }
        }
    }

    @Override
    public List<ProblemEntity> findAll() throws SQLException {
        return null;
    }

    @Override
    public ProblemEntity updateById(ProblemEntity entity) throws SQLException {
        return null;
    }

    @Override
    public ProblemEntity deleteById(int id) throws SQLException {
        return null;
    }
}
