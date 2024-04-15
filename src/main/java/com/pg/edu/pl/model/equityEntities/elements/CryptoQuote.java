package com.pg.edu.pl.model.equityEntities.elements;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import lombok.*;

/** CryptoQuote class is a subclass of EquityHolding that resembles a cryptocurrency quote*/
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Setter
@Getter
@ToString(callSuper = true)
public class CryptoQuote extends EquityHolding implements Comparable<CryptoQuote>{
    private Crypto crypto;
    /** This variable is linked to the "normal" (national currency) currency
     * in which cryptocurrency is traded in
     * */
    private String currency;

    @Override
    public int compareTo(CryptoQuote o) {
        return this.toString().compareTo(o.toString());
    }
}
