package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import lombok.*;

import java.util.Comparator;

/** Quote class is a subclass of EquityHolding that resembles a single quote*/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Getter
@Setter
@ToString(callSuper = true)
public class Quote extends EquityHolding implements Comparable<Quote> {
    private Stock stock;
    /** Earnings per share of stock */
    private Double eps;
    /** Price to earnings ratio. */
    private Double pe;
    /** Date and time of Statement of company's profitability. */
    private String earningsAnnouncement;

    @Override
    public int compareTo(Quote o) {
        return this.toString().compareTo(o.toString());
    }
}
