package com.pg.edu.pl.model.equityEntities.elements;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.Date;

/**
 * EquityHolding class is an abstract class that holds common variables
 * for Stock and Crypto classes that inherit from it.
 */
@Data
@SuperBuilder
@Getter
@Setter
@ToString
public abstract class EquityHolding implements Comparable<EquityHolding> {
    /**Date of registered screening of equity */
    private Date date;

    /** First price an equity trades at when the market opens. */
    private Double open;

    /** lowest price in specific minute */
    private Double low;

    /** highest price in specific minute */
    private Double high;

    /**
     * The price of the equity.
     */
    private Double close;

    /** Number of shares traded in an equity or contracts
     * traded in futures or options.
     */
    private Long volume;

    /** Symbol of the equity. */
    private String symbol;

    /**
     * Compares this EquityHolding object with another EquityHolding object based on their string representations.
     * @param o The other EquityHolding object to compare to.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(EquityHolding o) {
        return this.toString().compareTo(o.toString());
    }
}
