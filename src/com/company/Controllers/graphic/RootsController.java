package com.company.Controllers.graphic;

import com.company.Controllers.AccountController;
import com.company.Controllers.AuthenticateController;
import com.company.Controllers.Client.ScoreboardController;
import com.company.Controllers.MainMenuController;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Sound;
import com.company.Views.Graphic;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

public class RootsController {
    public static Parent authenticate;
    static Parent scoreboard;
    static Parent mainMenu;
    static Parent collection;
    static Parent shop;
    static Parent customCardGenerator;
    static ScoreboardController scoreboardController;
    static MainMenuController mainMenuController;
    static CollectionController collectionController;
    static ShopController shopController;
    static GameController gameController;
    public static AuthenticateController authenticateController;
    public static Collection jBuyCollection;
    public static Collection jSellCollection=null;

    static {
        try {
            customCardGenerator = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/custom-card.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openMainMenu() {
        Sound.play(Sound.MAIN_MENU_SOUND_ADDRESS, true);
        try {
            FXMLLoader mainMenuLoader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/main-menu.fxml"));
            mainMenu = mainMenuLoader.load();
            mainMenuController = mainMenuLoader.getController();
            mainMenuController.initValues();
            Graphic.stage.getScene().setRoot(RootsController.mainMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void backToMainMenu() {
        Graphic.stage.getScene().setRoot(RootsController.mainMenu);
        mainMenuController.initValues();
    }

    public static void openGameCollection() {
        try {
            FXMLLoader collectionLoader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/collection.fxml"));
            collection = collectionLoader.load();
            collectionController = collectionLoader.getController();
            collectionController.updateDecks();
            Graphic.stage.getScene().setRoot(RootsController.collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openShop() {
        try {
            FXMLLoader shopLoader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/shop.fxml"));
            shop = shopLoader.load();
            shopController = shopLoader.getController();
            shopController.updateDecks();
            Graphic.stage.getScene().setRoot(RootsController.shop);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void openScoreBoardMenu(List<String> list) {
        try {
            FXMLLoader scoreboardMenuLoader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/scoreboard.fxml"));
            scoreboard = scoreboardMenuLoader.load();
            scoreboardController = scoreboardMenuLoader.getController();
            scoreboardController.initValues(list);
            Graphic.stage.getScene().setRoot(RootsController.scoreboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void game() {
        try {
            FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/game.fxml"));
            Parent root = loader.load();
            gameController = (GameController) loader.getController();
            gameController.init();
            Graphic.stage.getScene().setRoot(root);
            Sound.pause(Sound.MAIN_MENU_SOUND_ADDRESS);
            Sound.play(Sound.BATTLE_MAIN_MUSIC_ADDRESS, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openCustomCardGenerator() {
        Platform.runLater(() -> Graphic.stage.getScene().setRoot(customCardGenerator));

    }

    public static void setLoginErrorOnAuthenticate(String errorMessage) {
        Platform.runLater(() -> {
            authenticateController.setLoginError(errorMessage);
        });
    }

    public static void setSignUpErrorOnAuthenticate(String errorMessage) {
        Platform.runLater(() -> {
            authenticateController.setSignUpError(errorMessage);
        });
    }

    public static void openLoginTab() {
        Platform.runLater(() -> {
            authenticateController.successfulLogin();
        });
    }
}
