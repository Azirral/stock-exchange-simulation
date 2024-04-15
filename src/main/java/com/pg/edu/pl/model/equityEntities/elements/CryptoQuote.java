package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import lombok.*;

/**
 * CryptoQuote class is a subclass of EquityHolding that resembles a cryptocurrency quote
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Setter
@Getter
@ToString(callSuper = true)
public class CryptoQuote extends EquityHolding implements Comparable<CryptoQuote>{
    /** The cryptocurrency associated with this quote. */
    private Crypto crypto;

    /** The currency in which the cryptocurrency is traded. */
    private String currency;

    /**
     * Compares this crypto quote to another crypto quote based on their string representations.
     * @param o The other crypto quote to compare to.
     * @return A negative integer, zero, or a positive integer as this crypto quote is less than, equal to, or greater than the specified crypto quote.
     */
    @Override
    public int compareTo(CryptoQuote o) {
        return this.toString().compareTo(o.toString());
    }
}
