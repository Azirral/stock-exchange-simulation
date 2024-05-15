package com.pg.edu.pl;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static com.pg.edu.pl.model.server.tcp.TCPClient.runUser;
import static com.pg.edu.pl.model.server.tcp.TCPServer.runServer;

public class User {
    public static void main(String[] args) throws IOException {
        runUser();
    }
}
