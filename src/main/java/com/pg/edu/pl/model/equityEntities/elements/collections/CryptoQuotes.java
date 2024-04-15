package com.pg.edu.pl.model.equityEntities.elements.collections;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class CryptoQuotes {
    @Singular
    private List<CryptoQuote> cryptoQuotes;
}
