package com.example;

import java.util.Scanner;

public class ConsoleScannerExample {
    public static void main(String[] args) {
        //scanner reading from stdin
        Scanner kb = new Scanner(System.in);

        System.out.println("Enter age: ");
        int age = kb.nextInt();

        //won't scan city because the newline entered from entering age will be consumed kb.nextLine below
        System.out.println("Enter your city: ");
        String city = kb.nextLine();

        System.out.println(city);


        /** instead need to add a kb.nextLine() to consume the newline character */
        System.out.println("Enter age: ");
        age = kb.nextInt();
        kb.nextLine();

        //won't scan city because the newline entered from entering age will be consumed kb.nextLine below
        System.out.println("Enter your city: ");
        city = kb.nextLine();

        System.out.println(city);
    }
}