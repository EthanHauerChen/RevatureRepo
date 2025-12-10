package org.example.service.model;

import java.util.Objects;

public class Employee {
    private Integer id;
    private String fullName;
    private Department department;
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(fullName, employee.fullName) && Objects.equals(department, employee.department) && Objects.equals(location, employee.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, department, location);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", department=" + department +
                ", location=" + location +
                '}';
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Employee(Integer id, String fullName, Department department, Location location) {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.location = location;
    }

    public Employee() {
    }
}
