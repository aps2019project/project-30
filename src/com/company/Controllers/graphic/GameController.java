package com.company.Controllers.graphic;

import animatefx.animation.ZoomIn;
import com.company.Controllers.BattleController;
import com.company.Controllers.JsonController;
import com.company.Controllers.MainMenuController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Card.Card;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Sound;
import com.company.Models.User.Account;
import com.company.Views.BattleView;
import com.company.Views.Graphic;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController implements Initializable {

    public static VBox cardDesciption;
    public HBox handContainer;
    public Button endTurn;
    public VBox graveyard;
    public Label player1name;
    public Label player2name;
    public HBox manaContainer1;
    public HBox manaContainer2;
    public AnchorPane gameRoot;
    public StackPane pauseMenu;
    public Pane tableContainer;
    public GridPane gameTable;
    public ImageView player1HeroPic;
    public ImageView player2HeroPic;
    public Label fastForward;
    boolean tabPressed = false;
    boolean numLockPressed = false;
    boolean ctrlPressed = false;
    boolean MPressed = false;
    private Card selectedCard;
    public static VBox vvBox;
    public static VBox vvvBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        player1name.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               vvBox= BattleView.getInfo();
                gameRoot.getChildren().add(vvBox);
                gameRoot.setLeftAnchor(vvBox,100.0);
                gameRoot.setTopAnchor(vvBox,100.0);
            }
        });
        player2name.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 vvvBox= BattleView.getInfo();
                gameRoot.getChildren().add(vvvBox);
                gameRoot.setRightAnchor(vvvBox,100.0);
                gameRoot.setTopAnchor(vvvBox,100.0);
            }
        });
        player1name.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameRoot.getChildren().remove(vvBox);
            }
        });
        player2name.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameRoot.getChildren().remove(vvvBox);
            }
        });

        cheatCodeHandler();

        gameRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case Q:
                        changePaseMenuState();
                        break;
                }
            }

            private void changePaseMenuState() {
                if(!pauseMenu.isVisible()) {
                    GaussianBlur gaussianBlur = new GaussianBlur(30);
                    gameRoot.setEffect(gaussianBlur);
                    pauseMenu.setVisible(true);
                } else{
                    gameRoot.setEffect(null);
                    pauseMenu.setVisible(false);
                }
            }
        });

        fastForward.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(pauseMenu.isVisible()){

                }
            }
        });


    }

    private void cheatCodeHandler() {
        gameRoot.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case TAB:
                        tabPressed = true;
                        break;
                    case PLUS:
                        numLockPressed = true;
                        break;
                }
                if (tabPressed && numLockPressed) {
                    Battle.getPlayingBattle().getTurnToPlay().setMaxMana(9);
                    RootsController.gameController.updateMana();
                }
            }
        });
    }

    public void init() {
//        if (!Battle.getPlayingBattle().getTurnToPlay().getName().equals(Account.getLoggedInAccount().getUsername())) {
//            endTurn.setDisable(true);
//        }
        Sound.muteAndUnmute(Graphic.stage.getScene(), Sound.BATTLE_MAIN_MUSIC_ADDRESS);

        updateGraveYard();
        gameTable = new GridPane();
        gameTable.setVgap(10);
        gameTable.setHgap(10);
        updateTable(gameTable);
        PerspectiveTransform e = new PerspectiveTransform();
        e.setUlx(Graphic.stage.widthProperty().getValue() / 2 - 900);
        e.setUly(Graphic.stage.heightProperty().getValue() / 2 - 400);
        e.setUrx(Graphic.stage.widthProperty().getValue() / 2 + 900);
        e.setUry(Graphic.stage.heightProperty().getValue() / 2 - 400);
        e.setLlx(Graphic.stage.widthProperty().getValue() / 2 - 1000);
        e.setLly(Graphic.stage.heightProperty().getValue() / 2 + 500);
        e.setLrx(Graphic.stage.widthProperty().getValue() / 2 + 1000);
        e.setLry(Graphic.stage.heightProperty().getValue() / 2 + 500);
//        gameTable.setEffect(e);
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
        handContainer.getChildren().clear();
        for (Card card : Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards()) {
            AnchorPane pane = new AnchorPane();
            StackPane handCard = new StackPane();
            Label cardName = new Label(card.getName());
            handCard.setPrefWidth(180);
            handCard.setPrefHeight(180);
            handCard.getChildren().add(cardName);
            try {
                Image cardGif;
                if (card instanceof Spell || card instanceof Item)
                    cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_actionbar.gif");
                else
                    cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_idle.gif");
                ImageView cardView = new ImageView(cardGif);
                cardView.setFitWidth(150);
                cardView.setFitHeight(150);
                handCard.getChildren().add(cardView);
            } catch (Exception e) {
//                e.printStackTrace();
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
            pane.setId(card.getId());
            pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (selectedCard == null) {
                        TranslateTransition transition = new TranslateTransition();
                        transition.setToY(-30);
                        transition.setNode(pane);
                        transition.play();
                        Battle.getPlayingBattle().getBattleController().selectCard(pane.getId());
                        selectedCard = Battle.getPlayingBattle().getBattleController().getCardById(pane.getId());

                    } else {
                        TranslateTransition transition = new TranslateTransition();
                        transition.setToY(0);
                        transition.setNode(pane);
                        transition.play();
                        selectedCard = null;
                    }
                    updateGameTableColor();
                }
            });
            pane.setOnMouseEntered(event -> {
                cardDesciption = BattleView.cardDesciption(Battle.getPlayingBattle().getBattleController().getCardById(pane.getId()), "game");
                gameRoot.getChildren().add(cardDesciption);
                gameRoot.setBottomAnchor(cardDesciption, pane.getLayoutY() + 200.0);
                gameRoot.setLeftAnchor(cardDesciption, pane.getLayoutX());

            });
            pane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    gameRoot.getChildren().remove(cardDesciption);
                }
            });
        }
    }

    private void updateGameTableColor() {
        if (selectedCard != null && Battle.getPlayingBattle().getBattleController().cardExistsInHand(selectedCard.getId())) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    Cell cell = Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1);
                    if (cell.getCardInCell() != null &&
                            BattleController.playerThatHasThisCard(cell.getCardInCell()).equals(Battle.getPlayingBattle().getTurnToPlay()))
                        for (int x = -1; x <= 1; x++)
                            for (int y = -1; y <= 1; y++)
                                if (BattleController.validCoordinatesRange(i + 1 + x, j + 1 + y)) {
                                    AnchorPane tileToColoring = getCellFromGameTable(i + x, j + y);
                                    Cell cellToColoring = Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1 + x, j + 1 + y);
                                    if (cellToColoring.getCardInCell() == null) {
                                        tileToColoring.getStyleClass().clear();
                                        tileToColoring.getStyleClass().add("tile-to-deploy");
                                    } else {
                                        tileToColoring.getStyleClass().clear();
                                        tileToColoring.getStyleClass().add("tile-default");
                                    }
                                }
                }
            }
        } else if (selectedCard != null && !Battle.getPlayingBattle().getBattleController().cardExistsInHand(selectedCard.getId())) {
            int cardX = ((Soldier) selectedCard).getCell().getxCoordinate();
            int cardY = ((Soldier) selectedCard).getCell().getyCoordinate();
            setTableToDefaultColor();
            for (int x = -2; x <= 2; x++)
                for (int y = -2; y <= 2; y++)
                    if (Math.abs(x) + Math.abs(y) <= 2) {
                        AnchorPane cell = getCellFromGameTable(cardX + x - 1, cardY + y - 1);
                        if (Battle.getPlayingBattle().getMap().getCellByCoordinates(cardX + x, cardY + y) != null &&
                                Battle.getPlayingBattle().getMap().getCellByCoordinates(cardX + x, cardY + y).getCardInCell() == null) {
                            cell.getStyleClass().clear();
                            cell.getStyleClass().add("tile-to-deploy");
                        }
                    }
        } else {
            setTableToDefaultColor();
        }
