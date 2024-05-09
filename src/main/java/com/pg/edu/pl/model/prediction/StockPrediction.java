package com.pg.edu.pl.model.prediction;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.elements.Quote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a prediction for stock price.
 * Inherits from Prediction class.
 */
public class StockPrediction extends Prediction {

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
    public void predict_linear() throws ParseException {
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

    /**
     * Method to predict stock price using polynomial regression.
     */
    @Override
    public void predict_polynomial() throws ParseException {
        /** Get quotes from Stock object */
        List<Quote> quotes = stockPredict.getQuotes().getQuotes();

        /** Perform polynomial regression */
        WeightedObservedPoints points = new WeightedObservedPoints();
        for (Quote quote : quotes) {
            points.add(stringDateToTimestamp(quote.getDate(),"yyyy-MM-dd HH:mm:ssXXX"), quote.getClose());
        }
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2); // Quadratic polynomial
        double[] coefficients = fitter.fit(points.toList());

        /** Predict the next price (assuming next predict_lineartimestamp is one day ahead) */
        long nextTimestamp = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        double nextPrice = evaluatePolynomial(coefficients, nextTimestamp);

        /** Set the predicted price */
        setPredictedPrice(nextPrice);

        /** Print the predicted price */
        System.out.println("Polynomial Regression Prediction for: " + stockPredict.getName());
        System.out.println("Predicted price: " + nextPrice + " for " + new Date(nextTimestamp));
    }

    /**
     * This method is used to evaluate a polynomial.
     *
     * @param coefficients The coefficients of the polynomial.
     * @param timestamp The timestamp at which the polynomial is to be evaluated.
     * @return The result of the polynomial evaluation.
     */
    private double evaluatePolynomial(double[] coefficients, long timestamp) {
        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(timestamp, i);
        }
        return result;
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
}
