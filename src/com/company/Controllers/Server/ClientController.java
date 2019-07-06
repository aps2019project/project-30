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
        setServerRequestControllerUp(this, clientSocket);
        setServerResponseControllerUp(this, clientSocket);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    private void setServerResponseControllerUp(ClientController client, Socket clientSocket) {
        try {
            serverResponseController = new ServerResponseController(client, clientSocket.getOutputStream());
            serverResponseController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setServerRequestControllerUp(ClientController client, Socket clientSocket) {
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

    public void setServerRequestController(ServerRequestController serverRequestController) {
        this.serverRequestController = serverRequestController;
    }

    public void setServerResponseController(ServerResponseController serverResponseController) {
        this.serverResponseController = serverResponseController;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public boolean isConnected() {
        return serverRequestController != null;
    }

}
