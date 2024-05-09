package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class AppModuleAdmin {
    private Quote quote;
    private Quotes quotes;
    private Stock symbol;
    private UserProfile user;
    private Accounts accounts;
    private void dataInit() {
        this.accounts = new Accounts(new ArrayList<UserProfile>());
        this.user = null;
        this.quote = null;
        this.quotes = Quotes.builder().quote(this.quote).build();
        this.symbol = Stock.builder()
                .symbol("AAACX")
                .name("American Beacon Balanced Fund R5 Class")
                .stockExchange("NASDAQ")
                .quotes(quotes)
                .build();
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                printMainMenu();
                System.out.print("\nEnter your choice: ");
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    continue;
                }
                int choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1:
                        accounts.logIn();
                        break;
                    case 2:
                        accounts.register();
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 5:
                        accounts.getUsers().get(0).setName("Monika");
                        break;
                    case 6:
                        user = accounts.getUsers().get(0).clone();
                        break;
                    case 7:
                        System.out.println(accounts.getUsers().get(0));
                        System.out.println(accounts.getUsers().get(1));
                        break;
                    case 8:
                        Collections.sort(accounts.getUsers());
                        break;
                    case 9:
                        System.out.println("Exiting Stock Master. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input not available. Please provide valid input.");
            scanner.next();
        } finally {
            scanner.close();
        }
    }
    public static void printMainMenu() {
        System.out.println("Welcome to Stock Master - Your Ultimate Stock Simulation Experience!\n");
        System.out.println("Main Menu:\n");
        System.out.println("1. Logging in");
        System.out.println("2. Register new Account");
        System.out.println("3. List stocks");
        System.out.println("4. Show UserProfile");
        System.out.println("5. Change name");
        System.out.println("6. Save profile");
        System.out.println("7. Print users");
        System.out.println("8. Sort users");
        System.out.println("9. Exit");
    }

    public void runApplication() {
        dataInit();
        runMenu();
    }
}