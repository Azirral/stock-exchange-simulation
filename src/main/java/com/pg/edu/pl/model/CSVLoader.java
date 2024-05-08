package com.pg.edu.pl.model;
import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.categories.collections.Cryptos;
import com.pg.edu.pl.model.equityEntities.categories.collections.Stocks;
import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
import com.pg.edu.pl.model.equityEntities.elements.collections.CryptoQuotes;
import com.pg.edu.pl.model.equityEntities.elements.collections.Quotes;

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
        ArrayList<Stock> stocksList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if(values.length == 3 )
                stocksList.add(Stock.builder().symbol(values[0]).name(values[1]).
                    stockExchange(values[2]).build());
        }
        br.close();
        return Stocks.builder().stocks(stocksList).build();
    }

    public Quotes loadQuotesFromCSV(String filePath) throws IOException {
        List<Quote> quoteList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            quoteList.add(Quote.builder().date(values[0]).open(Double.valueOf(values[1])).
                    low(Double.valueOf(values[2])).high(Double.valueOf(values[3])).close(Double.valueOf(values[4]))
                    .volume(Long.valueOf(values[5])).symbol(values[6]).stock(null).build());
        }
        br.close();
        return Quotes.builder().quotes(quoteList).build();
    }

    public CryptoQuotes loadCryptoQuotesFromCSV(String filePath) throws IOException {
        List<CryptoQuote> cryptoQuoteList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            cryptoQuoteList.add(CryptoQuote.builder().date(values[0]).open(Double.valueOf(values[1])).
                    low(Double.valueOf(values[2])).high(Double.valueOf(values[3])).close(Double.valueOf(values[4]))
                    .volume(Long.valueOf(values[5])).symbol(values[6]).crypto(null).currency(values[7]).build());
        }
        br.close();
        return CryptoQuotes.builder().cryptoQuotes(cryptoQuoteList).build();
    }
}