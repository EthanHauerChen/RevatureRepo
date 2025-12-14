package org.example.repository.DAO;

import org.example.repository.entities.DepartmentEntity;
import org.example.util.ConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDAO implements DAOInterface<DepartmentEntity>{

    //obtain the static connection from Connection
    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(DepartmentEntity departmentEntity) throws SQLException {

        /** https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
         * an object that contains a "precompiled" SQL statement that can be sent right away.
         * if faster that sending a normal Statement
         * most importantly, prevents SQL injection by using parameters (the ?). essentially,
         * the ? parameters will ALWAYS be treated as parameters to a statement and NEVER a SQL statement
         */
        String sql = "INSERT INTO department (department) VALUES (?) RETURNING id";

        /** LHS is an interface and Connection.prepareStatement returns a PreparedStatement type
         * but this is OK because the connection returned by the DriverManager specifically implements
         * a way to prepare the statement and return a non-interface subclass that implements PreparedStatement
         */
        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            //use PreparedStatement.set___ to specify the parameters in the prepared sql statement
            stmt.setString(1, departmentEntity.getDepartment());

            /** object that represents a SQL table returned from a SQL query,
             * represented as rows where each rs.next returns a row from the table
             */
            try(ResultSet rs = stmt.executeQuery()){
                // if success, return id of newly created department
                if(rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null; // failed to create department in the db
    }

    /** https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
     * Optionals are wrappers around objects in case they are null to avoid things like NullPointerExceptions.
     * To obtain the object if it exists, simply check Optional.isPresent(), and if true, then Optional.get()
     */
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

                    // return the Optional wrapper around the entity
                    return Optional.of(departmentEntity);
                }
            }
        }
        // return Optional equivalent of null
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
