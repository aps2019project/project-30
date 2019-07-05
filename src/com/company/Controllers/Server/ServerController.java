package com.company.Controllers.Server;

import com.company.Models.User.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Comparator;
import java.util.Properties;

public class ServerController {
    final private static String PORT_NUMBER_FILE_ADDRESS = "config.properties";

    public static void setPortNumberOnPropertiesFile(ServerSocket server,int portNumber) throws IOException {
        File portNumberFile = new File(PORT_NUMBER_FILE_ADDRESS);
        if (!portNumberFile.exists()) {
            portNumberFile.createNewFile();
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(portNumberFile));
        String port = String.valueOf(portNumber);
        System.out.println(port);
        properties.setProperty("port", port);
        properties.store(new FileOutputStream(PORT_NUMBER_FILE_ADDRESS), null);
    }

    public static int readingPortNumberFromFile() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(PORT_NUMBER_FILE_ADDRESS));
            String port = properties.getProperty("port");
            System.out.println(port);
            return Integer.parseInt(port);
        } catch (IOException e) {
            System.err.println("there is no port number on config file");
        }
        return 8000;
    }
}
