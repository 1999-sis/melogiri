package com.example.melogiri.controller;

import java.net.InetSocketAddress;
import java.net.Socket;

public class PortChecker {

    public static boolean isPortOpen(String ipAddress, int port) {
        try {
            // Create a socket with a timeout
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, port), 1000); // 1000 milliseconds timeout
            socket.close();
            return true; // Port is open
        } catch (Exception e) {
            return false; // Port is closed or unreachable
        }
    }
}
