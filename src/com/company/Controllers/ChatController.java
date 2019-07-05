package com.company.Controllers;

import com.company.Models.Property;
import com.company.Models.Response;

import java.util.LinkedList;

public class ChatController {
    private static LinkedList<String> chats = new LinkedList<>();

    public static void newMessage(String username, String message) {
        String formatedMessage = String.format("%s :: %s", username, message);
        chats.addLast(formatedMessage);
        notifyAllConnectedUsers(formatedMessage);
    }

    private static void notifyAllConnectedUsers(String message) {
        AccountController.getConnectedAccount().forEach(account -> {
            account.getClientController().getServerResponseController().sendResponse(
                    new Response(Response.Codes.MESSAGE_NOTIFY, new Property("message", message)));
        });
    }
}
