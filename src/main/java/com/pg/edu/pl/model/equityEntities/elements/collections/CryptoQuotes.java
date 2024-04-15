package com.pg.edu.pl.model.equityEntities.elements.collections;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Comparator;
import java.util.List;

/**
 * The type CryptoQuotes represents a collection of cryptocurrency quotes.
 */
@Data
@Builder
public class CryptoQuotes implements Comparator<CryptoQuote> {
    /** The list of cryptocurrency quotes. */
    @Singular
    private List<CryptoQuote> cryptoQuotes;

    /**
     * Compares two cryptocurrency quotes for order.
     * @param o1 The first cryptocurrency quote to compare.
     * @param o2 The second cryptocurrency quote to compare.
     * @return A negative integer, zero, or a positive integer as the first cryptocurrency quote is less than, equal to, or greater than the second cryptocurrency quote.
     */
    @Override
    public int compare(CryptoQuote o1, CryptoQuote o2) {
        return o1.compareTo(o2);
    }
}
