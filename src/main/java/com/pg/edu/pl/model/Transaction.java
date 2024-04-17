package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import com.pg.edu.pl.model.equityEntities.elements.Symbol;
import lombok.*;

import java.util.Comparator;

/**
 * Transaction class is an abstract class which holds common variables and abstract
 * methods for class "Sell" and "Purchase" which are responsible for users wallet
 * transactions.
 */

@Getter
@Setter
@AllArgsConstructor
public abstract class Transaction implements Comparable<Transaction>, Comparator<Transaction> {
    /** instance of equity holding on which the transaction will be performed */
    private EquityHolding equityHolding;
    /** amount of the equity holding specified by the user */
    private Double amount;
    /** timestamp of the transaction (from the operating system) */
    private Long timestamp;
    /** instance of users wallet in which the transaction will be performed */
    private Wallet wallet;

    /** Function responsible for performing the transaction. */
    public abstract void performTransaction();

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

}
