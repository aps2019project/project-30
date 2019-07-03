package com.company.Controllers.Server;

import com.company.Models.Property;
import com.company.Models.Request;
import com.company.Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerRequestController extends Thread{
    public InputStream input;
    private ClientController client;

    public ServerRequestController(ClientController client ,InputStream input) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try (InputStreamReader reader = new InputStreamReader(input)) {
            Gson gson = new Gson();
            JsonStreamParser parser = new JsonStreamParser(reader);
            while (Thread.currentThread().isAlive()) {
                if (parser.hasNext()) {
                    Request request = gson.fromJson(parser.next(), Request.class);
                    System.out.println("Client Request : " + request.getContent().toString());
                    handleResponse(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(Request request) {
        switch (request.getType()){
            case LOGIN:
                Response response = new Response(Response.Codes.LOGIN,new Property("message","login"));
                client.getServerResponseController().sendResponse(response);
                break;
        }
    }

}
