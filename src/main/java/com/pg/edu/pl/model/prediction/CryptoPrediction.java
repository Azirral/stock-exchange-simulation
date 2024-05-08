package com.pg.edu.pl.model.prediction;

import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void predict_linear() throws ParseException {
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
    public void predict_polynomial() throws ParseException {

        /** Get quotes from Crypto object */
        List<CryptoQuote> quotes = cryptoPredict.getCryptoQuotes().getCryptoQuotes();

        /** Prepare data for polynomial regression */
        WeightedObservedPoints obs = new WeightedObservedPoints();
        for (CryptoQuote quote : quotes) {
            obs.add(stringDateToTimestamp(quote.getDate(),"yyyy-MM-dd HH:mm:ssXXX") , quote.getClose());
        }

        /** Fit a polynomial curve to the data */
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2); // Fit a quadratic curve
        double[] coefficients = fitter.fit(obs.toList());

        /** Predict the next price (assuming next timestamp is one day ahead) */
        long nextTimestamp = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        double nextPrice = predictNextPrice(coefficients, nextTimestamp);

        /** Set the predicted price */
        setPredictedPrice(nextPrice);

        /** Print the predicted price */
        System.out.println("Polynomial Regression Prediction for: " + cryptoPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + new Date(nextTimestamp));
    }

    /**
     * Helper method to predict the next price using polynomial coefficients.
     * @param coefficients Coefficients of the polynomial curve.
     * @param timestamp Timestamp of the next prediction.
     * @return Predicted price.
     */
    private double predictNextPrice(double[] coefficients, long timestamp) {
        double nextTimestampSquared = timestamp * timestamp;
        return coefficients[0] + coefficients[1] * timestamp + coefficients[2] * nextTimestampSquared;
    }

    public static double stringDateToTimestamp(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateString);
        return (double) date.getTime();
    }

}

