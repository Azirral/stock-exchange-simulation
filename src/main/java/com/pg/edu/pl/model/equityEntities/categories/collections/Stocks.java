package com.pg.edu.pl.model.equityEntities.categories.collections;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class Stocks implements Comparator<Stock> {
    @Singular
    List<Stock> stocks;

    @Override
    public int compare(Stock o1, Stock o2) {
        return o1.compareTo(o2);
    }
}
