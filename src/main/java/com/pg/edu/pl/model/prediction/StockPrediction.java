package com.pg.edu.pl.model.prediction;

import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.equityEntities.elements.Quote;
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
    public void predict() {
        /** Initialize Spark */
        SparkConf conf = new SparkConf().setAppName("PricePrediction").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession.builder().appName("PricePrediction").getOrCreate();

        /** Generate sample data (Replace this with your actual data loading) */
        List<Quote> quoteList = stockPredict.getQuotes().getQuotes();

        /** Create RDD from the list */
        JavaRDD<Quote> quoteRDD = sc.parallelize(quoteList);

        /** Convert RDD to DataFrame */
        Dataset<Row> quoteDF = spark.createDataFrame(quoteRDD, Quote.class);

        /** Split the data into training and test sets (70% training and 30% testing) */
        Dataset<Row>[] splits = quoteDF.randomSplit(new double[]{0.7, 0.3});
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
