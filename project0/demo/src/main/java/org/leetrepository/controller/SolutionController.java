package org.leetrepository.controller;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.SolutionDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.repository.entities.SolutionEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.SolutionService;
import org.leetrepository.service.model.Solution;
import org.leetrepository.util.InputHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class SolutionController {
    private static final SolutionDAO solutionDAO = new SolutionDAO();
    private static final ProblemService problemService = new ProblemService(new ProblemDAO());
    private static final SolutionService solutionService = new SolutionService(solutionDAO, problemService);
    private ProblemController problemController;
    private TopicController topicController;

    public SolutionController() {}
    public SolutionController(ProblemController problemController, TopicController topicController) {
        this.problemController = new ProblemController();
        this.topicController = new TopicController();
    }

    public void handleInput() {
        boolean running = true;
        while(running){
            printMainMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addSolution();
                case 2 -> edit();
//                case 3 -> getProblem();
                case 0 -> {
                    System.out.println("Leaving Problem Menu");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void edit() {
        Optional<Solution> solution = Optional.empty();
        //get input
        int problemId = InputHandler.getIntInput("What is the number of the LeetCode problem that the solution belongs to?");
        if (problemId < 0) {
            System.out.println("Please enter a valid number");
            return;
        }
        if (problemService.getEntityById(problemId).isEmpty()) {
            System.out.println("problem not found");
            return;
        }
        String name = InputHandler.getStringInput("Enter the name of the Solution you are trying to edit");
        if (name.equals("")) {
            System.out.println("invalid input");
            return;
        }
        solution = solutionService.getSolutionByNameGivenProblemId(name, problemId);
        if (solution.isEmpty()) {
            System.out.println("Solution not fonud");
            return;
        }
        String description = InputHandler.getStringInput("Enter the description of the LeetCode problem. Leave blank if you don't want to edit this field");
        if (description.equals("")) {
            description = solution.get().getDescription();
        }
        String code = InputHandler.getMultiLineInput("Enter the code for the Solution. . Leave blank if you don't want to edit this field. Type END on a new line to stop");
        ByteBuffer codeBytes;
        if (code.equals("")) {
            codeBytes = solution.get().getSolutionCode();
        }
        else {
            codeBytes = ByteBuffer.wrap(code.getBytes(StandardCharsets.UTF_8));
        }

        //attempt creation
        SolutionEntity solutionEntity = new SolutionEntity(problemId, name, description, codeBytes);
        solutionEntity = solutionService.updateEntity(solutionEntity);

        //return success/error
        if (solutionEntity != null) {
            System.out.println("Successfully edited Solution for problem " + problemId + ", " + name);
        }
        else {
            System.out.println("Invalid input, try again");
        }
    }

    private void addSolution() {
        //get input
        int problemId = InputHandler.getIntInput("What is the number of the LeetCode problem that the solution belongs to?");
        if (problemId < 0) {
            System.out.println("Please enter a valid number");
            return;
        }
        if (solutionService.getEntityById(problemId).isEmpty()) {
            System.out.println("problem not found");
            return;
        }
        String name = InputHandler.getStringInput("Enter the name of the Solution");
        if (name.equals("")) {
            name = "name";
        }
        String description = InputHandler.getStringInput("Enter the description of the LeetCode problem");
        if (description.equals("")) {
            System.out.println("No description given. Description will default to \"description\"");
            description = "description";
        }
        String code = InputHandler.getMultiLineInput("Enter the code for the Solution. Type END on a new line to stop");
        if (code.equals("")) {
            code = "solution";
        }
        ByteBuffer codeBytes = ByteBuffer.wrap(code.getBytes(StandardCharsets.UTF_8));

        //attempt creation
        SolutionEntity solutionEntity = new SolutionEntity(problemId, name, description, codeBytes);
        int solutionId = solutionService.createEntity(solutionEntity);

        //return success/error
        if (solutionId != -1) {
            System.out.println("Successfully created Solution for problem " + problemId + ", " + name);
        }
        else {
            System.out.println("Invalid input, try again");
        }
    }

    private void printMainMenu() {
        System.out.println("1. Add a solution");
        System.out.println("2. Edit a solution");
        System.out.println("3. View a solution");
        System.out.println("0. Exit");
    }

    private void printNotEmpty(String input) {
        System.out.println(input + " must not be empty");
    }
}
