package com.pg.edu.pl;

import com.pg.edu.pl.model.AppModuleUdpServer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/** Class represents UDP server */
public class UdpServer
{
    public static void main(String[] args) throws IOException
    {
        /** Creating a datagram socket in order to send and listen data in bytes. */
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        /** Initializing data in appModule */
        AppModuleUdpServer appModuleUdpServer = new AppModuleUdpServer();
        appModuleUdpServer.dataInit();
        DatagramPacket DpReceive = null;

        /** byte array is used to save the data which is to be sent or recieved */
        byte buf[] = null;
        while (true)
        {
            /** Receiving a packet */
            DpReceive = new DatagramPacket(receive, receive.length);
            ds.receive(DpReceive);
            System.out.println("Client:" + data(receive));
            if (String.valueOf(data(receive)) == "exit"){
                break;
            }
            /** creating object to be sent and converting it to byte array. */
            Object o = AppModuleUdpServer.stocks.findStock(String.valueOf(data(receive)));
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
            os.flush();
            os.writeObject(o);
            os.flush();

            /** Sending the byte array to the client using datagram packet. "DpReceive.getSocketAddress" holds data
             * about the address from which the data was sent in "DpReceive" */
            byte[] sendBuf = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, DpReceive.getSocketAddress());
            ds.send(packet);
            os.close();
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
