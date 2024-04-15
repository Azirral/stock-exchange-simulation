package com.pg.edu.pl.model.equityEntities.elements.collections;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class CryptoQuotes implements Comparator<CryptoQuote> {
    @Singular
    private List<CryptoQuote> cryptoQuotes;

    @Override
    public int compare(CryptoQuote o1, CryptoQuote o2) {
        return o1.compareTo(o2);
    }
}
