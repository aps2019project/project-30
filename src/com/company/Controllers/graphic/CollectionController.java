package com.company.Controllers.graphic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CollectionController {
    public JFXTextField deckName;
    public JFXButton createDeck;
    public VBox deckContainer;


    public void createDeck(ActionEvent actionEvent) {
        Label deck = new Label(deckName.getText());
        deck.getStyleClass().add("collection-deck");
        deckContainer.getChildren().add(deck);

    }
}
