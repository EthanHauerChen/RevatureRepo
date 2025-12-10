package org.example.repository.DAO;

import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.LocationEntity;
import org.example.util.ConnectionHandler;

import javax.xml.stream.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationDAO implements DAOInterface<LocationEntity> {

    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(LocationEntity entity) throws SQLException {
        String sql = "INSERT INTO location (location) VALUES (?) RETURNING id";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, entity.getLocation());

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    @Override
    public Optional<LocationEntity> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM location WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    LocationEntity location = new LocationEntity();
                    location.setId(rs.getInt("id"));
                    location.setLocation(rs.getString("location"));

                    return Optional.of(location);
                }
            }
        }
        return Optional.empty();    }

    @Override
    public List<LocationEntity> findAll() throws SQLException {
        List<LocationEntity> locations = new ArrayList<>();

        String sql = "SELECT * FROM location";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                LocationEntity location = new LocationEntity();
                location.setId(rs.getInt("id"));
                location.setLocation(rs.getString("location"));
                locations.add(location);
            }
        }
        return locations;    }

    @Override
    public LocationEntity updateById(LocationEntity entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        return false;
    }
}
