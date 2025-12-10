package org.example.repository.DAO;

import org.example.repository.entities.DepartmentEntity;
import org.example.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDAO implements DAOInterface<DepartmentEntity>{

    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(DepartmentEntity departmentEntity) throws SQLException {

        String sql = "INSERT INTO department (department) VALUES (?) RETURNING id";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, departmentEntity.getDepartment());

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    @Override
    public Optional<DepartmentEntity> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM department WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    DepartmentEntity departmentEntity = new DepartmentEntity();
                    departmentEntity.setId(rs.getInt("id"));
                    departmentEntity.setDepartment(rs.getString("department"));

                    return Optional.of(departmentEntity);
                }
            }
        }
        return Optional.empty();
    }

    // READ ALL
    public List<DepartmentEntity> findAll() throws SQLException {
        List<DepartmentEntity> departments = new ArrayList<>();

        String sql = "SELECT * FROM department";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setId(rs.getInt("id"));
                departmentEntity.setDepartment(rs.getString("department"));
                departments.add(departmentEntity);
            }
        }
        return departments;
    }

    @Override
    public DepartmentEntity updateById(DepartmentEntity entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException{
        return false;
    }
}
