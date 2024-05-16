package com.pg.edu.pl.model.prediction;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.elements.Quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a prediction for stock price.
 * Inherits from Prediction class.
 */
public class StockPrediction extends Prediction implements Serializable {

    /** Stock for which we are predicting the price */
    private final Stock stockPredict;

    /**
     * Constructor for StockPrediction class.
     * @param name The name of the prediction.
     * @param description Description of the prediction.
     * @param predictedPrice The predicted price.
     * @param accuracy The accuracy of the prediction.
     * @param log_date The date of the prediction.
     * @param stock The Stock object for prediction.
     */
    public StockPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, Stock stock) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.stockPredict = stock;
    }

    /**
     * Method to predict stock price.
     * Overrides the predict() method of the Prediction class.
     */
    @Override
    public void predictLinear() throws ParseException {
        /** Get quotes from Stock object */
        List<Quote> quotes = stockPredict.getQuotes().getQuotes();

        /** Perform linear regression */
        SimpleRegression regression = new SimpleRegression();
        /** Use timestamp as independent variable and price as dependent variable */
        for (Quote quote : quotes) {
            /** Use timestamp as independent variable and price as dependent variable */
            regression.addData(stringDateToTimestamp(quote.getDate(),"yyyy-MM-dd HH:mm:ssXXX"), quote.getClose());
        }

        /** Get slope and intercept of the regression line */
        double slope = regression.getSlope();
        double intercept = regression.getIntercept();

        /** Predict the next price (assuming next timestamp is one day ahead) */
        long nextTimestamp = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        double nextPrice = slope * nextTimestamp + intercept;

        /** Set the predicted price */
        setPredictedPrice(nextPrice);

        /** Print the predicted price */
        System.out.println("Linear Regression Prediction for: " + stockPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + new Date(nextTimestamp));
    }


    @Override
    public void predictLinearMultiThreads(int threads) throws ParseException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        /** Get quotes from Stock object */
        List<Quote> quotes = stockPredict.getQuotes().getQuotes();

        /** Perform linear regression */
        SimpleRegression regression = new SimpleRegression();

        List<Future<?>> futures = new ArrayList<>();
        /** Use timestamp as independent variable and price as dependent variable */
        for (Quote quote : quotes) {
            Future<?> future = executor.submit(() -> {
                try {
                    regression.addData(stringDateToTimestamp(quote.getDate(),"yyyy-MM-dd HH:mm:ssXXX"), quote.getClose());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get(); // Wait for task completion
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace(); // Handle exceptions
            }
        }
        executor.shutdown();

        /** Get slope and intercept of the regression line */
        double slope = regression.getSlope();
        double intercept = regression.getIntercept();

        /** Predict the next price (assuming next timestamp is one day ahead) */
        long nextTimestamp = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        double nextPrice = slope * nextTimestamp + intercept;

        /** Set the predicted price */
        setPredictedPrice(nextPrice);

        /** Print the predicted price */
        System.out.println("Linear Regression Prediction for: " + stockPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + new Date(nextTimestamp));
    }

    /**
     * This method is used to convert a string date to a timestamp.
     *
     * @param dateString The date as a string.
     * @param format The format of the date string.
     * @return The timestamp.
     * @throws ParseException If the date string cannot be parsed.
     */
    public static double stringDateToTimestamp(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateString);
        return (double) date.getTime();
    }

    @Override
    public String toString() {
        return "StockPrediction {" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", predictedPrice=" + getPredictedPrice() +
                ", accuracy=" + getAccuracy() +
                ", log_date=" + getLog_date() +
                '}';
    }
}
