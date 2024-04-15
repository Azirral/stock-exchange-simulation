package com.pg.edu.pl.model.equityEntities.categories;

import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@ToString(callSuper = true)
@Getter
@Setter
public class Stock extends Symbol implements Comparable<Stock> {
    private Quotes quotes;
    @Override
    public int compareTo(Stock o) {
        return this.toString().compareTo(o.toString());
    }
}
