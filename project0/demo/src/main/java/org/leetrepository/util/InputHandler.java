package org.leetrepository.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer getIntInput(String prompt){
        while(true){
            System.out.println(prompt);
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid Input. Please enter a number.");
            }
        }
    }

    public static String getStringInput(String prompt){
        while(true){
            System.out.println(prompt);
            try{
                return scanner.nextLine();
            }catch(RuntimeException e){
                System.out.println("Invalid Input. Please enter a String.");
            }
        }
    }

//    public static String getMultiLineInput(String prompt) {
//        List<String> lines = new ArrayList<>();
//        System.out.println("Enter lines of text (type 'END' on a new line to finish):");
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            if (line.equalsIgnoreCase("END")) {
//                break; // Exit the loop when "END" is entered
//            }
//            lines.add(line);
//        }
//
//        System.out.println("\n--- Captured Input ---");
//        for (String capturedLine : lines) {
//            System.out.println(capturedLine);
//        }
//    }
}
