package com.pg.edu.pl.model.equityEntities.categories;

import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import lombok.*;

/**
 * Class Crypto is a category to which a number of cryptoQuotes is assigned.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@ToString(callSuper = true)
@Builder
public class Crypto extends Symbol implements Comparable<Crypto>{
    private String currency;
    private CryptoQuotes cryptoQuotes;

    @Override
    public int compareTo(Crypto o) {
        return this.toString().compareTo(o.toString());
    }

}
