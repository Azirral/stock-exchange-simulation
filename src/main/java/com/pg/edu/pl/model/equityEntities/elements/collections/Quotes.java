package com.pg.edu.pl.model.equityEntities.elements.collections;

import com.pg.edu.pl.model.equityEntities.elements.Quote;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class Quotes implements Comparator<Quote> {
    @Singular
    private List<Quote> quotes;

    @Override
    public int compare(Quote o1, Quote o2) {
        return o1.compareTo(o2);
    }
}
