package com.company.Models.Client;

import com.company.Controllers.Client.ClientRequestController;
import com.company.Controllers.Client.ClientResponseController;
import com.company.Models.Request;
import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class Client {
    final private static String PORT_NUMBER_FILE_ADDRESS = "config.properties";
    private static String sererIP;
    private static int portNumber;
    private static String AuthToken = null;

    private static boolean connected = false;

    private static ClientRequestController requestController;
    private static ClientResponseController responseController;
    private static Socket clientSocket;

    public static boolean setClientUp() {
        if (connectToTheServer()) {
            setResponseController();
            setRequestController();
            return true;
        }
        return false;
    }

    public static boolean connectToTheServer() {
        try {
            clientSocket = new Socket(sererIP, portNumber);
            setConnected(true);
        } catch (IOException e) {
            System.err.println("Server is not ready yet,press your button and come back later");
            return false;
        }
        return true;
    }

    public static void setConnected(boolean connected) {
        Client.connected = connected;
    }

    public static boolean isConnected() {
        return connected;
    }

    private static void setResponseController() {
        try {
            responseController = new ClientResponseController(clientSocket.getInputStream());
            responseController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setRequestController() {
        try {
            requestController = new ClientRequestController(clientSocket.getOutputStream());
            requestController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientRequestController getRequestController() {
        return requestController;
    }

    public static ClientResponseController getResponseController() {
        return responseController;
    }

    public static void main(String[] args) {
        while (!Client.isConnected()) {
            if (Client.setClientUp()) {
                Request request = new Request();
                request.setType(Request.Type.LOGIN);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", "ali");
                request.setContent(jsonObject);
                Client.getRequestController().sendRequest(request);
            } else {
                System.err.println("connection failed");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setSererIP(String sererIP) {
        Client.sererIP = sererIP;
    }

    public static void setPortNumber(int portNumber) {
        Client.portNumber = portNumber;
    }
}