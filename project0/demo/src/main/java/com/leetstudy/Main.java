package com.leetstudy;

/**
 * MAIN IDEA:
 * some sort of app that tracks and logs:
 * completed leetcode solutions
 * info about the leetcode problems like
 *      description
 *      difficulty
 *      topics
 * completed solutions contain
 *      completed code
 *      could have multiple solutions per solution "object"
 * maybe entertain the idea of multiple users
 */

public class Main {
    HashMap<Integer, Problem> problems; //how should it be ID
    public static void main(String[] args) {
        showMenu();
    }

    private void showMenu() {
        //user selects add leetcode problem
        Problem p = new Problem(user given input);

        //user selects upload solution, should provide file path
        ask if relative path or absolute path
        if ()
    }
}