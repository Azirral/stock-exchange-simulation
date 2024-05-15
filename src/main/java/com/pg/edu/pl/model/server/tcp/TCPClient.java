package com.pg.edu.pl.model.server.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void runUser() throws IOException {
        // create a socket to connect to the server running on localhost at port number 9090
        Socket socket = new Socket("localhost", 9090);

        // Setup output stream to send data to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Setup input stream to receive data from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());

        // Send message to the server
        out.println("Hello from client!");


        while (true) {
            // Receive response from the server
            String response = in.readLine();
            System.out.println("Server says: " + response);

            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            out.println(message);

            if (out.toString().equals("exit"))
                break;

        }
        // Close the socket
        socket.close();
    }
}
