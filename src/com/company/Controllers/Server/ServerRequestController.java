package com.company.Controllers.Server;

import com.company.Controllers.AccountController;
import com.company.Controllers.ChatController;
import com.company.Controllers.JsonController;
import com.company.Models.Card.Card;
import com.company.Models.Property;
import com.company.Models.Request;
import com.company.Models.Response;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

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
            case NEW_MESSAGE:
                newMessageHandler(request);
                break;
            case BUY:
                buyHandeler(request);
                break;
            case SELL:
                sellHandler(request);
                break;
            case SHOPBUY:
                shopBuyHandler(request);
                break;
            case SHOPSELL:
                shopSellHandler(request);
                break;
        }
    }

    private void newMessageHandler(Request request) {
        ChatController.newMessage(
                client.getAccount().getUsername(),
                request.getContent().get("message").getAsString());
    }

    private void scoreboard() {
        ServerAccountController.sortPlayers();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < Account.getAccounts().size(); i++) {
            Account account = Account.getAccounts().get(i);
            jsonArray.add((i + 1) + "-> " + account.getUsername() + " " + account.getWins());
        }
        Response response = new Response(Response.Codes.ACCOUNTS_INFO);
        JsonObject content = new JsonObject();
        content.add("accountsList", jsonArray);
        response.setContent(content);
        client.getServerResponseController().sendResponse(response);
    }

    private void disconnectHandler() {
        client.getServerResponseController().sendResponse(
                new Response(Response.Codes.SUCCESSFUL_LOG_OUT)
        );
        client.setServerRequestController(null);
        client.setServerResponseController(null);
    }

    private void signinHandler(Request request) {
        String username = request.getContent().get(Property.USERNAME_PROPERTY).getAsString();
        String password = request.getContent().get(Property.PASSWORD_PROPERTY).getAsString();
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
            ServerAccountController.saveAccounts();
        } catch (Account.SignupException e) {
            response = new Response(Response.Codes.BAD_SIGN_UP);
        }
        client.getServerResponseController().sendResponse(response);
    }

    private void buyHandeler(Request request){
        String name=request.getContent().get(Property.CARDID_PROPERTY).getAsString();
        if(Shop.getNumberofcar().get(name)>=1){
            com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), name);
            int n=Shop.getNumberofcar().get(name);
            Shop.getNumberofcar().remove(name);
            Shop.getNumberofcar().put(name,n-1);
        }

    }

    private void sellHandler(Request request){
        String id=request.getContent().get(Property.CARDID_PROPERTY).getAsString();
        String name="";
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()){
            if(card.getId().equals(id)) {
                name = card.getName();
                break;
            }
        }
        int n=Shop.getNumberofcar().get(name);
        Shop.getNumberofcar().remove(name);
        Shop.getNumberofcar().put(name,n+1);
        com.company.Controllers.ShopController.sell(Account.getLoggedInAccount(), id);
    }

    private void shopBuyHandler(Request request){
        Response response=new Response(Response.Codes.SENT_CARDS,new Property(Property.SHOP_BUYCARD,JsonController.getGson().toJson(Shop.getShopCollection().getCards())));
        client.getServerResponseController().sendResponse(response);
    }

    private void shopSellHandler(Request request){
        Response response=new Response(Response.Codes.SHOLSELCARD_SENT,new Property(Property.SHOP_SELLCARD,JsonController.getGson().toJson(Account.getLoggedInAccount().getCollection().getCards())));
        client.getServerResponseController().sendResponse(response);
    }
}


