package com.company.Controllers.graphic;

import com.company.Controllers.AccountController;
import com.company.Controllers.MainMenuController;
import com.company.Models.Sound;
import com.company.Views.Graphic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class RootsController {
    static Parent mainMenu;
    static Parent collection;
    static Parent shop;
    static Parent customCardGenerator;
    static MainMenuController mainMenuController;
    static CollectionController collectionController;
    static ShopController shopController;

    static {
        try {
            customCardGenerator = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/custom-card.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openMainMenu() {
        Sound.play(Sound.MAIN_MENU_SOUND_ADDRESS);
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
    public static void game(){
        try {
            FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/game.fxml"));
            Parent root = loader.load();
            ((GameController) loader.getController()).init();
            Graphic.stage.getScene().setRoot(root);
            Sound.pause(Sound.MAIN_MENU_SOUND_ADDRESS);
            Sound.play(Sound.BATTLE_MAIN_MUSIC_ADDRESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openCustomCardGenerator() {
        Graphic.stage.getScene().setRoot(customCardGenerator);
    }
}
