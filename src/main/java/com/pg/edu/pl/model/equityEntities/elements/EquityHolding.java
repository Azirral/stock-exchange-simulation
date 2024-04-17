package com.pg.edu.pl.model.equityEntities.elements;

import lombok.*;

import java.util.Comparator;

/**
 * EquityHolding class is an abstract class that holds common variables
 * for Stock and Crypto classes that inherit from it.
 */
@Data
@Builder
@Getter
@Setter
@ToString
public abstract class EquityHolding implements Comparable<EquityHolding> {
    /**
     * The price of the equity.
     */
    private Double price;

    /**
     * The percentage change in price.
     */
    private Double changesPercentage;

    /** Difference between the current price
     * and the last trade of the previous day.
     */
    private Double change;

    /**
     * The lowest price of the equity for the current trading day.
     */
    private Double dayLow;

    /**
     * The highest price of the equity for the current trading day.
     */
    private Double dayHigh;

    /**
     * The highest price of the equity for the past year.
     */
    private Double yearHigh;

    /**
     * The lowest price of the equity for the past year.
     */
    private Double yearLow;

    /** Total dollar market value of a company's
     * outstanding shares of equity.
     */
    private Long marketCap;

    /**
     * The average price of the equity over the past 50 days.
     */
    private Double priceAvg50;

    /**
     * The average price of the equity over the past 200 days.
     */
    private Double priceAvg200;

    /** Number of shares traded in an equity or contracts
     * traded in futures or options.
     */
    private Long volume;

    /** Number of shares of a particular equity that,
     * on average, change hands during a single trading day.
     */
    private Long avgVolume;

    /** First price an equity trades at when the market opens. */
    private Double open;

    /** Prior day's final price of a security
     * when the market officially closes for the day.
     */
    private Double previousClose;

    /** Company's equity currently held by all its shareholders. */
    private Long sharesOutstanding;

    /** Time of registered screening of equity. */
    private Long timestamp;

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
