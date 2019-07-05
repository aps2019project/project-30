package com.company.Models.Server;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Controllers.Client.ClientRequestController;
import com.company.Controllers.Client.ClientResponseController;
import com.company.Controllers.Server.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

    final private static String PORT_NUMBER_FILE_ADDRESS = "config.properties";

    static{
        AccountController.LoadSavedAccountsAndAddToAccounts();
        BattleController.loadSavedGamesAndAddToSavedGamesList();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(readingPortNumberFromFile());
        while (true) {
            Socket client = server.accept();
            new ClientController(client);
        }
    }

    private static void setPortNumberOnPropertiesFile(ServerSocket server) throws IOException {
        File portNumberFile = new File(PORT_NUMBER_FILE_ADDRESS);
        if (!portNumberFile.exists()) {
            portNumberFile.createNewFile();
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(portNumberFile));
        String port = String.valueOf(server.getLocalPort());
        System.out.println(port);
        properties.setProperty("port", port);
        properties.store(new FileOutputStream(PORT_NUMBER_FILE_ADDRESS), null);
    }


    private static int readingPortNumberFromFile() {
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
