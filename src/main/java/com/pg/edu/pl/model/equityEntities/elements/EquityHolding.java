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
public abstract class EquityHolding{
    private Double price;
    private Double changesPercentage;
    /** Difference between the current price
     * and the last trade of the previous day.
     */
    private Double change;
    private Double dayLow;
    private Double dayHigh;
    private Double yearHigh;
    private Double yearLow;
    /** Total dollar market value of a company's
     * outstanding shares of equity.
     */
    private Long marketCap;
    private Double priceAvg50;
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
}
