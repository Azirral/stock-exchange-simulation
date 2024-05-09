package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class AppModule {
    private Quotes quotes;
    private CryptoQuotes cryptoQuotes;
    private Cryptos cryptos;
    private Stocks stocks;
    private Accounts accounts;
    private Symbol symbol;

    private void dataInit() throws IOException {
        this.quotes = Quotes.builder().quotes(new ArrayList<>()).build();
        this.cryptoQuotes = CryptoQuotes.builder().cryptoQuotes(new ArrayList<>()).build();
        this.cryptos = Cryptos.builder().cryptos(new ArrayList<>()).build();
        this.stocks = Stocks.builder().stocks(new ArrayList<>()).build();
        //this.accounts = Accounts.builder().users(new ArrayList<>()).build();
        this.accounts = FileHandler.loadAccountsFromCSV("users.csv", cryptoQuotes, quotes, cryptos, stocks);


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
                        //System.out.println(quote.toString());
                        break;
                    case 4:
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 5:
                    /*    System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        if (accounts.getUsers().get(0).getWallet().getTransactionsHistory() != null)
                            System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        Purchase p = Purchase.builder()
                                .equityHolding(quote)
                                .amount(3.0)
                                .timestamp((long) 10)
                                .wallet(accounts.getUsers().get(0).getWallet())
                                .build();
                        p.performTransaction(symbol);
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        break;*/
                    case 6:
                        /*System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                        Sell s = Sell.builder()
                                .equityHolding(quote)
                                .amount(2.0)
                                .timestamp((long) 11)
                                .wallet(accounts.getUsers().get(0).getWallet())
                                .build();
                        s.performTransaction(symbol);
                        System.out.println(accounts.getUsers().get(0).getWallet().getEquitiesOwned().toString());
                        System.out.println(accounts.getUsers().get(0).getWallet().getTransactionsHistory().toString());
                       */ break;
                    case 7:
                        accounts.getUsers().get(0).setName("Monika");
                        break;
                    case 8:
                        //user = accounts.getUsers().get(0).clone();
                        break;
                    case 9:
                        //System.out.println(user);
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 10:
                        System.out.println("user1 wallet: " + accounts.getUsers().get(0).getWallet().getCredit());
                        //System.out.println("user2 wallet: " + user.getWallet().getCredit());
                        break;
                    case 11:
                        System.out.println("Exiting Stock Master. Goodbye!");
                        FileHandler.saveUserProfile("users.csv", accounts.getUsers());
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
        System.out.println("5. Purchase");
        System.out.println("6. Sell");
        System.out.println("7. Change name");
        System.out.println("8. Save profile");
        System.out.println("9. Print users");
        System.out.println("10. Print wallets");
        System.out.println("11. Exit");
    }

    public void runApplication() throws IOException {
            dataInit();
            runMenu();
        }
    }