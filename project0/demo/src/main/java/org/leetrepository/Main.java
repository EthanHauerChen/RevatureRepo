package org.leetrepository;

import org.leetrepository.controller.ProblemController;
import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.util.ConnectionHandler;
import org.leetrepository.util.InputHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        System.out.println("======Welcome to the LeetCode Repository. This tracks all your LeetCode problems and solutions======");
//        ProblemController problemController = new ProblemController();
//        SolutionController solutionController = new SolutionController();
//
//        boolean running = true;
//        while(running){
//            printMenu();
//            int choice = InputHandler.getIntInput("Make a choice: ");
//            switch(choice){
//                case 1 -> problemController.handleInput();
//                case 2 -> solutionController.handleInput();
//                case 0 -> {
//                    System.out.println("Goodbye!");
//                    running = false;
//                }
//            }
//        }

        TopicDAO topicDAO = new TopicDAO();
        //test findAll topics
//        try {
//            System.out.println(topicDAO.findAll());
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test create success
//        try {
//            TopicEntity t1 = new TopicEntity("Math");
//            int id1 = topicDAO.create(t1);
//            int id2 = topicDAO.create("Graph");
//            TopicEntity topic1 = topicDAO.findById(id1).get();
//            TopicEntity topic2 = topicDAO.findById(id2).get();
//            System.out.println("Test create with entity: " + id1 + topic1);
//            System.out.println("test create with String: " + id2 + topic2);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test create failure
//        try {
//            TopicEntity t1 = new TopicEntity("Math");
//            int id1 = topicDAO.create(t1);
//            System.out.println("Should not be printed, Math topic already exists, should've thrown error" + id1);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Properly threw error");
//        }
        //test find success
//        try {
//            TopicEntity t1 = topicDAO.findById(12).get();
//            System.out.println(t1);
//            List<TopicEntity> list = topicDAO.findAll();
//            System.out.println(list);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test find failure
//        try {
//            Optional<TopicEntity> t1 = topicDAO.findById(9999999);
//            System.out.println(t1);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test update success
//        try {
//            int id = topicDAO.create("Enumerattion");
//            TopicEntity t = new TopicEntity(id, "Enumeration");
//            System.out.println("Before update " + topicDAO.findById(id));
//            System.out.println("After update " + topicDAO.updateById(t));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test update failure
//        try {
//            TopicEntity t = new TopicEntity(9999, "asfoijawe");
//            System.out.println(topicDAO.updateById(t));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test delete success
//        try {
//            System.out.println(topicDAO.deleteById(25));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test delete failure
        try {
            System.out.println(topicDAO.deleteById(99999));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. Problem Menu");
        System.out.println("\tadd or edit a LeetCode problem");
        System.out.println("2. Solution Menu");
        System.out.println("\tadd or edit a LeetCode solution");
        System.out.println("0. Exit");
    }
}
