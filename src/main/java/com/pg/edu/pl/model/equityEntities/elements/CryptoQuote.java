package com.pg.edu.pl.model.equityEntities.elements;

import lombok.*;

/** CryptoQuote class is a subclass of EquityHolding that resembles a cryptocurrency quote*/
@Builder
@Data
@Setter
@Getter
@ToString
public class CryptoQuote extends EquityHolding implements Comparable<CryptoQuote>{
    /** This variable is linked to the "normal" (national currency) currency
     * in which cryptocurrency is traded in
     * */
    private String currency;
    protected CryptoQuote(String currency, Double price, Double changesPercentage,
                          Double change, Double dayLow, Double dayHigh, Double yearHigh, Double yearLow,
                          Long marketCap, Double priceAvg50, Double priceAvg200, Long volume, Long avgVolume,
                          Double open, Double previousClose, Long sharesOutstanding, Long timestamp) {
        super(price, changesPercentage, change, dayLow, dayHigh, yearHigh, yearLow, marketCap,
                priceAvg50, priceAvg200, volume, avgVolume, open, previousClose, sharesOutstanding, timestamp);
        this.currency = currency;
    }

    @Override
    public int compareTo(CryptoQuote o) {
        return this.toString().compareTo(o.toString());
    }
}
