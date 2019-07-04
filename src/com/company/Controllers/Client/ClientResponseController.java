package com.company.Controllers.Client;

import com.company.Models.Client.Client;
import com.company.Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class ClientResponseController extends Thread {

    public InputStream input;

    public ClientResponseController(InputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try (InputStreamReader reader = new InputStreamReader(input)) {
            Gson gson = new Gson();
            JsonStreamParser parser = new JsonStreamParser(reader);
            while (Client.isConnected()) {
                if (parser.hasNext()) {
                    Response response = gson.fromJson(parser.next(), Response.class);
                    System.out.println("Server Response : code ---> " + response.getCode() + "\n" + response.getContent().toString());
                    handleResponse(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(Response response) {
        switch (response.getCode()) {
            case Response.Codes.SUCCESSFUL_LOGIN:
                break;
        }
    }
}
