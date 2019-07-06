package com.company.Controllers.graphic;

import com.company.Models.Client.Client;
import com.company.Models.Property;
import com.company.Models.Request;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatController {
    public VBox chats;
    public Button send;
    public TextField newMessage;

    public void sendMessage(ActionEvent actionEvent) {
        Client.getRequestController().sendRequest(
                new Request(Request.Type.NEW_MESSAGE, new Property("message", newMessage.getText())));
        newMessage.setText("");
    }

    public void addChatToChats(String chat) {
        chats.getChildren().add(new Label(chat));
    }
}
