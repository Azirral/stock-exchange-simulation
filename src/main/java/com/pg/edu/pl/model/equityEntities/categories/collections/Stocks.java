package com.pg.edu.pl.model.equityEntities.categories.collections;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import lombok.*;

import java.util.Comparator;
import java.util.List;

/**
 * The type Stocks represents a collection of stock categories.
 */
@Data
@Builder
@Getter
@Setter
public class Stocks implements Comparator<Stock> {
    /** The list of stock categories. */
    @Singular
    List<Stock> stocks;

    /**
     * Compares two stock categories for order.
     * @param o1 The first stock category to compare.
     * @param o2 The second stock category to compare.
     * @return A negative integer, zero, or a positive integer as the first stock category is less than, equal to, or greater than the second stock category.
     */
    @Override
    public int compare(Stock o1, Stock o2) {
        return o1.compareTo(o2);
    }

    public Stock findStock(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol))
                return stock;
        }
        return null;
    }
}
