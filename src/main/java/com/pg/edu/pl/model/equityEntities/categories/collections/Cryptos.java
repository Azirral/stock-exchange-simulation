package com.pg.edu.pl.model.equityEntities.categories.collections;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class Cryptos implements Comparator<Crypto> {
    List<Crypto> cryptos;

    @Override
    public int compare(Crypto o1, Crypto o2) {
        return o1.compareTo(o2);
    }
}
