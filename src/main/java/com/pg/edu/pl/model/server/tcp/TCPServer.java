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

    /**
     * This method starts a server that listens on port 9090 for client connections.
     * When a client connects, it sets up the input and output streams for communication.
     * The server continuously listens for serialized objects from the client.
     * If the received object is an instance of {@code Stock}, it processes the object
     * using the {@code threading} method and sends back a {@code StockPrediction} object.
     * If the received object is a {@code String} and the message is "exit",
     * the server will terminate the connection.
     *
     * @throws IOException if an I/O error occurs when waiting for a connection.
     */
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

        while(true) {
            try {
                //Requirement LAB 6 Receiving Serialized object
                Object o = inObject.readObject();
                if(o instanceof Stock) {
                    Stock stock = (Stock) o;
                    System.out.println("Received UserCarParameters from client: " + stock);

                    StockPrediction prediction = threading(4, stock);

                    outObject.writeObject(prediction);
                }
                else if (o instanceof String) {
                    String message = (String) o;
                    if (message.equals("exit"))
                        break;
                }
            } catch (ClassNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        }
        // Close the client socket
        clientSocket.close();
        // Close the server socket
        serverSocket.close();
    }


    /**
     * Perform multi-threaded stock predictions.
     *
     * @param threads Number of threads to use
     * @param stock Stock on which the Regression analysis will be performed
     */
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






