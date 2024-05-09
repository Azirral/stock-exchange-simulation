package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * CryptoQuote class is a subclass of EquityHolding that resembles a cryptocurrency quote
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Setter
@Getter
@ToString(callSuper = true)
public class CryptoQuote extends EquityHolding implements Comparable<EquityHolding>{
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
    public int compareTo(EquityHolding o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return "CryptoQuote{" +
                "date='" + getDate() + '\'' +
                ", open=" + getOpen() +
                ", low=" + getLow() +
                ", high=" + getHigh() +
                ", close=" + getClose() +
                ", volume=" + getVolume() +
                ", symbol='" + getSymbol() + '\'' +
                ", crypto=" + crypto +
                ", currency='" + currency + '\'' +
                '}';
    }

    /**
     * Gives a representation of the object in csv format with "~" delimiter and a flag as a first element to vary quote
     * from cryptoQuote. It is needed to properly load objects from file.
     * @return A string which represents the object in csv format with "~" delimiter
     * */
    @Override
    public String toCSV() {
        return String.join("~", "c",getDate(), String.valueOf(getOpen()),
                String.valueOf(getLow()), String.valueOf(getHigh()), String.valueOf(getClose()),
                String.valueOf(getVolume()), getSymbol(), String.valueOf(crypto), String.valueOf(currency));
    }
}
