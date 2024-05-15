package com.pg.edu.pl.model.server.tcp;

import com.pg.edu.pl.model.Account;
import com.pg.edu.pl.model.AppModuleUser;
import com.pg.edu.pl.model.UserProfile;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
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

        AppModuleUser appModuleUser = new AppModuleUser();
        appModuleUser.runApplication(outObject, inObject);
        // Close the socket
        socket.close();
    }


}
