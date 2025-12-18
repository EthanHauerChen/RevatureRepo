package org.example.controller;

import org.example.repository.entities.LocationEntity;
import org.example.service.LocationService;
import org.example.service.model.Location;
import org.example.util.InputHandler;

import java.util.List;
import java.util.Optional;

public class LocationController {

    private final LocationService locationService = new LocationService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addLocation();
                case 2 -> searchLocationByName();
                case 3 -> searchLocationById();
                case 4 -> getAllLocations();
                case 0 -> {
                    System.out.println("Leaving Location Services");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void getAllLocations() {
        List<Location> locations = locationService.getAllModels();
        for(Location location : locations){
            System.out.println(location);
        }
    }

    private void searchLocationById() {
        // What can the user provide?
        // Location name
        Integer locationId = InputHandler.getIntInput("What is the location id?");
        Optional<Location> location = locationService.getModelById(locationId);
        if(location.isPresent()){
            System.out.println(location.get());
        }else{
            System.out.println("Location not found");
        }
    }

    private void searchLocationByName() {
        // What can the user provide?
        // Location name
        String locationName = InputHandler.getStringInput("What is the location name?");
        Optional<Location> location = locationService.getModelByLocationName(locationName);
        if(location.isPresent()){
            System.out.println(location.get());
        }else{
            System.out.println("Location not found");
        }
    }

    private void printMenu(){
        System.out.println("=== LOCATION SERVICES MENU ===");
        System.out.println("1. Add Location");
        System.out.println("2. Search Location by Name");
        System.out.println("3. Search Location by Id");
        System.out.println("4. Get All Locations");
        System.out.println("0. Exit");
    }

    private void addLocation(){
        // WHat is needed?
        // Location name
        String locationName = InputHandler.getStringInput("What is the new Location name?");
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLocation(locationName);
        Integer newLocationId = locationService.createEntity(locationEntity);

        if (newLocationId == -1){
            System.out.println("Invalid Location Name");
        }else{
            System.out.println("New Location Created: " + newLocationId);
        }
    }
}
