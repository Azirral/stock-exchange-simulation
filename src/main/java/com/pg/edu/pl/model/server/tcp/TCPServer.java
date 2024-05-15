package com.pg.edu.pl.model.server.tcp;

import com.pg.edu.pl.model.AppModule;
import com.pg.edu.pl.model.AppModuleUser;
import com.pg.edu.pl.model.UserProfile;
import com.pg.edu.pl.model.equityEntities.categories.Stock;
import com.pg.edu.pl.model.prediction.StockPrediction;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;

public class TCPServer {

    public static void runServer() throws IOException {

        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server is running and waiting for client connection...");

        // Accept incoming client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        // Setup input and output streams for communication with the client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        ObjectOutputStream outObject = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inObject = new ObjectInputStream(clientSocket.getInputStream());

        try {
            //Requirement LAB 6 Receiving Serialized object
            Stock stock = (Stock) inObject.readObject();
            System.out.println("Received UserCarParameters from client: " + stock);

            StockPrediction prediction = threading(4, stock);

            outObject.writeObject(prediction);
        } catch (ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }


        // Close the client socket
        clientSocket.close();
        // Close the server socket
        serverSocket.close();
    }

    public static StockPrediction threading(int threads, Stock stock) throws ParseException {
            if (stock.getQuotes() != null) {
                StockPrediction predictor = new StockPrediction("Stock Price Prediction",
                        "Using regression analysis", null, null, new Date(), stock);
                predictor.predictLinearMultiThreads(threads);
                return predictor;
            }
        return null;
    }
}






