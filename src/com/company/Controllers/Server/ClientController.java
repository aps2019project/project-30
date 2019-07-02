package com.company.Controllers.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientController {

    private ServerRequestController requestController;
    private ServerResponseController responseController;

    public ClientController(Socket client) {
        setRequestController(client);
        setResponseController(client);
    }

    private void setResponseController(Socket clientSocket) {
        try {
            responseController = new ServerResponseController(clientSocket.getOutputStream());
            responseController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRequestController(Socket clientSocket) {
        try {
            requestController = new ServerRequestController(clientSocket.getInputStream());
            requestController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
