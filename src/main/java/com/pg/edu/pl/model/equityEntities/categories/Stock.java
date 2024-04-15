package com.pg.edu.pl.model.equityEntities.categories;

import lombok.*;

@Builder
@Data
@ToString
@Getter
@Setter
public class Stock extends Symbol implements Comparable<Stock> {

    @Override
    public int compareTo(Stock o) {
        return this.toString().compareTo(o.toString());
    }
}
