package com.pg.edu.pl.model.equityEntities.categories;

import lombok.*;

/**
 * The Symbol class represents a financial symbol, which is a unique identifier for an equity or cryptocurrency.
 * It encapsulates information such as the symbol itself, the name of the equity or cryptocurrency,
 * and the stock exchange or trading platform where it is listed.
 */
@Data
@Builder
@Getter
@Setter
@ToString
public abstract class Symbol {
    /**
     * The symbol of the equity.
     */
    String symbol;

    /**
     * The name of the equity.
     */
    String name;

    /**
     * The stock exchange where the equity is traded.
     */
    String stockExchange;
}
