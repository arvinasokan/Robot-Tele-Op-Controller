package com.example.arvin.myapplication;

/**
 * Created by arvin on 10/7/2015.
 */
import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.StrictMode;
import android.os.Build.VERSION;

public class UDPSender {

    private String ip="";
    private int port=9001;
    public byte[] str = new byte[1024];
    DatagramSocket client_socket = null;
    private boolean connected = false;


    public UDPSender(Context applicationContext) {
    }

    public boolean client_open() throws IOException{
        return true;
    }
    public void send_data(int[] buttonpress, String ip_address)throws IOException{
        String messageStr=Arrays.toString(buttonpress);
        int server_port = port;
        DatagramSocket s = new DatagramSocket();
        InetAddress local = InetAddress.getByName(ip_address);
        int msg_length=messageStr.length();
        byte[] message = messageStr.getBytes();
        DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
        s.send(p);
        s.close();
    }

    public void close_socket() {
        //s.close();
    }


}

