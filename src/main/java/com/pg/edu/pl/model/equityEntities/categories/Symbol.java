package com.pg.edu.pl.model.equityEntities.categories;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * The Symbol class represents a financial symbol, which is a unique identifier for an equity or cryptocurrency.
 * It encapsulates information such as the symbol itself, the name of the equity or cryptocurrency,
 * and the stock exchange or trading platform where it is listed.
 */
@Data
@SuperBuilder
@Getter
@Setter
@ToString
public abstract class Symbol implements Comparable<Symbol> {
    /**
     * The symbol of the equity.
     */
    String symbol;

    /**
     * The name of the equity.
     */
    String name;

    /**
     * The stock exchange where the equity is traded.
     */
    String stockExchange;

    /**
     * Compares this symbol to another symbol based on their string representations.
     * @param o The other symbol to compare to.
     * @return A negative integer, zero, or a positive integer as this symbol is less than, equal to, or greater than the specified symbol.
     */
    public int compareTo(Symbol o) {
        return this.toString().compareTo(o.toString());
    }
}
