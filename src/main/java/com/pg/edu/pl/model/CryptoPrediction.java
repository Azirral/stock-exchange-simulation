package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a prediction for cryptocurrency price.
 * Inherits from Prediction class.
 */
@Getter
@Setter
public class CryptoPrediction extends Prediction {
    private CryptoQuote cryptoQuote;

    /**
     * Constructor for CryptoPrediction class.
     * @param name The name of the prediction.
     * @param description Description of the prediction.
     * @param predictedPrice The predicted price.
     * @param accuracy The accuracy of the prediction.
     * @param log_date The date of the prediction.
     * @param cryptoQuote The CryptoQuote object.
     */
    public CryptoPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, CryptoQuote cryptoQuote) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.cryptoQuote = cryptoQuote;
    }

    /**
     * Method to predict cryptocurrency price.
     * Overrides the predict() method of the Prediction class.
     */
    @Override
    public void predict() {
        // Simple prediction algorithm: assuming a 5% increase in price
        if (cryptoQuote != null && cryptoQuote.getClose() != null) {
            // Assuming the price will increase by 5% from the current price
            double predictedPrice = cryptoQuote.getClose() * 1.05;
            setPredictedPrice(predictedPrice);
        } else {
            // If crypto data is not available, set predicted price to null
            setPredictedPrice(null);
        }
    }
}
