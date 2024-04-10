package com.pg.edu.pl.classes;

import lombok.*;

/**
 * Sell class is a subclass of abstract class Transaction. It is responsible for
 * updating users wallet after sell transaction has been made.
 */

public class Sell extends Transaction {

    public Sell(EquityHolding equityHolding, Double amount, Long timestamp, Wallet wallet) {
        super(equityHolding, amount, timestamp, wallet);
    }

    @Override
    public void performTransaction() {

    }
}
