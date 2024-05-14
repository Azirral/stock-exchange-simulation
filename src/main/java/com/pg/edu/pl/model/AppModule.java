package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import com.pg.edu.pl.model.prediction.StockPrediction;
import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the main module of the application.
 */
@Getter
@Setter
public class AppModule {
    /** Collection of stock entities */
    private static Stocks stocks;
    /** Collection of cryptocurrency entities */
    private Cryptos cryptos;
    /** Quotes for Bajaj Auto */
    private Quotes bajaj_auto;
    /** Quotes for Cipla */
    private Quotes cipla;
    /** Quotes for Kotak Bank */
    private Quotes kotakbank;
    /** Quotes for Yes Bank */
    private Quotes yesbank;
    /** Quotes for Siemens */
    private Quotes siemens;
    /** Collection of cryptocurrency quotes */
    private CryptoQuotes cryptoQuotes;
    /** User profile */
    private UserProfile user;
    /** User accounts */
    private Accounts accounts;

    /**
     * Initializes the data required for the application to run.
     *
     * @throws IOException if an I/O error occurs
     */
    private void dataInit() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CSVLoader csvLoader = new CSVLoader();
        this.cryptoQuotes = CryptoQuotes.builder().cryptoQuotes(new ArrayList<>()).build();
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
        for (Stock stock : stocks.getStocks()) {
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
//        this.accounts = FileHandler.loadAccountsFromCSV("users.csv", null, bajaj_auto, cryptos, stocks);
        executor.shutdown();
    }

    /**
     * Runs the main menu of the application.
     */
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
                    case 5:
                        accounts.getUsers().get(0).setName("Monika");
                        break;
                    case 6:
                        System.out.println(accounts.getUsers().get(0));
                        break;
                    case 7:
                        System.out.println("user1 wallet: " + accounts.getUsers().get(0).getWallet().getCredit());
                        break;
                    case 8:
                        try {
                            FileWriter writer = new FileWriter("durations.csv");

                            /** Write header to CSV file */
                            writer.write("Iteration,Duration (ms)\n");

                            for (int i = 0; i < 100; i++) {
                                long start = System.currentTimeMillis();
                                for (Stock stock : stocks.getStocks()) {
                                    if (stock.getQuotes() != null) {
                                        StockPrediction predictor = new StockPrediction("Stock Price Prediction",
                                                "Using regression analysis", null, null, new Date(), stock);
                                        predictor.predictLinear();
                                    }
                                }
                                long end = System.currentTimeMillis();
                                long duration = end - start;
                                System.out.println("Duration " + i + ": " + duration);

                                /** Write duration to CSV file */
                                writer.write(i + "," + duration + "\n");
                            }

                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (int n = 1; n < 7; n++) {
                            try {
                                FileWriter writer = new FileWriter("durations_on_" + n + "threads" + ".csv");

                                /** Write header to CSV file */
                                writer.write("Iteration,Duration (ms)\n");

                                for (int i = 1; i < 100; i++) {
                                    long start = System.currentTimeMillis();
                                    threading(n);
                                    long end = System.currentTimeMillis();
                                    long duration = end - start;
                                    System.out.println("Duration " + i + ": " + duration);

                                    /** Write duration to CSV file */
                                    writer.write(i + "," + duration + "\n");
                                }

                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 9:
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
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    /**
     * Prints the main menu of the application.
     */
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
        System.out.println("8. Test multithreading");
        System.out.println("9. Exit");
    }

    /**
     * Perform multi-threaded stock predictions.
     *
     * @param threads Number of threads to use
     */
    public static void threading(int threads) {
        for (Stock stock : stocks.getStocks()) {
            if (stock.getQuotes() != null) {
                StockPrediction predictor = new StockPrediction("Stock Price Prediction", "Using regression analysis", null, null, new Date(), stock);

                Thread linearPredictionThread = new Thread(() -> {
                    long startThread = System.currentTimeMillis();
                    try {
                        predictor.predictLinearMultiThreads(threads);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    long endThread = System.currentTimeMillis();
                    System.out.println("Linear prediction time: " + (endThread - startThread) + "ms");
                });

                /** Start the threads*/
                linearPredictionThread.start();
                /** Wait for threads to finish */
                try {
                    linearPredictionThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Runs the application.
     *
     * @throws IOException if an I/O error occurs
     */
    public void runApplication() throws IOException {
        dataInit();
        runMenu();
    }
}
