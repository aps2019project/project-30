package com.company.Controllers.Server;

import com.company.Controllers.AccountController;
import com.company.Models.Request;
import com.company.Models.Response;
import com.company.Models.User.Account;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import javax.security.auth.login.LoginException;
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
                    handleRequest(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Request request) {
        switch (request.getType()){
            case LOGIN:
                signinHandler(request);
                break;
            case SIGNUP:
                signUpHandler(request);
                break;

        }
    }

    private void signinHandler(Request request) {
        String username = request.getContent().get("username").getAsString();
        String password = request.getContent().get("username").getAsString();
        Response response = new Response();
        JsonObject content = new JsonObject();
        try {
            String token = AccountController.login(username, password);
            response.setCode(Response.Codes.SUCCESSFUL_LOGIN);
            content.addProperty("token", token);
            Account account = Account.getAccountByUsername(username);
            client.setAccount(account);
            account.setClientController(client);
        } catch (LoginException e) {
            response.setCode(Response.Codes.BAD_LOGIN);
            content.addProperty("errorMessage", e.getMessage());
        }
        response.setContent(content);
        client.getServerResponseController().sendResponse(response);
    }

    private void signUpHandler(Request request) {
        String username = request.getContent().get("username").getAsString();
        String password = request.getContent().get("username").getAsString();
        Response response = new Response();
        try {
            AccountController.signup(username, password);
            response.setCode(Response.Codes.SUCCESSFUL_SIGNUP);
        } catch (Account.SignupException e) {
            response.setCode(Response.Codes.BAD_SIGNUP);
            e.printStackTrace();
        }
        client.getServerResponseController().sendResponse(response);
    }

}
