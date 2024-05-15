package com.pg.edu.pl;

// Java program to illustrate Server side
// Implementation using DatagramSocket
import com.pg.edu.pl.model.AppModuleUdpServer;
import com.pg.edu.pl.model.UserProfile;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer
{
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];
        AppModuleUdpServer appModuleUdpServer = new AppModuleUdpServer();
        appModuleUdpServer.dataInit();
        DatagramPacket DpReceive = null;

        byte buf[] = null;
        InetAddress ip = InetAddress.getLocalHost();
        while (true)
        {
            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : receive the data in byte buffer.
            ds.receive(DpReceive);
            System.out.println("Client:" + data(receive));
            switch (String.valueOf(data(receive))) {
                case "1":

                    String promptUN = "provide username: ";
                    buf = promptUN.getBytes();
                    DatagramPacket sendPromptUN = new DatagramPacket(buf, buf.length, DpReceive.getSocketAddress());
                    ds.send(sendPromptUN);
                    ds.receive(DpReceive);
                    String username = String.valueOf(data(receive));
                    System.out.println(username);

                    String promptP = "provide password: ";
                    buf = promptP.getBytes();
                    DatagramPacket sendPromptP = new DatagramPacket(buf, buf.length, DpReceive.getSocketAddress());
                    ds.send(sendPromptP);
                    ds.receive(DpReceive);
                    String password = String.valueOf(data(receive));

                    String coming = "coming";
                    buf = coming.getBytes();
                    DatagramPacket sendComing = new DatagramPacket(buf, buf.length, DpReceive.getSocketAddress());
                    ds.send(sendComing);
                    UserProfile up = appModuleUdpServer.runLogIn(username, password);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(up);
                    objectOutputStream.flush();
                    byte[] buffer = byteArrayOutputStream.toByteArray();
                    DatagramPacket sendObj = new DatagramPacket(buffer, buffer.length, DpReceive.getSocketAddress());
                    ds.send(sendObj);
            }
//            buf = (.getBytes();
//            DatagramPacket dpSend = new DatagramPacket(buf, buf.length, DpReceive.getSocketAddress());

            //ds.send(dpSend);
            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }


    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
