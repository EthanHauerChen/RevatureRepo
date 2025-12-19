package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.example.repository.UserDAO;
import org.example.repository.entity.UserEntity;
import org.example.util.ConnectionHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        MongoClient client = ConnectionHandler.getClient();
//        MongoDatabase database = ConnectionHandler.getDatabase();
//
//        ConnectionHandler.closeClient();
        UserDAO userDAO = new UserDAO("users");

        // CRUD Operations
        // Create
        UserEntity newUserEntity = new UserEntity("john_doe", "pass1");
        newUserEntity.setRoles(List.of("Engineer", "IT"));
        userDAO.insert(newUserEntity);

        // Read
        UserEntity found = userDAO.findByUsernameAndPassword("john_doe", "pass1");
        System.out.println(found);



        ConnectionHandler.closeClient();
    }
}