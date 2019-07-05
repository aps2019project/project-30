package com.company.Controllers.Server;

import com.company.Controllers.AccountController;
import com.company.Controllers.JsonController;
import com.company.Models.Property;
import com.company.Models.Request;
import com.company.Models.Response;
import com.company.Models.User.Account;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerRequestController extends Thread {
    public InputStream input;
    private ClientController client;

    public ServerRequestController(ClientController client, InputStream input) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try (InputStreamReader reader = new InputStreamReader(input)) {
            Gson gson = new Gson();
            JsonStreamParser parser = new JsonStreamParser(reader);
            while (Thread.currentThread().isAlive()) {
                if (client.isConnected() && parser.hasNext()) {
                    Request request = gson.fromJson(parser.next(), Request.class);
                    System.out.println("Client Request: " + request.getType().toString() + " ---> " + request.getContent().toString());
                    handleRequest(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Request request) {
        switch (request.getType()) {
            case LOGIN:
                signinHandler(request);
                break;
            case SIGN_UP:
                signUpHandler(request);
                break;
            case SCOREBOARD:
                scoreboard();
                break;
            case DISCONNECT:
                disconnectHandler();
                break;

        }
    }

    private void scoreboard() {
        ServerAccountController.sortPlayers();
        JsonObject jsonObject = new JsonObject();
        for (Account account:Account.getAccounts()) {
            jsonObject.addProperty(account.getUsername(),account.getWins());
        }
        client.getServerResponseController().sendResponse(new Response(Response.Codes.SUCCESSFUL_SCOREBOARD,jsonObject));
    }

    private void disconnectHandler() {
        client.getServerResponseController().sendResponse(
                new Response(Response.Codes.SUCCESSFUL_LOG_OUT)
        );
        client.setServerRequestController(null);
        client.setServerResponseController(null);
    }

    private void signinHandler(Request request) {
        String username = request.getContent().get("username").getAsString();
        String password = request.getContent().get("username").getAsString();
        Response response;
        try {
            String token = AccountController.login(username, password);
            response = new Response(Response.Codes.SUCCESSFUL_LOGIN, new Property("token", token));
            Account account = Account.getAccountByUsername(username);
            client.setAccount(account);
            account.setClientController(client);
        } catch (LoginException e) {
            response = new Response(Response.Codes.BAD_LOGIN, new Property(Property.ERROR_MESSAGE_PROPERTY, e.getMessage()));
        }
        client.getServerResponseController().sendResponse(response);
    }

    private void signUpHandler(Request request) {
        String username = request.getContent().get(Property.USERNAME_PROPERTY).getAsString();
        String password = request.getContent().get(Property.PASSWORD_PROPERTY).getAsString();
        Response response;
        try {
            AccountController.signup(username, password);
            response = new Response(Response.Codes.SUCCESSFUL_SIGN_UP);
        } catch (Account.SignupException e) {
            response = new Response(Response.Codes.BAD_SIGN_UP);
        }
        client.getServerResponseController().sendResponse(response);
    }

}
