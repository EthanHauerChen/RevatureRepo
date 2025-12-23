package org.leetrepository.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceInterface<T, U> {

    // CRUD Operations

    Integer createEntity(T entity);
    Optional<T> getEntityById(Integer id);
    Set<T> getAllEntities();
    T updateEntity(T newEntity);
    boolean deleteEntity(Integer id);

    // Conversion
    Optional<U> convertEntityToModel(T entity);

    Optional<U> getModelById(Integer id);


}