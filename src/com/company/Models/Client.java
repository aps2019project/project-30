package com.company.Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class Client {
    final private static String PORT_NUMBER_FILE_ADDRESS = "config.properties";
    final private static String SERVER_IP = "localhost";

    private static boolean isConnected = false;
    private static Socket clientSocket;


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
}