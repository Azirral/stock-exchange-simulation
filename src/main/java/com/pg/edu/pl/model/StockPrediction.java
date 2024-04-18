package com.pg.edu.pl.model;

import com.pg.edu.pl.model.equityEntities.elements.Quote;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a prediction for stock price.
 * Inherits from Prediction class.
 */
@Getter
@Setter
public class StockPrediction extends Prediction {

    // Stock for which we are predicting the price
    private Quote quote;

    /**
     * Constructor for StockPrediction class.
     * @param name The name of the prediction.
     * @param description Description of the prediction.
     * @param predictedPrice The predicted price.
     * @param accuracy The accuracy of the prediction.
     * @param log_date The date of the prediction.
     * @param quote The Quote object.
     */
    public StockPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, Quote quote) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.quote = quote;
    }

    /**
     * Method to predict stock price.
     * Overrides the predict() method of the Prediction class.
     */
    @Override
    public void predict() {
        // Simple prediction algorithm: using previous day's closing price as the predicted price for the next day
        if (quote != null && quote.getPreviousClose() != null) {
            // Assume predicted price is the same as previous day's closing price
            setPredictedPrice(quote.getPreviousClose());
        } else {
            // If stock data is not available, set predicted price to null
            setPredictedPrice(null);
        }
    }
}
