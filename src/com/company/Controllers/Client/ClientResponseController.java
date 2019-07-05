package com.company.Controllers.Client;

import com.company.Controllers.graphic.RootsController;
import com.company.Models.Client.Client;
import com.company.Models.Property;
import com.company.Models.Response;
import com.company.Models.User.Account;
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
                RootsController.openMainMenu();
                break;
            case Response.Codes.BAD_LOGIN:
                RootsController.setLoginErrorOnAuthenticate(
                        response.getContent().get(Property.ERROR_MESSAGE_PROPERTY).getAsString());
                break;
            case Response.Codes.SUCCESSFUL_SIGN_UP:
                RootsController.openLoginTab();
                break;
            case Response.Codes.BAD_SIGN_UP:
                RootsController.setSignUpErrorOnAuthenticate(
                        response.getContent().get(Property.ERROR_MESSAGE_PROPERTY).getAsString());
                break;
            case Response.Codes.SUCCESSFUL_LOG_OUT:
                Account.logout();
                System.exit(0);
                break;
        }
    }
}
