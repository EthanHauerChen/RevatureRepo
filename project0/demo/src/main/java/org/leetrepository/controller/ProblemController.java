package org.leetrepository.controller;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.SolutionService;
import org.leetrepository.service.TopicService;
import org.leetrepository.service.model.Problem;
import org.leetrepository.util.InputHandler;

import java.sql.SQLOutput;
import java.util.Optional;

public class ProblemController {
    private static final ProblemDAO problemDAO = new ProblemDAO();
    private static final ProblemService problemService = new ProblemService(problemDAO);
    private static final TopicService topicService = new TopicService(new TopicDAO(), problemService);
    private static final SolutionService solutionService = new SolutionService(new SolutionDAO(), problemService);
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
                case 2 -> edit();
                case 3 -> getProblem();
                case 0 -> {
                    System.out.println("Leaving Problem Menu");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void getSolutionsGivenProblem() {
        int choice = InputHandler.getIntInput("1. Search by problem number\n2. Search problem by name");
        Optional<Problem> problem = Optional.empty();

        switch (choice) {
            case 1 -> {
                int id = InputHandler.getIntInput("Enter the problem number");
                problem = problemService.getModelById(id);
            }
            case 2 -> {
                String name = InputHandler.getStringInput("Enter the problem name");
                problem = problemService.getModelByName(name);
            }
            default -> {
                System.out.println("Exiting");
                return;
            }
        }

        if (problem.isEmpty()) {
            System.out.println("problem not found");
            return;
        }

        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problem.get().getId());
        System.out.println(solutionService.getSolutionsGivenProblemId(problemEntity.getId()));
    }

    private void getTopicsGivenProblem() {
        int choice = InputHandler.getIntInput("1. Search by problem number\n2. Search problem by name");
        Optional<Problem> problem = Optional.empty();

        switch (choice) {
            case 1 -> {
                int id = InputHandler.getIntInput("Enter the problem number");
                problem = problemService.getModelById(id);
            }
            case 2 -> {
                String name = InputHandler.getStringInput("Enter the problem name");
                problem = problemService.getModelByName(name);
            }
            default -> {
                System.out.println("Exiting");
                return;
            }
        }

        if (problem.isEmpty()) {
            System.out.println("problem not found");
            return;
        }

        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problem.get().getId());
        System.out.println(topicService.getTopicsGivenProblemEntity(problemEntity));
    }

    private void printMainMenu() {
        System.out.println("=== Problem Menu ===");
        System.out.println("1. Add a new LeetCode problem");
        System.out.println("2. Edit an existing LeetCode problem");
//        System.out.println("\tadd/remove a solution, add/remove a topic");
        System.out.println("3. View a LeetCode problem");
        System.out.println("0. Exit");
    }

    private void printEditMenu() {
        System.out.println("=== Edit or Remove ===");
        System.out.println("1. Edit an existing LeetCode problem");
//        System.out.println("2. Add a solution");
//        System.out.println("3. Add a topic(s)");
    }

    private void printNotEmpty(String input) {
        System.out.println(input + " must not be empty");
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

    private void edit() {
        boolean running = true;
        while(running){
            printEditMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> editProblem();
                case 0 -> {
                    System.out.println("Leaving Edit Menu");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void editProblem() {
        Optional<Problem> problem = Optional.empty();
        ProblemEntity problemEntity = new ProblemEntity();
        //get input
        int choice = InputHandler.getIntInput("Enter 1 to search and edit by problem number. Enter 2 to search and edit by problem name");
        if (choice != 1 && choice != 2) {
            System.out.println("Exiting edit-problem menu");
            return;
        }
        int id = -1;
        String name = "";
        switch (choice) {
            case 1 -> {
                id = InputHandler.getIntInput("Enter the number of the LeetCode problem");
                problem = problemService.getModelById(id);
            }
            case 2 -> {
                name = InputHandler.getStringInput("Search the name of the LeetCode problem");
                problem = problemService.getModelByName(name);
            }
        }
        if (problem.isEmpty()) {
            System.out.println("Problem not found");
            return;
        }
        System.out.println("Editing: " + problem.get().getId() + ". " + problem.get().getName());
        problemEntity.setId(problem.get().getId());
        problemEntity.setName(problem.get().getName());
        String description = InputHandler.getStringInput("Enter the description of the LeetCode problem. Leave blank if not editing this field");
        String difficulty = InputHandler.getStringInput("Enter the difficulty. Must be one of the following {Easy, Medium, Hard, Unspecified}. Leave blank if not editing this field");
        String url = InputHandler.getStringInput("Enter the url to the LeetCode problem. Leave blank if not editing this field");
        if (description.equals("")) {
            description = problem.get().getDescription();
        }
        if (difficulty.equals("")) {
            difficulty = problem.get().getDifficulty();
        }
        if (url.equals("")) {
            url = problem.get().getUrl();
        }
        problemEntity.setDescription(description);
        problemEntity.setDifficulty(difficulty);
        problemEntity.setUrl(url);
        problemEntity = problemService.updateEntity(problemEntity);

        //return success/error
        if (problemEntity != null) {
            System.out.println("Successfully updated problem: " + problemEntity.getId() + ". " + name);
        }
        else {
            System.out.println("Invalid input, try again");
        }
    }

    private void getProblem() {
        boolean running = true;
        while(running){
            getMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> getById();
                case 2 -> getByName();
                case 3 -> getTopicsGivenProblem();
                case 4 -> getSolutionsGivenProblem();
                case 0 -> {
                    System.out.println("Exiting search Problem menu");
                    running = false;
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void getMenu() {
        System.out.println("1. Search by problem number");
        System.out.println("2. Search by problem name");
        System.out.println("3. Get all topics for a given problem");
        System.out.println("4. Get all solutions for a given problem");
        System.out.println("0. Exit");
    }

    private void getById() {
        int id = InputHandler.getIntInput("Enter the problem number");
        Optional<Problem> problem = problemService.getModelById(id);
        if (problem.isEmpty()) {
            System.out.println("Problem not found");
            return;
        }
        System.out.println(problem.get().toString());
    }

    private void getByName() {
        String name = InputHandler.getStringInput("Enter the problem name");
        Optional<Problem> problem = problemService.getModelByName(name);
        if (problem.isEmpty()) {
            System.out.println("Problem not found");
            return;
        }
        System.out.println(problem.get().toString());
    }
}
