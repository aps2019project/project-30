package com.company.Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class Client {
    final private static String PORT_NUMBER_FILE_ADDRESS = "config.properties";
    final private static String SERVER_IP = "localhost";

    private static boolean connected = false;
    private static Socket clientSocket;

    public static boolean connectToTheServer() {
        try {
            clientSocket = new Socket(SERVER_IP, readingPortNumberFromFile());
            setConnected(true);
        } catch (IOException e) {
            System.err.println("Server is not ready yet,press your button and come back later");
            return false;
        }
        return true;
    }

    private static int readingPortNumberFromFile() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(PORT_NUMBER_FILE_ADDRESS));
            String port = properties.getProperty("port");
            System.out.println(port);
            return Integer.parseInt(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 8000;
    }

    public static void setConnected(boolean connected) {
        Client.connected = connected;
    }

    public static boolean isConnected() {
        return connected;
    }
}