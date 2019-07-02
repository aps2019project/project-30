package com.company.Controllers.Client;

import com.company.Models.Request;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientRequestController extends Thread{
    private PrintStream printer;
    private BlockingQueue<Request> ClientRequests = new LinkedBlockingQueue<>();

    public ClientRequestController(OutputStream output) {
        printer = new PrintStream(output);
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                printer.println(new Gson().toJson(ClientRequests.take()));
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendRequest (Request request) {
        try {
            ClientRequests.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
