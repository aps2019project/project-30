package com.company.Controllers.graphic;

import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    public JFXMasonryPane cardContainer;
    public ImageView back;
    public TextField search;
    public void updateDecks(){
        updateShop("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back.setOnMouseClicked(event -> {
            RootsController.backToMainMenu();
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            updateShop(newValue);
        }));
    }

    private void updateShop(String newValue) {
        List<Card> cards;
        if (newValue.isEmpty())
            cards = Shop.getShopCollection().getCards();
        else
            cards = com.company.Controllers.ShopController.searchCardsByName(newValue);
        cardContainer.getChildren().clear();
        for (Card card : cards) {
            AnchorPane cardContainer = new AnchorPane();

            try {
                StackPane cardViewContainer = new StackPane();
                Image cardGif;
                if (card instanceof Spell)
                    cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + ".gif");
                else
                    cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_breathing.gif");
                ImageView cardView = new ImageView(cardGif);
                cardViewContainer.getChildren().add(cardView);
                cardContainer.getChildren().add(cardViewContainer);
                cardViewContainer.setPrefWidth(200);
                cardViewContainer.setPrefHeight(200);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("===" + e.getMessage());
            }

            cardContainer.setPrefSize(200, 262);
            cardContainer.getStyleClass().add("card-shop-container");
            Label title = new Label(card.getName().toUpperCase());
            title.getStyleClass().add("card-shop-name");
            title.setPrefWidth(200);
            cardContainer.getChildren().add(title);
            AnchorPane.setTopAnchor(title, 220.0);
            cardContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    boolean cardExist=false;
                    for(Card c:Account.getLoggedInAccount().getCollection().getCards()){
                        if(card.getName().equals(c.getName())){
                            cardExist=true;
                            com.company.Controllers.ShopController.sell(Account.getLoggedInAccount(), c.getId());
                            break;
                        }
                    }
                    if(!cardExist) {
                        com.company.Controllers.ShopController.buy(
                                Account.getLoggedInAccount(), card.getName());
                    }
                }
            });

            this.cardContainer.getChildren().add(cardContainer);
        }
    }
}
