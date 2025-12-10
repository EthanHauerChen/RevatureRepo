package org.example.repository.entities;

import java.util.Objects;

public class EmployeeEntity {
    private Integer id;
    private String fullName;
    private Integer departmentId;
    private Integer locationId;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer id, String fullName, Integer departmentId, Integer locationId) {
        this.id = id;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.locationId = locationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", departmentId=" + departmentId +
                ", locationId=" + locationId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(departmentId, that.departmentId) && Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, departmentId, locationId);
    }
}
