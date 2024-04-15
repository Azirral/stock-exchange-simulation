package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// CryptoPrediction class is a subclass of Prediction that resembles prediction for crypto price
@Getter
@Setter
public class CryptoPrediction extends Prediction {
    // Crypto for which we are predicting the price
    private CryptoQuote cryptoQuote;

    public CryptoPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, CryptoQuote cryptoQuote) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.cryptoQuote = cryptoQuote;
    }

    @Override
    public void predict() {
        // Simple prediction algorithm: assuming a 5% increase in price
        if (cryptoQuote != null && cryptoQuote.getPrice() != null) {
            // Assuming the price will increase by 5% from the current price
            double predictedPrice = cryptoQuote.getPrice() * 1.05;
            setPredictedPrice(predictedPrice);
        } else {
            // If crypto data is not available, set predicted price to null
            setPredictedPrice(null);
        }
    }
}
