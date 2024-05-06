package com.pg.edu.pl.model.prediction;

import com.pg.edu.pl.model.equityEntities.categories.Crypto;
import com.pg.edu.pl.model.equityEntities.elements.CryptoQuote;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.regression.RandomForestRegressor;
import org.apache.spark.ml.regression.RandomForestRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Date;
import java.util.List;

/**
 * Represents a prediction for cryptocurrency price.
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
     * Method to predict cryptocurrency price using Random Forest regression.
     * Overrides the predict() method of the Prediction class.
     */
    @Override
    public void predict() {
        /** Initialize Spark */
        SparkConf conf = new SparkConf().setAppName("CryptoPricePrediction").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession.builder().appName("CryptoPricePrediction").getOrCreate();

        /** Generate sample data (Replace this with your actual data loading) */
        List<CryptoQuote> cryptoQuoteList = cryptoPredict.getCryptoQuotes().getCryptoQuotes();

        /** Create RDD from the list */
        JavaRDD<CryptoQuote> cryptoQuoteRDD = sc.parallelize(cryptoQuoteList);

        /** Convert RDD to DataFrame */
        Dataset<Row> cryptoQuoteDF = spark.createDataFrame(cryptoQuoteRDD, CryptoQuote.class);

        /** Split the data into training and test sets (70% training and 30% testing) */
        Dataset<Row>[] splits = cryptoQuoteDF.randomSplit(new double[]{0.7, 0.3});
        Dataset<Row> trainingData = splits[0];
        Dataset<Row> testData = splits[1];

        /** Train a Random Forest regression model */
        RandomForestRegressor rf = new RandomForestRegressor()
                .setLabelCol("price")
                .setFeaturesCol("timestamp")
                .setNumTrees(100); /** Number of trees in the forest */
        RandomForestRegressionModel rfModel = rf.fit(trainingData);

        /** Make predictions on the test data */
        Dataset<Row> predictions = rfModel.transform(testData);

        /** Show the predicted prices */
        predictions.show();

        /** Stop Spark */
        spark.stop();
    }
}
