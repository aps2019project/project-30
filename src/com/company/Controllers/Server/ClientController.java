package com.company.Controllers.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientController {

    private ServerRequestController serverRequestController;
    private ServerResponseController serverResponseController;

    public ClientController(Socket clientSocket) {
        setServerRequestController(this, clientSocket);
        setServerResponseController(this, clientSocket);
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
}
