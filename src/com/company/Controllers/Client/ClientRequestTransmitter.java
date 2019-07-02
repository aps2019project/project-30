package com.company.Controllers.Client;

import com.company.Models.Request;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientRequestTransmitter extends Thread{
    private PrintStream printer;
    private BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    public ClientRequestTransmitter(OutputStream output) {
        printer = new PrintStream(output);
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                printer.println(new Gson().toJson(requests.take()));
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendRequest (Request request) {
        requests.add(request);
    }
}
