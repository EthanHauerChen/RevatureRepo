package org.leetrepository.repository.DAO;

import org.leetrepository.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAOInterface<T> {

    //create
    public Integer create(T entity) throws SQLException;

    //read
    public Optional<T> findById(int id) throws SQLException;
    public List<T> findAll() throws SQLException;

    //update
    public T updateById(T entity) throws SQLException;

    //delete
    public T deleteById(int id) throws SQLException;

}
