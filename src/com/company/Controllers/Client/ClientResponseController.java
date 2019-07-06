package com.company.Controllers.Client;

import com.company.Controllers.graphic.RootsController;
import com.company.Models.Client.Client;
import com.company.Models.Property;
import com.company.Models.Response;
import com.company.Models.User.Account;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            case Response.Codes.ACCOUNTS_INFO:
                JsonArray jsonArray = response.getContent().get("accountsList").getAsJsonArray();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++)
                    list.add(jsonArray.get(i).getAsString());
                RootsController.openScoreBoardMenu(list);
                break;
            case Response.Codes.SUCCESSFUL_LOG_OUT:
                Account.logout();
                System.exit(0);
                break;
            case Response.Codes.MESSAGE_NOTIFY:
                System.out.println(response.getContent().get("message").getAsString()); //todo : Graphic
                break;
        }
    }
}
