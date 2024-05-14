package com.pg.edu.pl.model.prediction;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import com.pg.edu.pl.model.equityEntities.categories.Crypto;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Represents a prediction for crypto price.
 * Inherits from Prediction class.
 */
public class CryptoPrediction extends Prediction {

    /** Crypto for which we are predicting the price */
    private final Crypto cryptoPredict;

    /**
     * Constructor for CryptoPrediction class.
     * @param name The name of the prediction.
     * @param description Description of the prediction.
     * @param predictedPrice The predicted price.
     * @param accuracy The accuracy of the prediction.
     * @param log_date The date of the prediction.
     * @param crypto The Crypto object for prediction.
     */
    public CryptoPrediction(String name, String description, Double predictedPrice, Double accuracy, Date log_date, Crypto crypto) {
        super(name, description, predictedPrice, accuracy, log_date);
        this.cryptoPredict = crypto;
    }

    /**
     * Method to predict crypto price.
     * Overrides the predict() method of the Prediction class.
     */
    @Override
    public void predictLinear() throws ParseException {
        /** Get quotes from Crypto object */
        List<CryptoQuote> quotes = cryptoPredict.getCryptoQuotes().getCryptoQuotes();

        /** Perform linear regression */
        SimpleRegression regression = new SimpleRegression();
        /** Use timestamp as independent variable and price as dependent variable */
        for (CryptoQuote quote : quotes) {
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
        System.out.println("Prediction for: " + cryptoPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + nextTimestamp);
    }

    @Override
    public void predictLinearMultiThreads(int threads) throws ParseException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        /** Get quotes from Crypto object */
        List<CryptoQuote> quotes = cryptoPredict.getCryptoQuotes().getCryptoQuotes();

        /** Perform linear regression */
        SimpleRegression regression = new SimpleRegression();

        List<Future<?>> futures = new ArrayList<>();
        /** Use timestamp as independent variable and price as dependent variable */
        for (CryptoQuote quote : quotes) {
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
        System.out.println("Prediction for: " + cryptoPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + nextTimestamp);
    }

    /**
     * This method is used to convert a string date to a timestamp.
     *
     * @param dateString The date as a string.
     * @param format The format of the date string.
     * @return The timestamp equivalent of the date string.
     * @throws ParseException If the date string cannot be parsed.
     */
    public static double stringDateToTimestamp(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateString);
        return (double) date.getTime();
    }

}

