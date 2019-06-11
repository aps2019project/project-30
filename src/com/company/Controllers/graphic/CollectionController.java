package com.company.Controllers.graphic;

import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CollectionController {
    public JFXTextField deckName;
    public JFXButton createDeck;
    public VBox deckContainer;
    public static ErrorType creatDeckErrorType;


    public void createDeck(ActionEvent actionEvent) {
        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(deckName.getText());
        if(creatDeckErrorType==null) {
            Label deck = new Label(deckName.getText());
            deck.getStyleClass().add("collection-deck");
            deckContainer.getChildren().add(deck);
        }
    }

    public static void assigncreatDeckErrorType(ErrorType errorType){
        creatDeckErrorType=errorType;
    }
}
