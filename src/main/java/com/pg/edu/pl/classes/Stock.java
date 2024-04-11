package com.pg.edu.pl.classes;

import lombok.Getter;
import lombok.Setter;

/** Stock class is a subclass of EquityHolding that resembles a Stock equity*/
@Getter
@Setter
public class Stock extends EquityHolding {
    /** The stock market on which stock is listed. */
    String stockExchange;
    /** Earnings per share of stock */
    Double eps;
    /** Price to earnings ratio. */
    Double pe;
    /** Date and time of Statement of company's profitability. */
    String earningsAnnouncement;

    protected Stock(String stockExchange, Double eps, Double pe, String earningsAnnouncement, String name,
                    String symbol, Double price, Double changesPercentage, Double change,
                    Double dayLow, Double dayHigh, Double yearHigh, Double yearLow, Long marketCap,
                    Double priceAvg50, Double priceAvg200, Long volume, Long avgVolume, Double open,
                    Double previousClose, Long sharesOutstanding, Long timestamp) {
        super(name, symbol, price, changesPercentage, change, dayLow, dayHigh, yearHigh, yearLow, marketCap,
                priceAvg50, priceAvg200, volume, avgVolume, open, previousClose, sharesOutstanding, timestamp);
        this.stockExchange = stockExchange;
        this.eps = eps;
        this.pe = pe;
        this.earningsAnnouncement = earningsAnnouncement;
    }
    // Override toString method to print the object
    @Override
    public String toString() {
        return "Stock{" +
                "stockExchange='" + stockExchange + '\'' +
                ", eps=" + eps +
                ", pe=" + pe +
                ", earningsAnnouncement='" + earningsAnnouncement + '\'' +
                "} " + super.toString();
    }

}
