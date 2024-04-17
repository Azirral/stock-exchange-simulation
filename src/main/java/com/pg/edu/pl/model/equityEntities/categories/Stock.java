package com.pg.edu.pl.model.equityEntities.categories;

import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import lombok.*;

/**
 * The type Stock represents a category of stocks.
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@ToString(callSuper = true)
@Getter
@Setter
public class Stock extends Symbol implements Comparable<Symbol> {
    /** The collection of quotes associated with this stock. */
    private Quotes quotes;

    /**
     * Compares this stock to another stock based on their string representations.
     * @param o The other stock to compare to.
     * @return A negative integer, zero, or a positive integer as this stock is less than, equal to, or greater than the specified stock.
     */
    @Override
    public int compareTo(Symbol o) {
        return this.toString().compareTo(o.toString());
    }
}
