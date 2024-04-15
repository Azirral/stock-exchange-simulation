package com.pg.edu.pl.model.equityEntities.categories;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@Builder
public class Crypto extends Symbol implements Comparable<Crypto>{
    private String currency;

    @Override
    public int compareTo(Crypto o) {
        return this.toString().compareTo(o.toString());
    }

}
