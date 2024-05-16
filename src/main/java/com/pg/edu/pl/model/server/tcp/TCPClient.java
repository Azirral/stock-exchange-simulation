package com.pg.edu.pl.model.server.tcp;

import com.pg.edu.pl.model.Account;
import com.pg.edu.pl.model.AppModuleUser;
import com.pg.edu.pl.model.UserProfile;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TCPClient {

    /**
     * This method establishes a connection to a server running on localhost at port 9090.
     * It sets up the necessary input and output streams for communication with the server.
     * The method creates an instance of {@code AppModuleUser} and runs the application
     * logic by invoking its {@code runApplication} method, passing the object streams as
     * parameters. After the application logic is executed, the method closes the socket.
     *
     * @throws IOException if an I/O error occurs when creating the socket or setting up the streams.
     */
    public static void runUser() throws IOException {
        // create a socket to connect to the server running on localhost at port number 9090
        Socket socket = new Socket("localhost", 9090);
        ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());

        AppModuleUser appModuleUser = new AppModuleUser();
        appModuleUser.runApplication(outObject, inObject);
        // Close the socket
        socket.close();
    }


}
