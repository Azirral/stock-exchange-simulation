package com.pg.edu.pl.model.equityEntities.elements.collections;

import com.pg.edu.pl.model.equityEntities.elements.Quote;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Comparator;
import java.util.List;

/**
 * The type Quotes represents a collection of stock quotes.
 */
@Data
@Builder
public class Quotes implements Comparator<Quote> {
    /** The list of stock quotes. */
    @Singular
    private List<Quote> quotes;

    /**
     * Compares two stock quotes for order.
     * @param o1 The first stock quote to compare.
     * @param o2 The second stock quote to compare.
     * @return A negative integer, zero, or a positive integer as the first stock quote is less than, equal to, or greater than the second stock quote.
     */
    @Override
    public int compare(Quote o1, Quote o2) {
        return o1.compareTo(o2);
    }
}
