package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;

/**
 * Purchase class is a subclass of abstract class Transaction. It is responsible for
 * updating users wallet after purchase transaction has been made.
 */

public class Purchase extends Transaction {

    public Purchase(EquityHolding equityHolding, Double amount, Long timestamp, Wallet wallet) {
        super(equityHolding, amount, timestamp, wallet);
    }

    @Override
    public void performTransaction() {
        this.getWallet().findEquityHolding(this.getEquityHolding().getSymbol()).setValue(this.getWallet().
                findEquityHolding(this.getEquityHolding().getSymbol()).getValue() + this.getAmount());
    }
}
