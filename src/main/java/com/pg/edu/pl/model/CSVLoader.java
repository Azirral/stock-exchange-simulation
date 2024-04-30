package com.pg.edu.pl.model;
import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public Cryptos loadCryptosFromCSV(String filePath) throws IOException {
        List<Crypto> cryptosList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            cryptosList.add(Crypto.builder().symbol(values[0]).name(values[1]).
                    stockExchange(values[2]).currency(values[3]).build());
        }
        br.close();
        return Cryptos.builder().cryptos(cryptosList).build();
    }

    public Stocks loadStocksFromCSV(String filePath) throws IOException {
        List<Stock> stocksList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            stocksList.add(Stock.builder().symbol(values[0]).name(values[1]).
                    stockExchange(values[2]).build());
        }
        br.close();
        return Stocks.builder().stocks(stocksList).build();
    }
}