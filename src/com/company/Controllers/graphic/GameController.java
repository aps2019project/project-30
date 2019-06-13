package com.company.Controllers.graphic;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public Pane tableContainer;

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
        PerspectiveTransform e = new PerspectiveTransform();
        e.setUlx(tableContainer.getLayoutX() + 150);    // Upper left
        e.setUly(tableContainer.getLayoutY());
        e.setUrx(tableContainer.getLayoutX() + 850);    // Upper right
        e.setUry(tableContainer.getLayoutY());
        e.setLlx(tableContainer.getLayoutX());      // Lower left
        e.setLly(tableContainer.getLayoutY() + 300);
        e.setLrx(tableContainer.getLayoutX() + 1000);    // Lower right
        e.setLry(tableContainer.getLayoutY() + 300);
//        gridPane.setEffect(e);
        tableContainer.getChildren().add(gridPane);
        player1name.setText(Battle.getPlayingBattle().getPlayers()[0].getName());
        player2name.setText(Battle.getPlayingBattle().getPlayers()[1].getName());
        updateMana();
        for (Card card : Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards()) {
            AnchorPane pane = new AnchorPane();
            StackPane handCard = new StackPane();
//            ImageView imageView = new ImageView(new Image("com/company/Views/graphic/images/test1.gif"));
            Label cardName = new Label(card.getName());
            handCard.setPrefWidth(180);
            handCard.setPrefHeight(180);
            handCard.getChildren().add(cardName);
            handCard.getStyleClass().add("game-hand-card-container");

            StackPane manaContainer = new StackPane();
            Label manaNum = new Label(String.valueOf(card.getManaPoint()));
            manaNum.getStyleClass().add("");
            ImageView manaImage = new ImageView(new Image("com/company/Views/graphic/images/mana.png"));
            manaImage.setFitWidth(45);
            manaImage.setFitHeight(45);
            pane.getChildren().add(handCard);
            pane.getChildren().add(manaContainer);
            manaContainer.getChildren().add(manaImage);
            manaContainer.getChildren().add(manaNum);
            AnchorPane.setTopAnchor(handCard, 0.0);
            AnchorPane.setTopAnchor(manaContainer, 140.0);
            AnchorPane.setLeftAnchor(manaContainer, 67.0);
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
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                } else {
                    Image image = new Image("com/company/Views/graphic/images/mana-inactive.png");
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                }
                if (j == 0)
                    manaContainer1.getChildren().add(imageView);
                else
                    manaContainer2.getChildren().add(imageView);
            }
        }
    }
}
