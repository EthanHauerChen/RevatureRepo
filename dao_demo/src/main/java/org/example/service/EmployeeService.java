package org.example.service;

import org.example.repository.DAO.EmployeeDAO;
import org.example.repository.entities.EmployeeEntity;
import org.example.service.interfaces.ServiceInterface;
import org.example.service.model.Department;
import org.example.service.model.Employee;
import org.example.service.model.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService implements ServiceInterface<EmployeeEntity, Employee> {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    private DepartmentService departmentService = new DepartmentService();
    private LocationService locationService = new LocationService();

    @Override
    public Integer createEntity(EmployeeEntity entity) {
        try{
            Integer newId = employeeDAO.create(entity);
            return newId;
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<EmployeeEntity> getEntityById(Integer id) {
        try{
            Optional<EmployeeEntity> employee = employeeDAO.findById(id);
            if(employee.isEmpty()){
                throw new RuntimeException("Employee not found");
            }
            return employee;
        }catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<EmployeeEntity> getAllEntities() {
        return null;
    }

    @Override
    public EmployeeEntity updateEntity(Integer id, EmployeeEntity newEntity) {
        return null;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return false;
    }

    @Override
    public Optional<Employee> convertEntityToModel(EmployeeEntity entity) {
        try{
            Optional<Location> location = locationService.getModelById(entity.getLocationId());

            if(location.isEmpty()){
                throw new RuntimeException("Invalid location id");
            }

            Optional<Department> department = departmentService.getModelById(entity.getDepartmentId());

            if(department.isEmpty()){
                throw new RuntimeException("Invalid department id");
            }

            Employee employee = new Employee();
            employee.setId(entity.getId());
            employee.setFullName(entity.getFullName());
            employee.setLocation(location.get());
            employee.setDepartment(department.get());

            return Optional.of(employee);

        }catch(RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> getModelById(Integer id) {

        try{
            Optional<Employee> employee = convertEntityToModel(getEntityById(id).get());
            return employee;
        }catch(RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }

    }
}
