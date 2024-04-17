package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Sell class is a subclass of abstract class Transaction. It is responsible for
 * updating users wallet after sell transaction has been made.
 */

public class Sell extends Transaction {

    /**
     * Constructs a new Sell object.
     *
     * @param equityHolding The EquityHolding object representing the equity being sold.
     * @param amount        The amount of equity being sold.
     * @param timestamp     The timestamp of the transaction.
     * @param wallet        The user's wallet.
     */
    public Sell(EquityHolding equityHolding, Double amount, Long timestamp, Wallet wallet) {
        super(equityHolding, amount, timestamp, wallet);
    }

    /**
     * Performs the sell transaction.
     * If the equity holding is found in the wallet, it updates the user's credit and
     * decreases the amount of equity owned. Otherwise, it prints an error message.
     *
     * @param symbol The symbol of the equity being sold.
     */
    @Override
    public void performTransaction(Symbol symbol) {
        Map.Entry<Symbol, Double> entry = this.getWallet().findEquityHolding(symbol);
        if (entry != null) {
            this.getWallet().setCredit(this.getWallet().getCredit()
                    + (this.getAmount() * this.getEquityHolding().getPrice()));
            this.getWallet().getEquitiesOwned().computeIfPresent(entry.getKey(), (k, v) -> v - this.getAmount());
        }
        else {
            System.out.println("No such qoute in wallet, please make sure you chosen the proper quote.");
        }
        this.getWallet().getTransactionsHistory().add(this);
    }
}
