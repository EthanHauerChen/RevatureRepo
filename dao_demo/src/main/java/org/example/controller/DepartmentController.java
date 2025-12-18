package org.example.controller;

import org.example.repository.entities.DepartmentEntity;
import org.example.repository.entities.LocationEntity;
import org.example.service.DepartmentService;
import org.example.service.LocationService;
import org.example.service.model.Department;
import org.example.service.model.Location;
import org.example.util.InputHandler;

import java.util.List;
import java.util.Optional;

public class DepartmentController {

    private final DepartmentService departmentService = new DepartmentService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addDepartment();
                case 2 -> searchDepartmentByName();
                case 3 -> searchDepartmentById();
                case 4 -> getAllDepartments();
                case 0 -> {
                    System.out.println("Leaving Department Services");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void getAllDepartments() {
        List<Department> departments = departmentService.getAllModels();
        for(Department department : departments){
            System.out.println(department);
        }
    }

    private void searchDepartmentById() {
        // What can the user provide?
        Integer departmentId = InputHandler.getIntInput("What is the department id?");
        Optional<Department> department = departmentService.getModelById(departmentId);
        if(department.isPresent()){
            System.out.println(department.get());
        }else{
            System.out.println("Location not found");
        }
    }

    private void searchDepartmentByName() {
        // What can the user provide?
        String departmentName = InputHandler.getStringInput("What is the department name?");
        Optional<Department> department = departmentService.getModelByDepartmentName(departmentName);
        if(department.isPresent()){
            System.out.println(department.get());
        }else{
            System.out.println("Department not found");
        }
    }

    private void printMenu(){
        System.out.println("=== DEPARTMENT SERVICES MENU ===");
        System.out.println("1. Add Department");
        System.out.println("2. Search Department by Name");
        System.out.println("3. Search Department by Id");
        System.out.println("4. Get All Departments");
        System.out.println("0. Exit");
    }

    private void addDepartment(){
        // WHat is needed?
        String departmentName = InputHandler.getStringInput("What is the new Department name?");
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartment(departmentName);
        Integer newDepartmentId = departmentService.createEntity(departmentEntity);

        if (newDepartmentId == -1){
            System.out.println("Invalid Department Name");
        }else{
            System.out.println("New Department Created: " + newDepartmentId);
        }
    }
}
