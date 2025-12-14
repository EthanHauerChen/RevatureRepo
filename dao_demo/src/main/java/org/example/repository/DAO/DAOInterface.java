package org.example.repository.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/** the DAO specifies how to interact with the SQL db to obtain Java objects (entities),
 * or how to use entities to interact with the SQL db
 */

import org.example.repository.entities.DepartmentEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * interface that requires methods that query the SQL db
 * either using Java objects (entities in the entities directory), or obtain
 * information from the db and convert them to entities
 */
public interface DAOInterface<T> {
    // CRUD

    // CREATE
    // creates a SQL row entry based on a given entity object
    public Integer create(T entity) throws SQLException;

    // READ BY ID
    public Optional<T> findById(Integer id) throws SQLException;

    // READ ALL
    public List<T> findAll() throws SQLException;
    // UPDATE
    public T updateById(T entity) throws SQLException;
    // DELETE
    public boolean deleteById(Integer id) throws SQLException;
}
