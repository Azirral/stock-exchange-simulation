package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class AppModule {
    private Stocks stocks;
    private Cryptos cryptos;
    private Quotes bajaj_auto;
    private Quotes cipla;
    private Quotes kotakbank;
    private Quotes yesbank;
    private Quotes siemens;
    private UserProfile user;
    private Accounts accounts;
    private void dataInit() {
        CSVLoader csvLoader = new CSVLoader();
        this.accounts = new Accounts(new ArrayList<>());
        this.user = null;
        try {
            this.stocks = csvLoader.loadStocksFromCSV("Stock.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.cryptos = csvLoader.loadCryptosFromCSV("Crypto.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.bajaj_auto = csvLoader.loadQuotesFromCSV("BAJAJ-AUTO_minute_data_with_indicators.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.cipla = csvLoader.loadQuotesFromCSV("CIPLA_minute_data_with_indicators.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.kotakbank = csvLoader.loadQuotesFromCSV("KOTAKBANK_minute_data_with_indicators.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.yesbank = csvLoader.loadQuotesFromCSV("YESBANK_minute_data_with_indicators.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.siemens = csvLoader.loadQuotesFromCSV("SIEMENS_minute_data_with_indicators.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //find stock by symbol in stocks and set it to symbol
        for (Stock stock : stocks.getStocks()) {
            //here it should overwrite the stocks list with the changed stock
            if (stock.getSymbol().equals("BAJAJ-AUTO.NS")) {
                stock.setQuotes(bajaj_auto);
                stocks.getStocks().set(stocks.getStocks().indexOf(stock), stock);
            } else if (stock.getSymbol().equals("CIPLA.NS")) {
                stock.setQuotes(cipla);
                stocks.getStocks().set(stocks.getStocks().indexOf(stock), stock);
            } else if (stock.getSymbol().equals("KOTAKBANK.BO")) {
                stock.setQuotes(kotakbank);
                stocks.getStocks().set(stocks.getStocks().indexOf(stock), stock);
            } else if (stock.getSymbol().equals("YESBANK.NS")) {
                stock.setQuotes(yesbank);
                stocks.getStocks().set(stocks.getStocks().indexOf(stock), stock);
            } else if (stock.getSymbol().equals("SIEMENS.NS")) {
                stock.setQuotes(siemens);
                stocks.getStocks().set(stocks.getStocks().indexOf(stock), stock);
            }
        }
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
                        for (Stock stock : stocks.getStocks()) {
                            if (stock.getQuotes() != null)
                                System.out.println(stock);
                        }
                        break;
                    case 4:
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 7:
                        accounts.getUsers().get(0).setName("Monika");
                        break;
                    case 8:
                        user = accounts.getUsers().get(0).clone();
                        break;
                    case 9:
                        System.out.println(user);
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 10:
                        System.out.println("user1 wallet: " + accounts.getUsers().get(0).getWallet().getCredit());
                        System.out.println("user2 wallet: " + user.getWallet().getCredit());
                        break;
                    case 11:
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
        System.out.println("5. Purchase");
        System.out.println("6. Sell");
        System.out.println("7. Change name");
        System.out.println("8. Save profile");
        System.out.println("9. Print users");
        System.out.println("10. Print wallets");
        System.out.println("11. Exit");
    }

    public void runApplication() {
            dataInit();
            runMenu();
        }
    }