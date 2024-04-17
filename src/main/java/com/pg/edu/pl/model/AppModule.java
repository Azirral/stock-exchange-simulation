package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.Quote;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppModule {


    public static void printMainMenu() {
        System.out.println("Welcome to Stock Master - Your Ultimate Stock Simulation Experience!\n");
        System.out.println("Main Menu:\n");
        System.out.println("1. Logging in");
        System.out.println("2. List stocks");
        System.out.println("3. Show UserProfile");
        System.out.println("4. Register new Account");
        System.out.println("5. Exit");
    }
    public static void runApplication() {
        Accounts accounts = new Accounts(new ArrayList<UserProfile>());
        UserProfile user1 = new UserProfile(ProfileColor.AZURE.getProfileColor(), "Jan", "Ludwicki", "janekludwicki",
                "qwerty", "halo@wp.pl");
        accounts.getUsers().add(user1);
        Quote quote1 = new Quote(-0.17, -6.29,
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
                        accounts.logIn();
                        break;
                    case 2:
                        System.out.println(quote1);
                        break;
                    case 3:
                        System.out.println(user1);
                        break;
                    case 4:
                        accounts.register();
                        break;
                    case 5:
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