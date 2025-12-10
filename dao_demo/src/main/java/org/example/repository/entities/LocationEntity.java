package org.example.repository.entities;

import java.util.Objects;

public class LocationEntity {

    private Integer id;
    private String location;

    public LocationEntity() {
    }

    public LocationEntity(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntity location1 = (LocationEntity) o;
        return Objects.equals(id, location1.id) && Objects.equals(location, location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}
