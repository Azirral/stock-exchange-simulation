package com.pg.edu.pl.classes;

import lombok.*;

/**
 * Transaction class is an abstract class which holds common variables and abstract
 * methods for class "Sell" and "Purchase" which are responsible for users wallet
 * transactions.
 */

@Getter
@Setter
@AllArgsConstructor
public abstract class Transaction {
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
}
