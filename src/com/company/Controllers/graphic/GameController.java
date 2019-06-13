package com.company.Controllers.graphic;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GameController {

    public HBox handContainer;
    public Button endTurn;
    public VBox graveyard;
    public Label player1name;
    public Label player2name;
    public HBox manaContainer1;
    public HBox manaContainer2;
    public AnchorPane gameRoot;

    public void init() {
//        if (!Battle.getPlayingBattle().getTurnToPlay().getName().equals(Account.getLoggedInAccount().getUsername())) {
//            endTurn.setDisable(true);
//        }
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label();
                if (Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1).getCardInCell() != null)
                    label.setText(Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1).getCardInCell().getName());
                gridPane.add(label, i, j);
                label.setPrefWidth(70);
                label.setPrefHeight(70);
                label.getStyleClass().add("tile-default");
            }
        }
        gameRoot.getChildren().add(gridPane);
        AnchorPane.setLeftAnchor(gridPane, 20.0);
        PerspectiveTransform e = new PerspectiveTransform();
        e.setUlx(400);    // Upper left
        e.setUly(300);
        e.setUrx(900);    // Upper right
        e.setUry(300);
        e.setLlx(200);      // Lower left
        e.setLly(800);
        e.setLrx(1100);    // Lower right
        e.setLry(800);
        gridPane.setEffect(e);
        player1name.setText(Battle.getPlayingBattle().getPlayers()[0].getName());
        player2name.setText(Battle.getPlayingBattle().getPlayers()[1].getName());
        updateMana();
        for (Card card : Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards()) {
            AnchorPane pane = new AnchorPane();
            StackPane handCard = new StackPane();
//            ImageView imageView = new ImageView(new Image("com/company/Views/graphic/images/test1.gif"));
            Label label = new Label(card.getName());
            handCard.setPrefWidth(180);
            handCard.setPrefHeight(180);
            handCard.getChildren().add(label);
            handCard.getStyleClass().add("game-hand-card-container");

            ImageView mana = new ImageView(new Image("com/company/Views/graphic/images/mana.png"));
            mana.setFitWidth(45);
            mana.setFitHeight(45);
            pane.getChildren().add(handCard);
            pane.getChildren().add(mana);
            AnchorPane.setTopAnchor(handCard, 0.0);
            AnchorPane.setTopAnchor(mana, 140.0);
            AnchorPane.setLeftAnchor(mana, 70.0);
            handContainer.getChildren().add(pane);
        }
    }

    public void endTurn(ActionEvent actionEvent) {
        endTurn.setDisable(true);
    }

    public void updateMana() {
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 9; i++) {
                ImageView imageView;
                if (i <= Battle.getPlayingBattle().getPlayers()[j].getMaxMana()) {
                    Image image = new Image("com/company/Views/graphic/images/mana.png");
                    imageView = new ImageView(image);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                } else {
                    Image image = new Image("com/company/Views/graphic/images/mana-inactive.png");
                    imageView = new ImageView(image);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                }
                if (j == 0)
                    manaContainer1.getChildren().add(imageView);
                else
                    manaContainer2.getChildren().add(imageView);
            }
        }
    }
}
