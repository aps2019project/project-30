package com.company.Controllers.Server;

import com.company.Models.Request;
import com.company.Models.Response;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.interrupted;

public class ServerResponseController extends Thread{
    private PrintStream printer;
    private BlockingQueue<Response> serverResponses = new LinkedBlockingQueue<>();
    private ClientController client;

    public ServerResponseController(ClientController client,OutputStream output) {
        printer = new PrintStream(output);
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                printer.println(new Gson().toJson(serverResponses.take()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(Response response) {
        try {
            serverResponses.put(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
