package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import lombok.*;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Accounts implements Serializable {
    /** List that contains all users that had created their accounts*/
    private ArrayList<UserProfile> users;

    /**
     * Method to register a new user.
     */
    public void register() {
        UserProfile user = registeringProcedure();
        users.add(user);
    }

    /**
     * Method to find a user account based on username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The UserProfile object if found, or null if not found.
     */
    public UserProfile findUserAccount(String username, String password) {
        for (UserProfile userProfile : users) {
            if (userProfile.getLogin().equals(username) && userProfile.getPassword().equals(password))
                return userProfile;
        }
        return null;
    }

    /**
     * Method to handle user login.
     */
    public void logIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Stock Master - Login");

        System.out.print("Enter username: ");
        try {
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (findUserAccount(enteredUsername, enteredPassword) == null) {
                System.out.println("Invalid username or password. Please try again.");
                logIn();
            }
            System.out.println("You were successfully logged in!");

        } catch (NoSuchElementException e) {
            System.out.println("Input not available. Please provide valid input.");
            // Consume the invalid input to prevent an infinite loop
            scanner.next();
        }
    }
    public UserProfile logIn(String enteredUsername, String enteredPassword) {
        if (findUserAccount(enteredUsername, enteredPassword) == null) {
            return null;
        }
        return findUserAccount(enteredUsername, enteredPassword);
    }

    /**
     * Method to handle the registration procedure for a new user.
     * @return The newly created UserProfile object.
     */
    private UserProfile registeringProcedure() {
        Color profileBackground;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registering new User");
        System.out.print("Please enter your username: ");
        String login = scanner.nextLine();
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        System.out.print("Please enter your Name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter your Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Please choose your profile color (Optional) - choose number 1-12:");
        System.out.println(
                "1." + ProfileColor.ORANGE.toString() + "\n" +
                        "2." + ProfileColor.YELLOW.toString() + "\n" +
                        "3." + ProfileColor.CHARTREUSE_GREEN.toString() + "\n" +
                        "4." + ProfileColor.GREEN.toString() + "\n" +
                        "5." + ProfileColor.SPRING_GREEN.toString() + "\n" +
                        "6." + ProfileColor.CYAN.toString() + "\n" +
                        "7." + ProfileColor.AZURE.toString() + "\n" +
                        "8." + ProfileColor.BLUE.toString() + "\n" +
                        "9." + ProfileColor.VIOLET.toString() + "\n" +
                        "10." + ProfileColor.MAGENTA.toString() + "\n" +
                        "11." + ProfileColor.ROSE.toString() + "\n" +
                        "12." + ProfileColor.RED.toString());
        String colorNumber = scanner.nextLine();
        Integer colourNumber = Integer.parseInt(colorNumber);
        switch (colourNumber) {
            default:
                profileBackground = null;
                break;
            case 1:
                profileBackground = ProfileColor.ORANGE.getProfileColor();
                break;
            case 2:
                profileBackground = ProfileColor.YELLOW.getProfileColor();
                break;
            case 3:
                profileBackground = ProfileColor.CHARTREUSE_GREEN.getProfileColor();
                break;
            case 4:
                profileBackground = ProfileColor.GREEN.getProfileColor();
                break;
            case 5:
                profileBackground = ProfileColor.SPRING_GREEN.getProfileColor();
                break;
            case 6:
                profileBackground = ProfileColor.CYAN.getProfileColor();
                break;
            case 7:
                profileBackground = ProfileColor.AZURE.getProfileColor();
                break;
            case 8:
                profileBackground = ProfileColor.BLUE.getProfileColor();
                break;
            case 9:
                profileBackground = ProfileColor.VIOLET.getProfileColor();
                break;
            case 10:
                profileBackground = ProfileColor.MAGENTA.getProfileColor();
                break;
            case 11:
                profileBackground = ProfileColor.ROSE.getProfileColor();
                break;
            case 12:
                profileBackground = ProfileColor.RED.getProfileColor();
                break;
        }
        Wallet wallet = Wallet.builder()
                .uuid(UUID.randomUUID())
                .credit(100.0)
                .exchangeValue(10.0)
                .lastSaleValue(10.0)
                .equitiesOwned(new HashMap<>())
                .transactionsHistory(new ArrayList<>())
                .build();
        if (profileBackground == null)
            return new UserProfile(wallet, name, surname, login, password, email);
        return new UserProfile(wallet, profileBackground, name, surname, login, password, email);
    }
}
