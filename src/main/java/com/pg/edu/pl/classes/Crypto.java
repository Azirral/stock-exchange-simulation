package com.pg.edu.pl.classes;

import lombok.Getter;
import lombok.Setter;

/** Crypto class is a subclass of EquityHolding that resembles cryptocurrency equity*/
@Setter
@Getter
public class Crypto extends EquityHolding{
    /** This variable is linked to the "normal" (national currency) currency
     * in which cryptocurrency is traded in
     * */
    private String currency;
    protected Crypto(String currency, String name, String symbol, Double price, Double changesPercentage,
                     Double change, Double dayLow, Double dayHigh, Double yearHigh, Double yearLow,
                     Long marketCap, Double priceAvg50, Double priceAvg200, Long volume, Long avgVolume,
                     Double open, Double previousClose, Long sharesOutstanding, Long timestamp) {
        super(name, symbol, price, changesPercentage, change, dayLow, dayHigh, yearHigh, yearLow, marketCap,
                priceAvg50, priceAvg200, volume, avgVolume, open, previousClose, sharesOutstanding, timestamp);
        this.currency = currency;
    }
}
