package com.pg.edu.pl.classes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// StockPrediction class is a subclass of Prediction that resembles prediction for stock price
@Getter
@Setter
public class StockPrediction extends Prediction {
    // Stock for which we are predicting the price
    private Stock stock;

    public StockPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, Stock stock) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.stock = stock;
    }

    @Override
    public void predict() {
        // Simple prediction algorithm: using previous day's closing price as the predicted price for the next day
        if (stock != null && stock.getPreviousClose() != null) {
            // Assume predicted price is the same as previous day's closing price
            setPredictedPrice(stock.getPreviousClose());
        } else {
            // If stock data is not available, set predicted price to null
            setPredictedPrice(null);
        }
    }
}
