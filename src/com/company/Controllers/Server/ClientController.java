package com.company.Controllers.Server;

import com.company.Models.User.Account;

import java.io.IOException;
import java.net.Socket;

public class ClientController {
    private Account account;
    private ServerRequestController serverRequestController;
    private ServerResponseController serverResponseController;
    private Socket clientSocket;

    public ClientController(Socket clientSocket) {
        this.clientSocket = clientSocket;
        setServerRequestController(this, clientSocket);
        setServerResponseController(this, clientSocket);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    private void setServerResponseController(ClientController client, Socket clientSocket) {
        try {
            serverResponseController = new ServerResponseController(client, clientSocket.getOutputStream());
            serverResponseController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setServerRequestController(ClientController client, Socket clientSocket) {
        try {
            serverRequestController = new ServerRequestController(client, clientSocket.getInputStream());
            serverRequestController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerRequestController getServerRequestController() {
        return serverRequestController;
    }

    public ServerResponseController getServerResponseController() {
        return serverResponseController;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
