package org.leetrepository.controller;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.util.InputHandler;

public class ProblemController {
    private static final ProblemDAO problemDAO = new ProblemDAO();
    private static final ProblemService problemService = new ProblemService(problemDAO);
    private SolutionController solutionController;
    private TopicController topicController;

    public ProblemController() {}
    public ProblemController(SolutionController solutionController, TopicController topicController) {
        this.solutionController = new SolutionController();
        this.topicController = new TopicController();
    }

    public void handleInput() {
        boolean running = true;
        while(running){
            printMainMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addProblem();
                case 0 -> {
                    System.out.println("Leaving Department Services");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
    private void printMainMenu() {
        System.out.println("=== Problem Menu ===");
        System.out.println("1. Add a new LeetCode problem");
        System.out.println("2. Edit an existing LeetCode problem");
        System.out.println("\tadd/remove a solution, add/remove a topic");
    }

    private void addProblem() {
        //get input
        int id = InputHandler.getIntInput("What is the number of the LeetCode problem?");
        if (id < 0) {
            System.out.println("Please enter a valid number");
            return;
        }
        String name = InputHandler.getStringInput("Enter the name of the LeetCode problem");
        if (name.equals("")) {
            printNotEmpty("name");
            return;
        }
        String description = InputHandler.getStringInput("Enter the description of the LeetCode problem");
        if (description.equals("")) {
            System.out.println("No description given. Description will default to \"description\"");
        }
        String difficulty = InputHandler.getStringInput("Enter the difficulty. Must be one of the following {Easy, Medium, Hard, Unspecified}");
        if (difficulty.equals("")) {
            difficulty = "Unspecified";
        }
        String url = InputHandler.getStringInput("Enter the url to the LeetCode problem");
        if (url.equals("")) {
            url = "https://leetcode.com/problemset/";
        }

        //attempt creation
        ProblemEntity problemEntity = new ProblemEntity(id, name, description, difficulty, url);
        int problemId = problemService.createEntity(problemEntity);

        //return success/error
        if (problemId != -1) {
            System.out.println("Successfully created problem: " + problemId + " " + name);
        }
        else {
            System.out.println("Invalid input, try again");
        }
    }

    private void printNotEmpty(String input) {
        System.out.println(input + " must not be empty");
    }
}
