package org.leetrepository.repository.DAO;

import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolutionDAO implements DAOInterface<SolutionEntity> {
//    Connection connection = ConnectionHandler.getConnection();
//
//    @Override
//    public Integer create(SolutionEntity entity) throws SQLException {
//        String sql = "INSERT INTO solution (name) values (?) RETURNING id;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, entity.getName());
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) return rs.getInt("id");
//            }
//        }
//        return -1;
//    }
//
//    public Integer create(String name) throws SQLException {
//        String sql = "INSERT INTO solution (name) values (?) RETURNING id;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, name);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) return rs.getInt("id");
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public Optional<SolutionEntity> findById(int id) throws SQLException {
//        String sql = "SELECT * FROM solution WHERE id = ?;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return Optional.of(constructEntity(rs));
//                }
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    @Override
//    public List<SolutionEntity> findAll() throws SQLException {
//        List<SolutionEntity> solutionEntities = new ArrayList<>();
//        String sql = "SELECT * FROM solution;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) { //could also use createStatement here. when to use which: https://www.baeldung.com/java-statement-preparedstatement
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    SolutionEntity entity = constructEntity(rs);
//                    solutionEntities.add(entity);
//                }
//            }
//        }
//
//        return solutionEntities;
//    }
//
//    @Override
//    public Optional<SolutionEntity> updateById(SolutionEntity entity) throws SQLException {
//        String sql = "UPDATE solution SET name = ? WHERE id = ? RETURNING *;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, entity.getName());
//            stmt.setInt(2, entity.getId());
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) { //return SolutionEntity constructed from rs to verify
//                    return Optional.of(constructEntity(rs));
//                }
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<SolutionEntity> deleteById(int id) throws SQLException {
//        String sql = "DELETE FROM solution where id = ? RETURNING *;";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) return Optional.of(constructEntity(rs));
//            }
//        }
//        return Optional.empty();
//    }
//
//    private SolutionEntity constructEntity(ResultSet rs) throws SQLException {
//        SolutionEntity SolutionEntity = new SolutionEntity();
//        SolutionEntity.setId(rs.getInt("id"));
//        SolutionEntity.setName(rs.getString("name"));
//        return SolutionEntity;
//    }

    @Override
    public Integer create(SolutionEntity entity) throws SQLException {
        return null;
    }

    @Override
    public Optional<SolutionEntity> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<SolutionEntity> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<SolutionEntity> updateById(SolutionEntity entity) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<SolutionEntity> deleteById(int id) throws SQLException {
        return Optional.empty();
    }
}
