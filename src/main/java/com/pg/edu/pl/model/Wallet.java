package com.pg.edu.pl.model;


import com.pg.edu.pl.model.equityEntities.elements.EquityHolding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Wallet class is responsible that resembles user's wallet and
 * equities he has in it
 */
@Getter
@Setter
@AllArgsConstructor
public class Wallet {
    /** Value of all equities owned in USD (exchanged)*/
    private Double exchangeValue;
    /** Amount of money user has in the account that is not exchanged to equity*/
    private Double credit;
    /** Amount of money user has gained/lost since last sale/purchase */
    private Double lastSaleValue;
    /** Map of all equities and amount of them user has */
    private Map<EquityHolding, Double> equitiesOwned;

    /** Method responsible for finding a given equity in equitiesOwned */
    public Map.Entry<EquityHolding, Double> findEquityHolding(String equitySymbol) {
        for (Map.Entry<EquityHolding,Double> entry : this.equitiesOwned.entrySet()) {
            if (entry.getKey().getSymbol().equals(equitySymbol))
                return entry;
        }
        return null;
    }

    /** Method responsible for calculating total value of equities owned in USD by the user */
    public Double calculateExchangesValue(Map<EquityHolding, Double> equitiesOwned) {
        double totalValueOwned = 0;
        for (Map.Entry<EquityHolding,Double> entry : equitiesOwned.entrySet()) {
            totalValueOwned += (entry.getKey().getPrice() * entry.getValue());
        }
        return totalValueOwned;
    }
}