//        for (Cell cell : Battle.getPlayingBattle().getMap().getCells()) {
//            if (Battle.getPlayingBattle().getBattleController().cardExistsInHand(Battle.getPlayingBattle().getTurnToPlay().getSelectedCard().getName())) {
//                if ()
//            }
//        }
    }

    private void setTableToDefaultColor() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                AnchorPane tileToColoring = getCellFromGameTable(i, j);
                tileToColoring.getStyleClass().clear();
                tileToColoring.getStyleClass().add("tile-default");
            }
        }
    }

    private AnchorPane getCellFromGameTable(int x, int y) {
        for (Node node : gameTable.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (AnchorPane) node;
            }
        }
        return null;
    }

    public void updateTable(GridPane gridPane) {
        gridPane.getChildren().clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                Cell cell = Battle.getPlayingBattle().getMap().getCellByCoordinates(i + 1, j + 1);
                Soldier soldierInCell = (Soldier) (cell.getCardInCell());
                AnchorPane tile = new AnchorPane();
                tile.setMaxSize(100, 100);
                tile.setPrefSize(100,    100);
                tile.setId((i + 1) + ":" + (j + 1));
                tile.setOnMouseClicked(this::tileClickHandler);

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
                    flagView.setFitWidth(40);
                    flagView.setFitHeight(56);
                    flagContainer.getChildren().add(flagView);
                    tile.getChildren().add(flagContainer);
                    AnchorPane.setTopAnchor(flagContainer, 0.0);
                    AnchorPane.setBottomAnchor(flagContainer, 0.0);
                    AnchorPane.setRightAnchor(flagContainer, 0.0);
                    AnchorPane.setLeftAnchor(flagContainer, 0.0);
                } else if (cell.getItem() != null) {
                    StackPane flagContainer = new StackPane();
                    Image itemImage;
                    itemImage = new Image("com/company/Views/graphic/images/tile-item.png");
                    ImageView itemView = new ImageView(itemImage);
                    itemView.setFitWidth(80);
                    itemView.setFitWidth(60);
                    flagContainer.getChildren().add(itemView);
                    tile.getChildren().add(flagContainer);
                    AnchorPane.setTopAnchor(flagContainer, 0.0);
                    AnchorPane.setBottomAnchor(flagContainer, 0.0);
                    AnchorPane.setRightAnchor(flagContainer, 0.0);
                    AnchorPane.setLeftAnchor(flagContainer, 0.0);
                }

                gridPane.add(tile, i, j);

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
        MainMenuController.timenow=-1;
        Battle.getPlayingBattle().getBattleController().endTurn();
        updateTable(gameTable);
        updateMana();
        updateHand();
        updateGraveYard();
