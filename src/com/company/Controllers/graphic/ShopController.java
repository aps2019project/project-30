package com.company.Controllers.graphic;

import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    public JFXTabPane tabPane;
    public JFXMasonryPane cardforsellContainer;
    public JFXMasonryPane cardforbuyContainer;
    public AnchorPane main;
    public ImageView back;
    public TextField search;

    public void updateDecks() {
        updateShopSell("");
        updateShopBuy("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back.setOnMouseClicked(event -> {
            RootsController.backToMainMenu();
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (tabPane.getSelectionModel().getSelectedItem().getId().equals("sell"))
                updateShopSell(newValue);
            else
                updateShopBuy(newValue);
        }));
    }

    private void updateShopSell(String newValue) {
        List<Card> cards;
        if (newValue.isEmpty())
            cards = Account.getLoggedInAccount().getCollection().getCards();
        else
            cards = com.company.Controllers.CollectionController.searchCardsByName(newValue);
        cardforsellContainer.getChildren().clear();
        for (Card card : cards) {
            AnchorPane cardContainer = new AnchorPane();
            createCardContainer(card, cardContainer);
            this.cardforsellContainer.getChildren().add(cardContainer);
        }
    }


    private void updateShopBuy(String newValue) {
        List<Card> cards;
        if (newValue.isEmpty())
            cards = Shop.getShopCollection().getCards();
        else
            cards = com.company.Controllers.ShopController.searchCardsByName(newValue);
        cardforbuyContainer.getChildren().clear();
        for (Card card : cards) {
            AnchorPane cardContainer = new AnchorPane();
            createCardContainer(card, cardContainer);
            this.cardforbuyContainer.getChildren().add(cardContainer);
        }
    }

    private void createCardContainer(Card card, AnchorPane cardContainer) {
        try {
            StackPane cardViewContainer = new StackPane();
            Image cardGif;
            if (card instanceof Spell)
                cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_actionbar.gif");
            else
                cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_idle.gif");
            ImageView cardView = new ImageView(cardGif);
            cardView.setFitWidth(150);
            cardView.setFitHeight(150);
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
                buyOrSell(card);
            }
        });
    }

    private void buyOrSell(Card card) {
        if (tabPane.getSelectionModel().getSelectedItem().getId().equals("sell")) {
            com.company.Controllers.ShopController.sell(Account.getLoggedInAccount(), card.getId());
            updateShopSell(search.getText());
        } else {
            com.company.Controllers.ShopController.buy(
                    Account.getLoggedInAccount(), card.getName());

        }
    }

    public void openCustomCardPage(ActionEvent actionEvent) {
        RootsController.openCustomCardGenerator();
    }
}
