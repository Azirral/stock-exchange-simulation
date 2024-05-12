package com.pg.edu.pl.classes;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppModule {

    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Stock Master - Login");

        System.out.print("Enter username: ");
        try {
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (enteredUsername.equals("janekludwicki") && enteredPassword.equals("qwerty")) {
                System.out.println("Login successful!");
                // Call your main menu function or perform further actions after successful login
            } else {
                System.out.println("Invalid username or password. Please try again.");
                // You might want to add a loop here to allow the user to retry or exit gracefully
            }
        } catch(NoSuchElementException e){
            System.out.println("Input not available. Please provide valid input.");
            // Consume the invalid input to prevent an infinite loop
            scanner.next();
        }
    }
    public static void printMainMenu() {
        System.out.println("Welcome to Stock Master - Your Ultimate Stock Simulation Experience!\n");
        System.out.println("Main Menu:\n");
        System.out.println("1. Logging in");
        System.out.println("2. List stocks");
        System.out.println("3. Show UserProfile");
        System.out.println("4. Exit");
    }
    public static void runApplication() {
        UserProfile user1 = new UserProfile(Color.red, "Jan", "Ludwicki", "janekludwicki",
                "qwerty", "halo@wp.pl");
        Stock stock1 = new Stock("NASDAQ", -0.17, -6.29,
                "2024-05-09T20:00:00.000+0000", "AAACX",
                "American Beacon Balanced Fund R5 Class", 6.22, 0.0,
                0.0, 6.22, 6.22, 6.55, 6.14, (long) 0,
                6.237, 6.24775, (long) 0, (long) 0, 6.22, 6.22,
                (long) 0, (long) 0);

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                printMainMenu();
                System.out.print("\nEnter your choice: ");
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    // Handle case where only Enter key was pressed
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    continue; // Restart the loop
                }
                int choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        System.out.println(stock1);
                        break;
                    case 3:
                        System.out.println(user1);
                        break;
                    case 4:
                        System.out.println("Exiting Stock Master. Goodbye!");
                        System.exit(0); // Terminate the program
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
        } catch(NoSuchElementException e){
                System.out.println("Input not available. Please provide valid input.");
                // Consume the invalid input to prevent an infinite loop
                scanner.next();
            } finally{
                scanner.close(); // Close the scanner after the loop
            }
        }
}