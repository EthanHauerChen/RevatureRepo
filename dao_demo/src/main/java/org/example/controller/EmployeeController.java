package org.example.controller;

import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.EmployeeEntity;
import org.example.service.DepartmentService;
import org.example.service.EmployeeService;
import org.example.service.LocationService;
import org.example.service.model.Department;
import org.example.service.model.Employee;
import org.example.service.model.Location;
import org.example.util.InputHandler;

import java.util.List;
import java.util.Optional;

public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService();
    private final LocationService locationService = new LocationService();
    private final DepartmentService departmentService = new DepartmentService();



    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Make a choice!");
            switch(choice){
                case 1 -> addEmployee();
//                case 2 -> getEmployeeById();
//                case 3 -> getEmployeeByName();
                case 4 -> getAllEmployees();
                case 5 -> getAllEmployeesByDepartment();
//                case 6 -> getAllEmployeesByLocation();
                case 0 -> {
                    System.out.println("Leaving Employee Services");
                    running = false; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void getAllEmployeesByDepartment() {
        String departmentName = InputHandler.getStringInput("What is the department name?");
        Optional<Department> department = departmentService.getModelByDepartmentName(departmentName);
        if(department.isPresent()){

            List<Employee> employees = employeeService.getAllModelsByDepartment(department.get());
            for(Employee employee: employees){
                System.out.println(employee);
            }
        }else{
            System.out.println("Invalid Department Name");
        }
    }

    private void getAllEmployees() {
        List<Employee> employees = employeeService.getAllModels();
        for(Employee employee: employees){
            System.out.println(employee);
        }
    }

    private void addEmployee() {
        // What do we expect from the user?
        // Employee full name
        String fullName = InputHandler.getStringInput("What is the Employees Full Name?");
        // Department Name
        String departmentName = InputHandler.getStringInput("What is the Department?");

        Optional<Department> department = departmentService.getModelByDepartmentName(departmentName);
        if (department.isPresent()){
            // Location Name
            String locationName = InputHandler.getStringInput("What is the Location name");

            Optional<Location> location = locationService.getModelByLocationName(locationName);

            if(location.isPresent()){
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setFullName(fullName);
                employeeEntity.setDepartmentId(department.get().getId());
                employeeEntity.setLocationId(location.get().getId());

                Integer newEmployeeId = employeeService.createEntity(employeeEntity);

                if(newEmployeeId == -1){
                    System.out.println("Unable to create employee");
                }else{
                    System.out.println("Employee has been created: " + newEmployeeId);
                }
            }else{
                System.out.println("Location name is invalid");
            }

        }else{
            System.out.println("Department name is invalid");
        }


    }

    private void printMenu(){
        System.out.println("=== EMPLOYEE SERVICES ===");
        System.out.println("1. Add Employee");
        System.out.println("2. Get Employee by Id");
        System.out.println("3. Get Employee by Name");
        System.out.println("4. Get All Employees");
        System.out.println("5. Get All Employees by Department");
        System.out.println("6. Get All Employees by Location");
        System.out.println("0. Exit");

    }
}
