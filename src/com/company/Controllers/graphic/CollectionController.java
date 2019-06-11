package com.company.Controllers.graphic;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class  CollectionController implements Initializable     {
    public JFXTextField deckName;
    public JFXButton createDeck;
    public VBox deckContainer;
    public static ErrorType creatDeckErrorType;
    public JFXMasonryPane cardContainer;
    public ImageView back;
    public TextField search;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("hi");
            List<Card> cards;
            if(newValue.isEmpty())
                cards=Account.getLoggedInAccount().getCollection().getCards();
            else
                cards=com.company.Controllers.CollectionController.searchCardsByName(newValue);
            cardContainer.getChildren().clear();
            for (Card card : cards) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefSize(200, 262);
                anchorPane.getStyleClass().add("card-shop-container");
                Label label = new Label(card.getName().toUpperCase());
                label.getStyleClass().add("card-shop-name");
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(200);
                anchorPane.getChildren().add(label);
                AnchorPane.setTopAnchor(label, 220.0);
                anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                    }
                });
            }

        }));
    }
}
