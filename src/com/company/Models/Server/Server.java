package com.company.Models.Server;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Controllers.AccountController;
import com.company.Controllers.Client.ClientRequestController;
import com.company.Controllers.Client.ClientResponseController;
import com.company.Controllers.Server.*;
import com.company.Controllers.ServerController;
import com.company.Views.ServerGraphic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

    static{
        AccountController.LoadSavedAccountsAndAddToAccounts();
        BattleController.loadSavedGamesAndAddToSavedGamesList();
    }

    public static void startServer() throws IOException {
        ServerSocket server = new ServerSocket(1010);
        new Thread(() -> {
            while (true) {
                System.out.println(":::: Online Accounts ::::");
                AccountController.getConnectedAccount().forEach(account -> {
                    System.out.println(account.getUsername());
                });
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (true) {
            Socket client = server.accept();
            new ClientController(client);
        }
    }

}
