package com.company.Controllers.graphic;

import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CollectionController {
    public JFXTextField deckName;
    public JFXButton createDeck;
    public VBox deckContainer;
    public static ErrorType creatDeckErrorType;


    public void createDeck(ActionEvent actionEvent) {
        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(deckName.getText());
        if(creatDeckErrorType==null) {
            HBox hBox=new HBox();
            hBox.getStyleClass().add("dechbox_collection");
            Label decklabel = new Label(deckName.getText());
            decklabel.getStyleClass().add("collection-deck");
            hBox.getChildren().add(decklabel);
            Image image = new Image("com/company/Views/graphic/images/waste-bin.png");
            ImageView imageView = new ImageView(image);
            imageView.setId(deckName.getText());
            imageView.getStyleClass().add("deck_image_delete");
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            hBox.getChildren().add(imageView);
            deckContainer.getChildren().add(hBox);

            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(((ImageView) event.getSource()).getId());
                    deckContainer.getChildren().remove(hBox);
                }
            });


        }
    }

    public static void assigncreatDeckErrorType(ErrorType errorType){
        creatDeckErrorType=errorType;
    }
}
