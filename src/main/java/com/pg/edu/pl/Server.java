package com.pg.edu.pl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.pg.edu.pl.model.server.tcp.TCPClient.runUser;

public class Server {
    public static void main(String[] args) throws IOException {
        runUser();
    }
}
