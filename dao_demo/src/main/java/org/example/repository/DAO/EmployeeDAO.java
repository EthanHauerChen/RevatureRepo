package org.example.repository.DAO;

import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.EmployeeEntity;
import org.example.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements DAOInterface<EmployeeEntity> {
    private Connection connection = ConnectionHandler.getConnection();
    @Override
    public Integer create(EmployeeEntity entity) throws SQLException {
        String sql = "INSERT INTO employee (full_name, dept_id, loc_id) VALUES (?, ?, ?) RETURNING id";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, entity.getFullName());
            stmt.setInt(2, entity.getDepartmentId());
            stmt.setInt(3, entity.getLocationId());

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null;    }

    @Override
    public Optional<EmployeeEntity> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    EmployeeEntity employee = new EmployeeEntity();
                    employee.setId(rs.getInt("id"));
                    employee.setFullName(rs.getString("full_name"));
                    employee.setDepartmentId(rs.getInt("dept_id"));
                    employee.setLocationId(rs.getInt("loc_id"));

                    return Optional.of(employee);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeEntity> findAll() throws SQLException {
        List<EmployeeEntity> employees = new ArrayList<>();

        String sql = "SELECT * FROM employee";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){

                EmployeeEntity employee = new EmployeeEntity();
                employee.setId(rs.getInt("id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setDepartmentId(rs.getInt("dept_id"));
                employee.setLocationId(rs.getInt("loc_id"));

                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public EmployeeEntity updateById(EmployeeEntity entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        return false;
    }
}
