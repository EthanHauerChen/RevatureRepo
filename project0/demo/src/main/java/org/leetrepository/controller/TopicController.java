package org.leetrepository.controller;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.DAO.TopicDAO;
import org.leetrepository.repository.entities.TopicEntity;
import org.leetrepository.service.ProblemService;
import org.leetrepository.service.TopicService;
import org.leetrepository.util.InputHandler;

public class TopicController {
    private static final ProblemService problemService = new ProblemService(new ProblemDAO());
    private static final TopicService topicService = new TopicService(new TopicDAO(), problemService);

    public TopicController() {}

    public void handleInput() {
        boolean running = true;
        while(running){
            printMainMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addTopic();
//                case 2 -> edit();
//                case 3 -> getProblem();
                case 0 -> {
                    System.out.println("Leaving Topic Menu");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void addTopic() {
        //get input
        String name = ("What is the name of the topic?");
        if (name.equals("")) {
            System.out.println("invalid input");
            return;
        }

        //attempt creation
        TopicEntity topicEntity = new TopicEntity(name);
        int topicId = topicService.createEntity(topicEntity);

        //return success/error
        if (topicId != -1) {
            System.out.println("Successfully created problem: " + topicId + " " + name);
        }
        else {
            System.out.println("Invalid input, try again");
        }
    }

    private void printMainMenu() {
        System.out.println("1. Add Topic");
        System.out.println("2. Edit a topic name");
        System.out.println("3. View all topics");
        System.out.println("0. Exit");
    }
}
