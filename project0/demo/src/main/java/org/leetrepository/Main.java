package org.leetrepository;

import org.leetrepository.controller.ProblemController;
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
        ProblemDAO problemDAO = new ProblemDAO();
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
//        try {
//            System.out.println(topicDAO.deleteById(99999));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test problem getByName
//        ProblemDAO problemDAO = new ProblemDAO();
//        try {
//            System.out.println(problemDAO.findByName("sasdfum"));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test topic findByName
//        try {
//            System.out.println(topicDAO.findByName("seasdfasfdarch"));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test problem find problems related to topic
//        try {
//            TopicEntity topic = topicDAO.findByName("Graph").get(0);
//            System.out.println(problemDAO.findProblemsForTopic(topic));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test add topic to problem
//        try {
//            ProblemEntity problem = problemDAO.findByName("Reverse words").get(0);
//            TopicEntity topic = topicDAO.findByName("Two Pointers").get(0);
//            problemDAO.addTopicToProblem(problem, topic);
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test find topics for a given problem
//        try {
//            ProblemEntity problem = problemDAO.findByName("Climbing").get(0);
//            System.out.println(topicDAO.findTopicsForProblem(problem));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test adding multiple topics to problem
//        try {
//            ProblemEntity problem = problemDAO.findByName("Sum of Two Integers").get(0);
//            List<TopicEntity> topics = new ArrayList<>();
//            topics.add(topicDAO.findByName("Math").get(0));
//            topics.add(topicDAO.findByName("Bit Manipulation").get(0));
//            problemDAO.addTopicsToProblem(problem, topics);
//            System.out.println(topicDAO.findTopicsForProblem(problem));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        SolutionDAO solutionDAO = new SolutionDAO();
        //test create + findbyid
//        try {
////            SolutionEntity solution = new SolutionEntity();
////            solution.setProblemId(1);
////            solution.setName("test");
////            solution.setDescription("test");
////            solution.setSolutionCode(ByteBuffer.wrap(makeTestByteArray()));
////            int id = solutionDAO.create(solution);
////            System.out.println(solutionDAO.findById(id));
////        }
////        catch (SQLException e) {
////            e.printStackTrace();
////        }
        //test create failure
//        try {
//            SolutionEntity solution = new SolutionEntity();
//            solution.setProblemId(1);
//            solution.setName("test");
//            solution.setDescription("test");
//            solution.setSolutionCode(ByteBuffer.wrap(makeTestByteArray()));
//            int id = solutionDAO.create(solution);
//            System.out.println(solutionDAO.findById(id));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test find all
//        try {
//            System.out.println(solutionDAO.findAll());
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test findsolutionsgivenproblem
//        try {
//            SolutionEntity solution = new SolutionEntity();
//            solution.setProblemId(53);
//            solution.setName("test");
//            solution.setDescription("test");
//            solution.setSolutionCode(ByteBuffer.wrap(makeTestByteArray()));
//            solutionDAO.create(solution);
//            ProblemEntity problem1 = new ProblemEntity();
//            ProblemEntity problem2 = new ProblemEntity();
//            problem2.setId(99999);
//            problem1.setId(1);
//            System.out.println(solutionDAO.findSolutionsGivenProblem(problem1));
//            System.out.println(solutionDAO.findSolutionsGivenProblem(problem2));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test find by name
//        try {
//            System.out.println(solutionDAO.findSolutionsByName("test"));
//            System.out.println(solutionDAO.findSolutionsByName("asdf"));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        //test find by name given problem
//        try {
//            ProblemEntity problem = new ProblemEntity();
//            problem.setId(1);
//            System.out.println(solutionDAO.findSolutionsByNameGivenProblem("test", problem));
//            System.out.println(solutionDAO.findSolutionsByNameGivenProblemId("test", 1));
//            System.out.println(solutionDAO.findSolutionsByNameGivenProblemId("test", 53));
//            System.out.println(solutionDAO.findSolutionsByNameGivenProblemId("test", 151));
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test update
//        try {
//            SolutionEntity solution = solutionDAO.findSolutionsGivenProblemId(53).get(0);
//            solution.setName("new name");
//            solution.setDescription("new descript");
//            solutionDAO.updateById(solution);
//        }
//        catch (SQLException e) { e.printStackTrace(); }
        //test delete
//        try {
//            SolutionEntity solution = solutionDAO.findById(6).get();
//            solutionDAO.deleteById(solution.getId());
//        }
//        catch (SQLException e) { e.printStackTrace(); }

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
