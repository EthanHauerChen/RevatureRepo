package org.leetrepository.repository.DAO;

import org.leetrepository.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DAOInterface<T> {

    //create
    public Integer create(T entity) throws SQLException;

    //read
    public Optional<T> findById(int id) throws SQLException;
    public Set<T> findAll() throws SQLException;

    //update
    public Optional<T> updateById(T entity) throws SQLException;

    //delete
    public Optional<T> deleteById(int id) throws SQLException;

}
