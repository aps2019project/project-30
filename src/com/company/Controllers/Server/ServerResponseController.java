package com.company.Controllers.Server;

import com.company.Models.Request;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.interrupted;

public class ServerResponseController extends Thread{
    private PrintStream printer;
    private BlockingQueue<Request> serverResponses = new LinkedBlockingQueue<>();

    public ServerResponseController(OutputStream output) {
        printer = new PrintStream(output);
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                printer.println(new Gson().toJson(serverResponses.take()));
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendRequest (Request request) {
        try {
            serverResponses.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
