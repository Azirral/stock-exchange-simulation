package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FileHandler {

    /**
     * Saves list of all users profiles in the app and their wallets into a text file
     * @param filepath the path of the file the info will be saved to
     * @param userProfiles list of all userProfiles created in the app
     */
    public static void saveUserProfile(String filepath, ArrayList<UserProfile> userProfiles) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath));
            for (UserProfile userProfile : userProfiles) {
                bufferedWriter.write(userProfile.getWallet().toCSV());
                bufferedWriter.write(userProfile.toCSV() + "\n");
            }
            bufferedWriter.close();
        }
        catch (Exception e) {

        }
    }

    /**
     * Loads all previously saved userProfile from the file. The data was saved in csv with different delimiters to
     * distinguish the attributes and the levels they are in.
     * @param filePath the path of the file the info will be saved to
     * @param cryptoQuotes an Object of type CryptoQuotes which mainly contains an attribute list of all objects type
     *                     CryptoQuote. It is need it to assign the loaded object type Transaction to particular
     *                     CryptoQuote.
     * @param quotes an Object of type Quotes which contains an attribute list of all objects type
     *                     Quote. It is need it to assign the loaded object type Transaction to particular Quote.
     * @param cryptos an Object of type Cryptos which contains an attribute list of all objects type
     *                     Crypto. It is used to be able to find particular Crypto with given symbol and load a hashMap.
     * @param stocks an Object of type Stocks which contains an attribute list of all objects type
     *                     Stock. It is used to be able to find particular Crypto with given symbol and load a hashMap.
     * @return An object type Accounts with loaded UserProfile class list and all its attributes/objects connected to
     * the user such like wallet and transactions
     * */
    public static Accounts loadAccountsFromCSV(String filePath, CryptoQuotes cryptoQuotes, Quotes quotes, Cryptos cryptos,
                                        Stocks stocks) throws IOException {
        ArrayList<UserProfile> userProfiles = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            HashMap<Symbol, Double> equitiesOwned = loadEquitiesHashMap(values[1].split("&"),cryptos , stocks);
            ArrayList<Transaction> transactionsHistory = new ArrayList<>();
            Wallet wallet = new Wallet(UUID.fromString(values[2]), Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]), Double.parseDouble(values[5]), equitiesOwned, transactionsHistory);
            loadTransactionsList(values[0].split("#"), cryptoQuotes, quotes, wallet, transactionsHistory);
            UserProfile userProfile = new UserProfile(wallet, new Color(Integer.parseInt(values[6]), Integer.
                    parseInt(values[7]), Integer.parseInt(values[8])), values[9], values[10],
                    values[11], values[12], values[13], UUID.fromString(values[14]));
            userProfiles.add(userProfile);
            }
        br.close();
        return Accounts.builder().users(userProfiles).build();
    }


    /**
     * Looks for Crypto with the provided symbol in cryptos list.
     * @param equities Array of type string which contain three elements. 1st one is a flag to distinguish crypto from
     *                 stocks. 2nd is Stock/Crypto symbol and 3rd is quantity of equity owned.
     * @param cryptos an Object of type Cryptos which contains an attribute list of all objects type
     *                     Crypto. It is used to be able to find particular Crypto with given symbol and load a hashMap.
     * @param stocks an Object of type Stocks which contains an attribute list of all objects type
     *                     Stock. It is used to be able to find particular Crypto with given symbol and load a hashMap.
     * @return HashMap of all equities owned by the user that was read from the file
     * */
    private static HashMap<Symbol,Double> loadEquitiesHashMap(String[] equities, Cryptos cryptos, Stocks stocks) {
        HashMap<Symbol,Double> equitiesOwned = new HashMap<>();
        if (equities[0].isEmpty())
            return null;
        for (String equity : equities) {
            String[] map = equity.split(":");
            if (map[0].equals("c")) {
                equitiesOwned.put(cryptos.findCrypto(map[1]), Double.parseDouble(map[2]));
            } else {
                equitiesOwned.put(stocks.findStock(map[1]), Double.parseDouble(map[2]));
            }
        }
        return equitiesOwned;
    }

    /**
     * Loads all transactions of particular user from the file to the List and objects.
     * @param transactions Array of type string which contain 6 elements. 1st one is a flag to distinguish crypto from
     *                 stocks. The following ones are the attributes of a transaction.
     * @param cryptoQuotes An Object of type CryptoQuotes which mainly contains an attribute list of all objects type
     *                     CryptoQuote. It is need it to assign the loaded object type Transaction to particular
     *                     CryptoQuote.
     * @param quotes An Object of type Quotes which contains an attribute list of all objects type
     *                     Quote. It is need it to assign the loaded object type Transaction to particular Quote.
     * @param wallet An object of class Wallet to which the loaded transactions will be assigned to
     * @param transactionsHistory An ArrayList of type Transaction to which the transactions will be added. It is
     *                            assigned to particular user's wallet. */
    private static void loadTransactionsList(String[] transactions, CryptoQuotes cryptoQuotes, Quotes quotes,
                                                        Wallet wallet, ArrayList<Transaction> transactionsHistory) {
        if (transactions[0].isEmpty())
            return;
        for (String transaction : transactions) {
            String[] transactionArray = transaction.split("'");
            String equityHolding = transactionArray[1];
            if (equityHolding.charAt(0) == 'c') {
                if (transactionArray[0].equals("s")) {
                    Transaction transactionElement = Sell.builder()
                            .equityHolding(cryptoQuotes.findCryptoInList(equityHolding))
                            .uuid(UUID.fromString(transactionArray[2]))
                            .amount(Double.parseDouble(transactionArray[3]))
                            .timestamp(Long.parseLong(transactionArray[4]))
                            .wallet(wallet).build();
                    transactionsHistory.add(transactionElement);
                }
                else {
                    Transaction transactionElement = Purchase.builder()
                            .equityHolding(cryptoQuotes.findCryptoInList(equityHolding))
                            .uuid(UUID.fromString(transactionArray[2]))
                            .amount(Double.parseDouble(transactionArray[3]))
                            .timestamp(Long.parseLong(transactionArray[4]))
                            .wallet(wallet).build();
                    transactionsHistory.add(transactionElement);
                }
            }
            else {
                if (transactionArray[0].equals("s")) {
                    Transaction transactionElement = Sell.builder().
                            equityHolding(quotes.findQuoteInList(equityHolding)).
                            uuid(UUID.fromString(transactionArray[2])).
                            amount(Double.parseDouble(transactionArray[3])).
                            timestamp(Long.parseLong(transactionArray[4])).
                            wallet(wallet).build();
                    transactionsHistory.add(transactionElement);
                }
                else {
                    Transaction transactionElement = Purchase.builder()
                            .equityHolding(quotes.findQuoteInList(equityHolding))
                            .uuid(UUID.fromString(transactionArray[2]))
                            .amount(Double.parseDouble(transactionArray[3]))
                            .timestamp(Long.parseLong(transactionArray[4]))
                            .wallet(wallet).build();
                    transactionsHistory.add(transactionElement);
                }
            }
        }
    }
}
