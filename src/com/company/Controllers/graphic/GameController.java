package com.company.Controllers.graphic;

import animatefx.animation.ZoomIn;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Card.Card;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.function.Consumer;

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
    public ImageView player1HeroPic;
    public ImageView player2HeroPic;

    public void init() {
//        if (!Battle.getPlayingBattle().getTurnToPlay().getName().equals(Account.getLoggedInAccount().getUsername())) {
//            endTurn.setDisable(true);
//        }
        updateGraveYard();
        gameTable = new GridPane();
        gameTable.setVgap(10);
        gameTable.setHgap(10);
        updateTable(gameTable);
        PerspectiveTransform e = new PerspectiveTransform();
        e.setUlx(20);
        e.setUly(5);
        e.setUrx(900);
        e.setUry(5);
        e.setLlx(-40);
        e.setLly(500);
        e.setLrx(1000);
        e.setLry(500);
        tableContainer.setEffect(e);
        gameTable.setAlignment(Pos.CENTER);
        tableContainer.getChildren().add(gameTable);
        Image heroPic1 = new Image("com/company/Views/graphic/images/heroes/" + Battle.getPlayingBattle().getPlayers()[0].getDeck().getHeroCard().getName() + "_logo.png");
        player1HeroPic.setImage(heroPic1);
        player1HeroPic.setOnMouseEntered(event -> new ZoomIn(player1HeroPic).play());
        Image heroPic2 = new Image("com/company/Views/graphic/images/heroes/" + Battle.getPlayingBattle().getPlayers()[1].getDeck().getHeroCard().getName() + "_logo.png");
        player1HeroPic.setImage(heroPic2);
        player2HeroPic.setOnMouseEntered(event -> new ZoomIn(player2HeroPic).play());

        player1name.setText(Battle.getPlayingBattle().getPlayers()[0].getName().toUpperCase());
        player2name.setText(Battle.getPlayingBattle().getPlayers()[1].getName().toUpperCase());
        player1name.getStyleClass().add("player-name");
        player2name.getStyleClass().add("player-name");
        updateMana();
        updateHand();
    }

    private void updateHand() {
        for (Card card : Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards()) {
            AnchorPane pane = new AnchorPane();
            StackPane handCard = new StackPane();
            Label cardName = new Label(card.getName());
            handCard.setPrefWidth(180);
            handCard.setPrefHeight(180);
            handCard.getChildren().add(cardName);
            try {
                Image cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_breathing.gif");
                ImageView cardView = new ImageView(cardGif);
                handCard.getChildren().add(cardView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            handCard.getStyleClass().add("game-hand-card-container");

            StackPane manaContainer = new StackPane();
            Label manaNum = new Label(String.valueOf(card.getManaPoint()));
            manaNum.getStyleClass().add("mana-num");
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
                Cell cell = Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1);
                Soldier soldierInCell = (Soldier) (cell.getCardInCell());
                if (soldierInCell != null) {
                    try {
                        StackPane cardViewContainer = getCardView(soldierInCell);
                        StackPane attackPowerContainer = getStatsView(soldierInCell.getAttackPower(), "attack-power-container");
                        StackPane healthPowerContainer = getStatsView(soldierInCell.getHealth(), "health-power-container");

                        tile.getChildren().add(cardViewContainer);
                        tile.getChildren().add(attackPowerContainer);
                        tile.getChildren().add(healthPowerContainer);
                        AnchorPane.setTopAnchor(cardViewContainer, 0.0);
                        AnchorPane.setBottomAnchor(cardViewContainer, 0.0);
                        AnchorPane.setRightAnchor(cardViewContainer, 0.0);
                        AnchorPane.setLeftAnchor(cardViewContainer, 0.0);
                        AnchorPane.setBottomAnchor(attackPowerContainer, 0.0);
                        AnchorPane.setLeftAnchor(attackPowerContainer, 0.0);
                        AnchorPane.setBottomAnchor(healthPowerContainer, 0.0);
                        AnchorPane.setRightAnchor(healthPowerContainer, 0.0);
                    } catch (NullPointerException | IllegalArgumentException e) {
                        System.out.println("===" + e.getMessage());
                    }
                } else if (cell.getFlag() != null) {
                    StackPane flagContainer = new StackPane();
                    Image flagGif;
                    flagGif = new Image("com/company/Views/graphic/images/gifs/flag.gif");
                    ImageView flagView = new ImageView(flagGif);
                    flagView.setFitWidth(60);
                    flagView.setFitWidth(83);
                    flagContainer.getChildren().add(flagView);
                    tile.getChildren().add(flagContainer);
                    AnchorPane.setTopAnchor(flagContainer, 0.0);
                    AnchorPane.setBottomAnchor(flagContainer, 0.0);
                    AnchorPane.setRightAnchor(flagContainer, 0.0);
                    AnchorPane.setLeftAnchor(flagContainer, 0.0);
                }

                gridPane.add(tile, i, j);
                tile.setPrefWidth(80);
                tile.setPrefHeight(80);
                tile.getStyleClass().add("tile-default");
            }
        }
    }

    private StackPane getStatsView(int attackPower2, String s) {
        StackPane attackPowerContainer = new StackPane();
        Label attackPower = new Label(String.valueOf(attackPower2));
        attackPowerContainer.getChildren().add(attackPower);
        attackPowerContainer.getStyleClass().add(s);
        attackPower.getStyleClass().add("stats");
        return attackPowerContainer;
    }

    private StackPane getCardView(Soldier soldierInCell) {
        StackPane cardViewContainer = new StackPane();
        Image cardGif;
        cardGif = new Image("com/company/Views/graphic/images/gifs/" + soldierInCell.getName() + "_breathing.gif");
        ImageView cardView = new ImageView(cardGif);
        cardViewContainer.getChildren().add(cardView);
        return cardViewContainer;
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

    public void updateGraveYard() {
        graveyard.getChildren().clear();
        Battle.getPlayingBattle().getTurnToPlay().getDeck().getDeckCards().stream()
                .filter((Card::isInGraveCards))
                .forEach(card -> {
                    StackPane cardContainer = new StackPane();
                    cardContainer.setPrefWidth(150);
                    cardContainer.setPrefHeight(150);
                    cardContainer.getStyleClass().add("graveyard-card-container");
                    Label cardName = new Label(card.getName());
                    cardContainer.getChildren().add(cardName);
                    graveyard.getChildren().add(cardContainer);
                });
    }
}
