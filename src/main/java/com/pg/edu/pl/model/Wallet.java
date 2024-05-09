package com.pg.edu.pl.model;


import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Wallet class is responsible that resembles user's wallet and
 * equities he has in it
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Wallet implements Cloneable{
    private UUID uuid;
    /** Value of all equities owned in USD (exchanged)*/
    private Double exchangeValue;
    /** Amount of money user has in the account that is not exchanged to equity*/
    private Double credit;
    /** Amount of money user has gained/lost since last sale/purchase */
    private Double lastSaleValue;
    /** Map of all equities and amount of them user has */
    private HashMap<Symbol, Double> equitiesOwned;
    /** History of all transactions made by the user */
    private ArrayList<Transaction> transactionsHistory;


    /**
     * Method responsible for finding a given equity in the equitiesOwned map.
     * @param equity The equity holding to find in the equitiesOwned map.
     * @return The map entry representing the found equity holding if it exists, or null if not found.
     */
    public Map.Entry<Symbol, Double> findEquityHolding(Symbol equity) {
        for (Map.Entry<Symbol,Double> entry : this.equitiesOwned.entrySet()) {
            if (entry.getKey().compareTo(equity) == 0)
                return entry;
        }
        return null;
    }


    /**
     * Searches for a transaction in the transaction history based on the provided timestamp.
     * @param timestamp The timestamp of the transaction to search for.
     * @return The transaction object if found, or null if not found.
     */
    private Transaction findTransactionInHistory(Long timestamp, EquityHolding equity) {
        for (Transaction transaction : transactionsHistory) {
            if(transaction.getTimestamp().equals(timestamp) &&
                    (transaction.getEquityHolding().compareTo(equity) == 0))
                return transaction;
        }
        return null;
    }


    /**
     * Creates a deep clone of the Wallet object.
     * @return A new instance of Wallet with identical field values.
     */
    @Override
    public Wallet clone() {
        try {
            return (Wallet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String toString() {
        return "Wallet{" +
                "uuid=" + uuid +
                ", exchangeValue=" + exchangeValue +
                ", credit=" + credit +
                ", lastSaleValue=" + lastSaleValue +
                ", equitiesOwned=" + equitiesOwned +
                ", transactionsHistory=" + transactionsHistory +
                '}';
    }

    /**
     * Gives a representation of the object in csv format with "," delimiter.
     * @return A string which represents the object in csv format with "," delimiter
     * */
    public String toCSV() {
        String sb =
                formatTransactionsHistory() + "," +
                formatEquitiesOwned() + "," +
                uuid + "," +
                exchangeValue + "," +
                credit + "," +
                lastSaleValue + ",";
        return sb;
    }

    /**
     * Gives a representation of the object in csv format with "&" delimiter between separate HaspMap elements and ":"
     * delimeter between flag, key, and value. The flag is used to differentiate crypto from stock.
     * @return A string which represents the object in csv format with "," delimiter
     * */
    private String formatEquitiesOwned() {
        StringBuilder equitiesBuilder = new StringBuilder();
        for (Symbol symbol : equitiesOwned.keySet()) {
            char flag = (symbol instanceof Crypto) ? 'c' : 's';
            equitiesBuilder.append(flag).append(":").append(symbol.getSymbol()).append(":").
                    append(equitiesOwned.get(symbol)).append("&");
        }
        return equitiesBuilder.toString();
    }

    /**
     * Gives a representation of the object in csv format with "#" delimiter.
     * @return A string which represents the object in csv format with "#" delimiter
     * */
    private String formatTransactionsHistory() {
        StringBuilder transactionsBuilder = new StringBuilder();
        for (Transaction transaction : transactionsHistory) {
            transactionsBuilder.append(transaction.toCSV()).append("#");
        }
        return transactionsBuilder.toString();
    }

}
