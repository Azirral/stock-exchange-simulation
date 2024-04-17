package com.pg.edu.pl.model;


import com.pg.edu.pl.model.equityEntities.categories.Symbol;
import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * Purchase class is a subclass of abstract class Transaction. It is responsible for
 * updating users wallet after purchase transaction has been made.
 */
@SuperBuilder
public class Purchase extends Transaction {

    /**
     * Constructs a new Purchase object.
     *
     * @param equityHolding The EquityHolding object representing the equity being purchased.
     * @param amount        The amount of equity being purchased.
     * @param timestamp     The timestamp of the transaction.
     * @param wallet        The user's wallet.
     */
    public Purchase(EquityHolding equityHolding, Double amount, Long timestamp, Wallet wallet) {
        super(equityHolding, amount, timestamp, wallet);
    }

    /**
     * Performs the purchase transaction.
     * If the equity holding is found in the wallet, it updates the user's credit and
     * increases the amount of equity owned. Otherwise, it adds the equity holding to the wallet.
     *
     * @param symbol The symbol of the equity being purchased.
     */
    @Override
    public void performTransaction(Symbol symbol) {
        Map.Entry<Symbol, Double> entry = this.getWallet().findEquityHolding(symbol);
        if (entry != null) {
            this.getWallet().setCredit(this.getWallet().getCredit()
                    - (this.getAmount() * this.getEquityHolding().getPrice()));
            this.getWallet().getEquitiesOwned().computeIfPresent(entry.getKey(), (k, v) -> v + this.getAmount());
        }
        else {
            this.getWallet().getEquitiesOwned().put(symbol, this.getAmount());
        }
        this.getWallet().getTransactionsHistory().add(this);
    }
}
