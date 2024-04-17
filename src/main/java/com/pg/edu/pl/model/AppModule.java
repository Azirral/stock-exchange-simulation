package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        System.out.println("9. Exit");
    }
    public static void runApplication() {
        Accounts accounts = new Accounts(new ArrayList<UserProfile>());
        UserProfile user1 = new UserProfile(ProfileColor.AZURE.getProfileColor()
                , "Jan", "Ludwicki", "janekludwicki",
                "qwerty", "halo@wp.pl");
        UserProfile user2 = null;
        accounts.getUsers().add(user1);
        Quote quote = Quote.builder()
                .price(145.775)
                .changesPercentage(0.32)
                .change(0.465)
                .dayLow(143.9)
                .dayHigh(146.71)
                .yearHigh(179.61)
                .yearLow(124.17)
                .marketCap(2306437439846L)
                .priceAvg50(140.8724)
                .priceAvg200(147.18594)
                .volume(42478176L)
                .avgVolume(73638864L)
                .open(144.38)
                .previousClose(145.31)
                .eps(5.89)
                .pe(24.75)
                .earningsAnnouncement("2023-04-26T10:59:00.000+0000")
                .sharesOutstanding(15821899776L)
                .timestamp(1677790773L)
                .stock(null) // Set the Stock object
                .build();
        Quotes quotes = Quotes.builder().quote(quote).build();
        Stock symbol1 = Stock.builder()
                .symbol("AAACX")
                .name("American Beacon Balanced Fund R5 Class")
                .stockExchange("NASDAQ")
                .quotes(quotes)
                .build();
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
                        accounts.register();
                        accounts.getUsers().get(1).setWallet(new Wallet(10.0, 10.0,
                                10.0, null, null));
                        accounts.getUsers().get(1).getWallet().setEquitiesOwned();
                        break;
                    case 3:
                        System.out.println(quote.toString());
                        break;
                    case 4:
                        System.out.println(accounts.getUsers().get(1));
                        break;
                    case 5:
                        System.out.println(accounts.getUsers().get(1).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(1).getWallet().getTransactionsHistory().toString());
                        Purchase p = Purchase.builder()
                                .equityHolding(quote)
                                .amount(3.0)
                                .timestamp((long)10)
                                .wallet(user1.getWallet())
                                .build();
                        p.performTransaction(symbol1);
                        System.out.println(accounts.getUsers().get(1).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(1).getWallet().getTransactionsHistory().toString());
                        break;
                    case 6:
                        System.out.println(accounts.getUsers().get(1).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(1).getWallet().getTransactionsHistory().toString());
                        Sell s = Sell.builder()
                                .equityHolding(quote)
                                .amount(2.0)
                                .timestamp((long)11)
                                .wallet(user1.getWallet())
                                .build();
                        s.performTransaction(symbol1);
                        System.out.println(accounts.getUsers().get(1).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(1).getWallet().getTransactionsHistory().toString());
                        break;
                    case 7:
                        accounts.getUsers().get(1).setName("Monika");
                        break;
                    case 8:
                        user2 = accounts.getUsers().get(1).clone();
                        break;
                    case 9:
                        System.out.println(accounts.getUsers().get(1));
                        System.out.println(user2);
                        System.exit(0); // Terminate the program
                        break;
                    case 10:
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