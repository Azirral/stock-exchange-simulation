package com.pg.edu.pl.model.equityEntities.elements;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

/** Quote class is a subclass of EquityHolding that resembles a single quote*/
@Data
@Builder
@Getter
@Setter
public class Quote extends EquityHolding implements Comparable<Quote> {
    /** Earnings per share of stock */
    Double eps;
    /** Price to earnings ratio. */
    Double pe;
    /** Date and time of Statement of company's profitability. */
    String earningsAnnouncement;

    protected Quote(Double eps, Double pe, String earningsAnnouncement, Double price, Double changesPercentage, Double change,
                    Double dayLow, Double dayHigh, Double yearHigh, Double yearLow, Long marketCap,
                    Double priceAvg50, Double priceAvg200, Long volume, Long avgVolume, Double open,
                    Double previousClose, Long sharesOutstanding, Long timestamp) {
        super(price, changesPercentage, change, dayLow, dayHigh, yearHigh, yearLow, marketCap,
                priceAvg50, priceAvg200, volume, avgVolume, open, previousClose, sharesOutstanding, timestamp);
        this.eps = eps;
        this.pe = pe;
        this.earningsAnnouncement = earningsAnnouncement;
    }

    @Override
    public int compareTo(Quote o) {
        return this.toString().compareTo(o.toString());
    }
}
