package com.pg.edu.pl;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/** Class represents UDP client */
public class UdpClient
{
    public static void main(String args[]) throws IOException, ClassNotFoundException {

        /** Creating the socket in order to maintain connection to server, and a scanner to listen user input */
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        byte data[] = new byte[65535];
        while (true)
        {
            /** reading user input to be sent to server and sending it*/
            System.out.println("give stock symbol or type \"exit\": ");
            String inp = sc.nextLine();
            buf = inp.getBytes();
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);
            ds.send(DpSend);
            if (inp.equals("exit"))
                break;

            /** Recieving the object and printing */
            DatagramPacket dpReceive = new DatagramPacket(data, data.length);
            ds.receive(dpReceive);
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
            Object o = ois.readObject();
            ois.close();
            System.out.println(o.toString());
        }
    }

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


