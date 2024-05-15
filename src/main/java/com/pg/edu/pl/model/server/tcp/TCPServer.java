package com.pg.edu.pl.model.server.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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


        while(true) {
            // Read message from client
            String message = in.readLine();
            System.out.println("Client says: " + message);

            // Send response to the client
            out.println("Message received by the server.");

            if(message.equals("exit"))
                break;
        }
        // Close the client socket
        clientSocket.close();
        // Close the server socket
        serverSocket.close();
    }
}
