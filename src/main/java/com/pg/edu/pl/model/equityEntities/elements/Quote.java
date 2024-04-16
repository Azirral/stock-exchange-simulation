package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import lombok.*;

import java.util.Comparator;

/**
 * Quote class is a subclass of EquityHolding that resembles a single quote
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Getter
@Setter
@ToString(callSuper = true)
public class Quote extends EquityHolding implements Comparable<Quote> {
    /** The stock associated with this quote. */
    private Stock stock;

    /** Earnings per share of stock */
    private Double eps;

    /** Price to earnings ratio. */
    private Double pe;

    /** Date and time of Statement of company's profitability. */
    private String earningsAnnouncement;

    /**
     * Compares this quote to another quote based on their string representations.
     * @param o The other quote to compare to.
     * @return A negative integer, zero, or a positive integer as this quote is less than, equal to, or greater than the specified quote.
     */
    @Override
    public int compareTo(Quote o) {
        return this.toString().compareTo(o.toString());
    }
}