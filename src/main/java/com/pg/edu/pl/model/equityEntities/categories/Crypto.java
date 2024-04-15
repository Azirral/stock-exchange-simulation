package com.pg.edu.pl.model.equityEntities.categories;

import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import lombok.*;

/**
 * Class Crypto represents a category of cryptocurrencies.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@ToString(callSuper = true)
@Builder
public class Crypto extends Symbol implements Comparable<Crypto>{
    /** The currency in which the cryptocurrency is traded. */
    private String currency;

    /** The collection of cryptocurrency quotes associated with this category. */
    private CryptoQuotes cryptoQuotes;

    /**
     * Compares this cryptocurrency to another cryptocurrency based on their string representations.
     * @param o The other cryptocurrency to compare to.
     * @return A negative integer, zero, or a positive integer as this cryptocurrency is less than, equal to, or greater than the specified cryptocurrency.
     */
    @Override
    public int compareTo(Crypto o) {
        return this.toString().compareTo(o.toString());
    }

}
