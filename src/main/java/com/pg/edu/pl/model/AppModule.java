package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import com.pg.edu.pl.model.prediction.StockPrediction;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class AppModule {
    private static Stocks stocks;
    private Cryptos cryptos;
    private Quotes bajaj_auto;
    private Quotes cipla;
    private Quotes kotakbank;
    private Quotes yesbank;
    private Quotes siemens;
    private CryptoQuotes cryptoQuotes;
    private UserProfile user;
    private Accounts accounts;

    private void dataInit() throws IOException{
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
        this.accounts = FileHandler.loadAccountsFromCSV("users.csv", null, bajaj_auto, cryptos, stocks);
        executor.shutdown();
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
                        long start = System.currentTimeMillis();
                        for (Stock stock : stocks.getStocks()) {
                            if (stock.getQuotes() != null) {
                                StockPrediction predictor = new StockPrediction("Stock Price Prediction",
                                        "Using regression analysis", null, null, new Date(), stock);
                                    predictor.predict_linear();
                                    predictor.predict_polynomial();
                            }
                        }
                        long end = System.currentTimeMillis();
                        long duration = end-start;
                        System.out.println(duration);
                        break;
                    case 9:
                        threading(2);
                        break;
                    case 10:
                        threading(3);
                        break;
                    case 11:
                        threading(4);
                        break;
                    case 12:
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
        System.out.println("8. Print wallets");
        System.out.println("8. Sequential prediction");
        System.out.println("9. parallel prediction on 2 threads");
        System.out.println("10. parallel prediction on 3 threads");
        System.out.println("11. parallel prediction on 4 threads");
        System.out.println("12. Exit");
    }

    public static void threading(int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        long start_ = System.currentTimeMillis();
        for (Stock stock : stocks.getStocks()) {
            if (stock.getQuotes() != null) {
                StockPrediction predictor = new StockPrediction("Stock Price Prediction", "Using regression analysis", null, null, new Date(), stock);

                Thread linearPredictionThread = new Thread(() -> {
                    long startThread = System.currentTimeMillis();
                    try {
                        predictor.predict_linear();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    long endThread = System.currentTimeMillis();
                    System.out.println("Linear prediction time: " + (endThread - startThread) + "ms");
                });

                Thread polynomialPredictionThread = new Thread(() -> {
                    long startThread = System.currentTimeMillis();
                    try {
                        predictor.predict_polynomial();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    long endThread = System.currentTimeMillis();
                    System.out.println("Polynomial prediction time: " + (endThread - startThread) + "ms");
                });

                /** Start the threads*/
                linearPredictionThread.start();
                polynomialPredictionThread.start();

                /** Wait for threads to finish */
                try {
                    linearPredictionThread.join();
                    polynomialPredictionThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        long end_ = System.currentTimeMillis();
        long duration_ = end_-start_;
        System.out.println(duration_);
        executor.shutdown();
    }

    public void runApplication() throws IOException {
            dataInit();
            runMenu();
        }
    }