package org.example.service;

import org.example.repository.DAO.LocationDAO;
import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.LocationEntity;
import org.example.service.interfaces.ServiceInterface;
import org.example.service.model.Department;
import org.example.service.model.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LocationService implements ServiceInterface<LocationEntity, Location> {

    private LocationDAO locationDAO = new LocationDAO();

    @Override
    public Integer createEntity(LocationEntity entity) {
        try{
            Integer newId = locationDAO.create(entity);

            return newId;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<LocationEntity> getEntityById(Integer id) {
        try{
            Optional<LocationEntity> locationEntity = locationDAO.findById(id);
            if(locationEntity.isEmpty()){
                throw new RuntimeException("Location not found");
            }
            return locationEntity;
        }catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<LocationEntity> getAllEntities() {
        return null;
    }

    @Override
    public LocationEntity updateEntity(Integer id, LocationEntity newEntity) {
        return null;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return false;
    }

    @Override
    public Optional<Location> convertEntityToModel(LocationEntity entity) {
        Location location = new Location();
        location.setId(entity.getId());
        location.setLocation(entity.getLocation());

        return Optional.of(location);
    }

    @Override
    public Optional<Location> getModelById(Integer id) {
        Optional<LocationEntity> locationEntity = getEntityById(id);
        try{
            if(locationEntity.isPresent()){
                Optional<Location> location = convertEntityToModel(locationEntity.get());
                if(location.isPresent()){
                    return location;
                }else{
                    throw new RuntimeException("DepartmentEntity conversion failed");
                }
            }else{
                throw new RuntimeException("DepartmentEntity not found");
            }
        }catch(RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
