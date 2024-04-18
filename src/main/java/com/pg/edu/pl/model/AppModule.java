package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;

import java.util.*;

public class AppModule {

    public static void printMainMenu() {
        System.out.println("Welcome to Stock Master - Your Ultimate Stock Simulation Experience!\n");
        System.out.println("Main Menu:\n");
        System.out.println("1. Logging in");
        System.out.println("2. Register new Account");
        System.out.println("3. List stocks");
        System.out.println("4. Show UserProfile");
        System.out.println("5. Purchase");
        System.out.println("6. Sell");
        System.out.println("7. Change name");
        System.out.println("8. Save profile");
        System.out.println("9. Print users");
        System.out.println("10. Add 10 credits to the user's wallet");
        System.out.println("11. Print wallets");
        System.out.println("12. Exit");
    }



    public static void runMenu() {
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
                        System.out.println(quote.toString());
                        break;
                    case 4:
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 5:
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        if(accounts.getUsers().get(0).getWallet().getTransactionsHistory()!=null)
                            System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        Purchase p = Purchase.builder()
                                .equityHolding(quote)
                                .amount(3.0)
                                .timestamp((long)10)
                                .wallet(accounts.getUsers().get(0).getWallet())
                                .build();
                        p.performTransaction(symbol);
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        break;
                    case 6:
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        Sell s = Sell.builder()
                                .equityHolding(quote)
                                .amount(2.0)
                                .timestamp((long)11)
                                .wallet(accounts.getUsers().get(0).getWallet())
                                .build();
                        s.performTransaction(symbol);
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        break;
                    case 7:
                        accounts.getUsers().get(0).setName("Monika");
                        break;
                    case 8:
                        user = accounts.getUsers().get(0).clone();
                        break;
                    case 9:
                        System.out.println(accounts.getUsers().get(0));
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 10:
                        user.getWallet().setCredit(10.0);
                        System.out.println("Credits were added successfully");
                    case 11:
                        System.out.println("user1 wallet: " + accounts.getUsers().get(0).getWallet().getCredit());
                        System.out.println("user2 wallet: " + user.getWallet().getCredit());
                        break;
                    case 12:
                        System.out.println("Exiting Stock Master. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
        } catch(NoSuchElementException e){
            System.out.println("Input not available. Please provide valid input.");
            scanner.next();
        } finally{
            scanner.close();
        }
    }
    public static void runApplication() {
        runMenu();
        }
}