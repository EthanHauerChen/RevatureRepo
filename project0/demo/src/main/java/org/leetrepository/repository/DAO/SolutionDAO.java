package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.util.ConnectionHandler;

import java.nio.ByteBuffer;
import java.sql.*;
import java.util.*;

public class SolutionDAO implements DAOInterface<SolutionEntity> {
    Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(SolutionEntity entity) throws SQLException {
        String sql = "INSERT INTO solution (problem_id, name, description, solution_code) values (?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getProblemId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.setBytes(4, entity.getSolutionCode().array());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Optional<SolutionEntity> findById(int id) throws SQLException {
        String sql = "SELECT * FROM solution WHERE id = ?;";
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

    @Override
    public Set<SolutionEntity> findAll() throws SQLException {
        Set<SolutionEntity> solutionEntities = new HashSet<>();
        String sql = "SELECT * FROM solution;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { //could also use createStatement here. when to use which: https://www.baeldung.com/java-statement-preparedstatement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SolutionEntity entity = constructEntity(rs);
                    solutionEntities.add(entity);
                }
            }
        }

        return solutionEntities;
    }

    public Set<SolutionEntity> findSolutionsGivenProblem(ProblemEntity problem) throws SQLException {
        return findSolutionsGivenProblemId(problem.getId());
    }
    public Set<SolutionEntity> findSolutionsGivenProblemId(int problem_id) throws SQLException {
        Set<SolutionEntity> solutionEntities = new HashSet<>();
        String sql = "SELECT * FROM solution " +
                "WHERE problem_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, problem_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    solutionEntities.add(constructEntity(rs));
                }
            }
        }
        return solutionEntities;
    }


    public Set<SolutionEntity> findSolutionsByName(String name) throws SQLException {
        Set<SolutionEntity> solutionEntities = new HashSet<>();
        String sql = "SELECT * FROM solution " +
                "WHERE name ILIKE ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    solutionEntities.add(constructEntity(rs));
                }
            }
        }
        return solutionEntities;
    }

    public Set<SolutionEntity> findSolutionsByNameGivenProblem(String name, ProblemEntity problem) throws SQLException {
        return findSolutionsByNameGivenProblemId(name, problem.getId());
    }
    public Set<SolutionEntity> findSolutionsByNameGivenProblemId(String name, int problem_id) throws SQLException {
        Set<SolutionEntity> solutionEntities = new HashSet<>();
        String sql = "SELECT * FROM solution " +
                "WHERE name ILIKE ? " +
                "AND problem_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setInt(2, problem_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    solutionEntities.add(constructEntity(rs));
                }
            }
        }
        return solutionEntities;
    }

    @Override
    public Optional<SolutionEntity> updateById(SolutionEntity entity) throws SQLException {
        String sql = "UPDATE solution SET problem_id = ?, name = ?, description = ?, solution_code = ? WHERE id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getProblemId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.setBytes(4, entity.getSolutionCode().array());
            stmt.setInt(5, entity.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { //return SolutionEntity constructed from rs to verify
                    return Optional.of(constructEntity(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<SolutionEntity> deleteById(int id) throws SQLException {
        String sql = "DELETE FROM solution where id = ? RETURNING *;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(constructEntity(rs));
            }
        }
        return Optional.empty();
    }

    private SolutionEntity constructEntity(ResultSet rs) throws SQLException {
        SolutionEntity solutionEntity = new SolutionEntity();
        solutionEntity.setId(rs.getInt("id"));
        solutionEntity.setProblemId(rs.getInt("problem_id"));
        solutionEntity.setName(rs.getString("name"));
        solutionEntity.setDescription(rs.getString("description"));
        solutionEntity.setSolutionCode(ByteBuffer.wrap(rs.getBytes("solution_code")));

        return solutionEntity;
    }
}
