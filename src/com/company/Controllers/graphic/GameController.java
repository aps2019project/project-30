package com.company.Controllers.graphic;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
    public Pane tableContainer;
    public GridPane gameTable;

    public void init() {
//        if (!Battle.getPlayingBattle().getTurnToPlay().getName().equals(Account.getLoggedInAccount().getUsername())) {
//            endTurn.setDisable(true);
//        }
        gameTable = new GridPane();
        gameTable.setVgap(10);
        gameTable.setHgap(10);
        updateTable(gameTable);
        PerspectiveTransform e = new PerspectiveTransform();
        e.setUlx(gameTable.getLayoutX() + 100);    // Upper left
        e.setUly(gameTable.getLayoutY());
        e.setUrx(gameTable.getLayoutX() + 900);    // Upper right
        e.setUry(gameTable.getLayoutY());
        e.setLlx(gameTable.getLayoutX());      // Lower left
        e.setLly(gameTable.getLayoutY() + 300);
        e.setLrx(gameTable.getLayoutX() + 1000);    // Lower right
        e.setLry(gameTable.getLayoutY() + 300);
//        gameTable.setEffect(e);
        gameTable.setAlignment(Pos.CENTER);
        tableContainer.getChildren().add(gameTable);
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

    private void updateTable(GridPane gridPane) {
        gridPane.getChildren().clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                AnchorPane tile = new AnchorPane();
                Soldier soldierInCell = (Soldier) (Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1).getCardInCell());
                if (soldierInCell != null) {
                    try {
                        StackPane cardViewContainer = new StackPane();
                        Image cardGif;
                        cardGif = new Image("com/company/Views/graphic/images/gifs/" + soldierInCell.getName() + "_breathing.gif");
                        ImageView cardView = new ImageView(cardGif);
                        cardViewContainer.getChildren().add(cardView);
                        Label attackPower = new Label(String.valueOf(soldierInCell.getAttackPower()));
                        Label healthPower = new Label(String.valueOf(soldierInCell.getHealth()));

                        tile.getChildren().add(cardViewContainer);
                        AnchorPane.setTopAnchor(cardViewContainer, 0.0);
                        AnchorPane.setBottomAnchor(cardViewContainer, 0.0);
                        AnchorPane.setRightAnchor(cardViewContainer, 0.0);
                        AnchorPane.setLeftAnchor(cardViewContainer, 0.0);
                    } catch (NullPointerException | IllegalArgumentException e) {
                        System.out.println("===" + e.getMessage());
                    }
                }
                gridPane.add(tile, i, j);
                tile.setPrefWidth(80);
                tile.setPrefHeight(80);
                tile.getStyleClass().add("tile-default");
            }
        }
    }

    public void endTurn(ActionEvent actionEvent) {
        Battle.getPlayingBattle().getBattleController().endTurn();
        updateTable(gameTable);
        updateMana();
//        endTurn.setDisable(true);
    }

    public void updateMana() {
        manaContainer1.getChildren().clear();
        manaContainer2.getChildren().clear();
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
