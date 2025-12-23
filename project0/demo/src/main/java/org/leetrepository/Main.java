package org.leetrepository;

import org.leetrepository.controller.ProblemController;
import org.leetrepository.controller.SolutionController;
import org.leetrepository.controller.TopicController;
import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.util.ConnectionHandler;
import org.leetrepository.util.InputHandler;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ProblemController problemController = new ProblemController();
        SolutionController solutionController = new SolutionController();
        TopicController topicController = new TopicController();
        problemController = new ProblemController(solutionController, topicController);

        System.out.println("WELCOME TO THE LEETCODE REPOSITORY. TRACK YOUR LEETCODE SOLUTIONS HERE");

        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Make a choice: ");
            switch(choice){
                case 1 -> problemController.handleInput();
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
            }
        }
    }

    private static byte[] makeTestByteArray() {
        byte[] array = {(byte) 0xee, (byte) 0xff};
        return array;
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
