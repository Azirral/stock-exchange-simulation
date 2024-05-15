package com.pg.edu.pl;

// Java program to illustrate Client side
// Implementation using DatagramSocket
import com.pg.edu.pl.model.AppModuleUdp;
import com.pg.edu.pl.model.UserProfile;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient
{
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();
        AppModuleUdp appModuleUdp = new AppModuleUdp();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        byte data[] = new byte[65535];
        while (true)
        {
            AppModuleUdp.printMainMenu();
            String inp = sc.nextLine();
            buf = inp.getBytes();
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);
            ds.send(DpSend);
            System.out.println("server sent: " + data(data));
            DatagramPacket dpReceive = new DatagramPacket(data, data.length);
            ds.receive(dpReceive);
            if (String.valueOf(data(data)) == "coming") {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dpReceive.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                UserProfile userProfile = (UserProfile) objectInputStream.readObject();

                System.out.println("userProfile" + userProfile);
            }
            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;
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


