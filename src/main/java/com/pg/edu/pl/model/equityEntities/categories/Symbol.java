package com.pg.edu.pl.model.equityEntities.categories;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
public abstract class Symbol {
    String symbol;
    String name;
    String stockExchange;
}