//        endTurn.setDisable(true);
    }
    public void endTurnq() {
        Battle.getPlayingBattle().getBattleController().endTurn();
        updateTable(gameTable);
        updateMana();
        updateHand();
        updateGraveYard();
//        endTurn.setDisable(true);
    }

    public void updateMana() {
        manaContainer1.getChildren().clear();
        manaContainer2.getChildren().clear();
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 9; i++) {
                Image image;
                ImageView imageView;
                if (i <= Battle.getPlayingBattle().getPlayers()[j].getMaxMana())
                    image = new Image("com/company/Views/graphic/images/mana.png");
                else
                    image = new Image("com/company/Views/graphic/images/mana-inactive.png");

                imageView = new ImageView(image);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                imageView.getStyleClass().add("mana-image");
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
                    cardContainer.setPrefWidth(60);
                    cardContainer.setPrefHeight(60);
                    cardContainer.getStyleClass().add("graveyard-card-container");
                    Image cardGif;
                    if (card instanceof Spell || card instanceof Item)
                        cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_actionbar.gif");
                    else
                        cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_idle.gif");
                    ImageView cardView = new ImageView(cardGif);
                    cardView.setFitWidth(80);
                    cardView.setFitHeight(80);
                    cardContainer.getChildren().add(cardView);
                    graveyard.getChildren().add(cardContainer);
                });
    }

    public void saveAndExit() {
        saveGame();
        exitGame();
    }

    private static void saveGame() {
        JsonController.removeFile(Battle.getSavedGamesFilePath());
        Battle.addToSavedBattles(Battle.getPlayingBattle());
        JsonController.writeAllSavedGamesOnFile();
    }

    private static void exitGame() {
        RootsController.backToMainMenu();
    }

    public void loadSavedGamesAndAddToSavedGamesList() {
        List<Battle> savedGames = JsonController.getSavedGames();
        if (savedGames != null)
            Battle.addToSavedBattles(savedGames);
    }

    private void tileClickHandler(MouseEvent event) {
        Matcher matcher = Pattern.compile("(?<i>\\d+):(?<j>\\d+)").matcher(((AnchorPane) event.getSource()).getId());
        matcher.find();
        int x = Integer.valueOf(matcher.group("i"));
        int y = Integer.valueOf(matcher.group("j"));
        if (Battle.getPlayingBattle().getTurnToPlay().getSelectedCard() != null &&
                Battle.getPlayingBattle().getBattleController().cardExistsInHand(Battle.getPlayingBattle().getTurnToPlay().getSelectedCard().getId())) {

            Battle.getPlayingBattle()
                    .getBattleController().insertNewCardToMap(x, y,
                    Battle.getPlayingBattle().getTurnToPlay().getSelectedCard().getId());
            updateHand();
            updateTable(gameTable);
            selectedCard = null;
        } else {
            Cell cell1 = Battle.getPlayingBattle().getMap().getCellByCoordinates(x, y);
            if (cell1.getCardInCell() != null) {
                if (selectedCard != null) {
                    Battle.getPlayingBattle().getBattleController().attack(((Soldier) cell1.getCardInCell()).getCell(), false);
                    int attackerX = ((Soldier) cell1.getCardInCell()).getCell().getxCoordinate();
                    int attackerY = ((Soldier) cell1.getCardInCell()).getCell().getyCoordinate();
                    new Thread(() -> {
                        Image idleImage = ((ImageView) ((StackPane) getCellFromGameTable(attackerX - 1, attackerY - 1)
                                .getChildren().get(0)).getChildren().get(0)).getImage();
                        ((ImageView) ((StackPane) getCellFromGameTable(attackerX - 1, attackerY - 1)
                                .getChildren().get(0)).getChildren().get(0)).setImage(
                                new Image("com/company/Views/graphic/images/gifs/" + Battle.getPlayingBattle().getTurnToPlay().getSelectedCard().getName() + "_attack.gif"));
                        long startTime = System.currentTimeMillis();
                        long endTime;
                        while ((endTime = System.currentTimeMillis()) - startTime < 2000) ;
                        System.out.println(endTime - startTime);
                        ((ImageView) ((StackPane) getCellFromGameTable(attackerX - 1, attackerY - 1)
                                .getChildren().get(0)).getChildren().get(0)).setImage(idleImage);
                    }).start();
                    updateTable(gameTable);
                    selectedCard = null;
                } else {
                    Battle.getPlayingBattle().getBattleController().selectCard(cell1.getCardInCell().getId());
                    selectedCard = Battle.getPlayingBattle().getBattleController().getCardById(cell1.getCardInCell().getId());
                }
            } else {
                Battle.getPlayingBattle().getBattleController().move(
                        Integer.valueOf(matcher.group("i")),
                        Integer.valueOf(matcher.group("j"))
                );
                if (((Soldier) Battle.getPlayingBattle().getTurnToPlay().getSelectedCard()).getCell().getxCoordinate() == x &&
                        ((Soldier) Battle.getPlayingBattle().getTurnToPlay().getSelectedCard()).getCell().getyCoordinate() == y) {

                }
                selectedCard = null;
                updateTable(gameTable);
                new Thread(() -> {
                    Image idleImage = ((ImageView) ((StackPane) getCellFromGameTable(x - 1, y - 1)
                            .getChildren().get(0)).getChildren().get(0)).getImage();
                    ((ImageView) ((StackPane) getCellFromGameTable(x - 1, y - 1)
                            .getChildren().get(0)).getChildren().get(0)).setImage(
                            new Image("com/company/Views/graphic/images/gifs/" + Battle.getPlayingBattle().getTurnToPlay().getSelectedCard().getName() + "_run.gif"));
                    long startTime = System.currentTimeMillis();
                    long endTime;
                    while ((endTime = System.currentTimeMillis()) - startTime < 2000) ;
                    System.out.println(endTime - startTime);
                    ((ImageView) ((StackPane) getCellFromGameTable(x - 1, y - 1)
                            .getChildren().get(0)).getChildren().get(0)).setImage(idleImage);
                }).start();
            }
            updateGameTableColor();
        }
    }
}
