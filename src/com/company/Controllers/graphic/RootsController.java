package com.company.Controllers.graphic;

import com.company.Controllers.MainMenuController;
import com.company.Views.Graphic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class RootsController {
    static Parent mainMenu;
    static Parent collection;
    static MainMenuController mainMenuController;
    static CollectionController collectionController;

    public static void openMainMenu() {
        try {
            FXMLLoader mainMenuLoader;
            mainMenuLoader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/main-menu.fxml"));
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
            FXMLLoader collectionLoader;
            collectionLoader = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/collection.fxml"));
            collection = collectionLoader.load();
            collectionController = collectionLoader.getController();
            collectionController.updateDecks();
            Graphic.stage.getScene().setRoot(RootsController.collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
