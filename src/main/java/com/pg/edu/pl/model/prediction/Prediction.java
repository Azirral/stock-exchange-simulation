package com.pg.edu.pl.model.prediction;

import lombok.*;

import java.util.Date;


/**
 * Prediction class is an abstract class that holds common variables
 * for StockPrediction and CryptoPrediction classes that inherit from it.
 */
@Getter
@Setter
@AllArgsConstructor()
public abstract class Prediction {
    /**
     * Name of the method used for prediction
     */
    private String name;
    /**
     * The short description of the method
     */
    private String description;
    /**
     * The predicted future price for stock/crypto
     */
    private Double predictedPrice;
    /**
     * The accuracy of the method based on
     * training model
     */
    private Double accuracy;
    /**
     * The date of the predicted price
     */
    private Date log_date;

    public abstract void predict_linear() throws Exception;

    public abstract void predict_polynomial() throws Exception;
}
