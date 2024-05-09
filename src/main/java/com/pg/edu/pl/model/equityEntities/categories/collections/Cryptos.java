package com.pg.edu.pl.model.equityEntities.categories.collections;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import lombok.*;

import java.util.Comparator;
import java.util.List;

/**
 * The type Cryptos represents a collection of cryptocurrency categories.
 */
@Data
@Builder
@Getter
@Setter
public class Cryptos implements Comparator<Crypto> {
    /** The list of cryptocurrency categories. */
    List<Crypto> cryptos;


    /**
     * Compares two cryptocurrency categories for order.
     * @param o1 The first cryptocurrency category to compare.
     * @param o2 The second cryptocurrency category to compare.
     * @return A negative integer, zero, or a positive integer as the first cryptocurrency category is less than, equal to, or greater than the second cryptocurrency category.
     */
    @Override
    public int compare(Crypto o1, Crypto o2) {
        return o1.compareTo(o2);
    }
}
