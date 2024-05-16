package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Quote class is a subclass of EquityHolding that resembles a single quote
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class Quote extends EquityHolding implements Comparable<EquityHolding>, Serializable {
    /** The stock associated with this quote. */
    private Stock stock;

//    /** Earnings per share of stock */
//    private Double eps;
//
//    /** Price to earnings ratio. */
//    private Double pe;
//
//    /** Date and time of Statement of company's profitability. */
//    private String earningsAnnouncement;

    /**
     * Compares this quote to another quote based on their string representations.
     * @param o The other quote to compare to.
     * @return A negative integer, zero, or a positive integer as this quote is less than, equal to, or greater than the specified quote.
     */
    @Override
    public int compareTo(EquityHolding o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return "Quote{" +
                "date='" + getDate() + '\'' +
                ", open=" + getOpen() +
                ", low=" + getLow() +
                ", high=" + getHigh() +
                ", close=" + getClose() +
                ", volume=" + getVolume() +
                ", symbol='" + getSymbol() + '\'' +
                ", stock=" + stock +
                '}';
    }

    /**
     * Gives a representation of the object in csv format with "~" delimiter and a flag as a first element to vary quote
     * from cryptoQuote. It is needed to properly load objects from file.
     * @return A string which represents the object in csv format with "~" delimiter
     * */
    @Override
    public String toCSV() {
        return String.join("~", "q",getDate(), String.valueOf(getOpen()),
                String.valueOf(getLow()), String.valueOf(getHigh()), String.valueOf(getClose()),
                String.valueOf(getVolume()), getSymbol(), String.valueOf(stock));
    }
}
