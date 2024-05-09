package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.UUID;

/**
 * Transaction class is an abstract class which holds common variables and abstract
 * methods for class "Sell" and "Purchase" which are responsible for users wallet
 * transactions.
 */

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public abstract class Transaction implements Comparable<Transaction>, Comparator<Transaction> {
    private UUID uuid;
    /** instance of equity holding on which the transaction will be performed */
    private EquityHolding equityHolding;
    /** amount of the equity holding specified by the user */
    private Double amount;
    /** timestamp of the transaction (from the operating system) */
    private Long timestamp;
    /** instance of users wallet in which the transaction will be performed */
    private Wallet wallet;

    /** Function responsible for performing the transaction. */
    public abstract void performTransaction(Symbol symbol);

    /**
     * Compares this transaction with the specified transaction for order.
     * @param o The transaction to be compared.
     * @return a negative integer, zero, or a positive integer as this transaction is less than,
     * equal to, or greater than the specified transaction.
     */
    @Override
    public int compareTo(Transaction o) {
        return this.toString().compareTo(o.toString());
    }

    /**
     * Compares two transactions based on their timestamps. Created for sorting purposes.
     * @param o1 The first transaction to be compared.
     * @param o2 The second transaction to be compared.
     * @return a negative integer, zero, or a positive integer as the first transaction's
     * timestamp is less than, equal to, or greater than the second transaction's timestamp.
     */
    @Override
    public int  compare(Transaction o1, Transaction o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid=" + uuid +
                ", equityHolding=" + equityHolding +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", wallet=" + wallet +
                '}';
    }

    /**
     * Gives a representation of the object in csv format with "'" delimiter and a flag as a first element to distinguis
     * Sell from Purchase and create the objects properly when loading from a file.
     * @return A string which represents the object in csv format with "'" delimiter
     * */
    public String toCSV() {
        char flag = (this instanceof Sell) ? 's' : 'p';
        return String.join("'", String.valueOf(flag), equityHolding.toCSV(), String.valueOf(uuid),
                String.valueOf(amount),String.valueOf(timestamp), String.valueOf(wallet.getUuid()));
    }
}
