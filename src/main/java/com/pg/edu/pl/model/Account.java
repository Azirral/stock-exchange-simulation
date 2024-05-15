package com.pg.edu.pl.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Account class  is an abstract class that holds variables needed to log into
 * an account and are used in UserProfile class
 * */
@Setter(AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Account implements Serializable {
    private UUID uuid;
    private String email;
    /** Username used to log into the user's account */
    private String login;
    private String password;

    /**
     * Override toString method to print the object
     * */
    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public Account logInTCP(BufferedWriter out, ObjectInputStream inObject) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Stock Master - Login");

        System.out.print("Enter username: ");
        try {
            String enteredUsername = scanner.nextLine();

            out.write(enteredUsername);

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            out.write(enteredPassword);
            Account user = (Account) inObject.readObject();
            if (user == null) {
                System.out.println("Invalid username or password. Please try again.");
                logInTCP(out, inObject);
            }
            System.out.println("You were successfully logged in!");
            return user;

        } catch (NoSuchElementException e) {
            System.out.println("Input not available. Please provide valid input.");
            // Consume the invalid input to prevent an infinite loop
            scanner.next();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
