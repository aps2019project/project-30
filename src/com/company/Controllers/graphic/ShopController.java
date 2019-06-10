package com.company.Controllers.graphic;

import com.company.Models.Card.Card;
import com.company.Models.Shop;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    public JFXMasonryPane cardContainer;
    public ImageView back;
    public TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            List<Card> cards;
            if (newValue.isEmpty())
                cards = Shop.getShopCollection().getCards();
            else
                cards = com.company.Controllers.ShopController.searchCardsByName(newValue);
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
                cardContainer.getChildren().add(anchorPane);
            }
        }));
    }
}
