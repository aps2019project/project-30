package com.company.Views;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Controllers.Client.ClientRequestController;
import com.company.Controllers.ShopController;
import com.company.Controllers.graphic.RootsController;
import com.company.Models.Client.Client;
import com.company.Models.Request;
import com.company.Models.User.Account;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Graphic extends Application {
    public static Stage stage;
    public static Parent shop;
    public static Parent game;
    public static Parent chooseGame;
    public static Parent collection;
    public static FXMLLoader mainMenuLoader;

    static {
        try {
            shop = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/shop.fxml"));
            game = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/game.fxml"));
            chooseGame = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/choose-game.fxml"));
            collection = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/collection.fxml"));
            AccountController.LoadSavedAccountsAndAddToAccounts();
            BattleController.loadSavedGamesAndAddToSavedGamesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/authenticate.fxml"));
        RootsController.authenticate = loader.load();
        RootsController.authenticateController = loader.getController();
        Scene scene = new Scene(RootsController.authenticate, 1600, 900);
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/authenticate.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/main-menu.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/shop.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/game.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/collection.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duelyst");
        Image image = new Image("com/company/Views/graphic/images/cursor.png");  //pass in the image path
        scene.setCursor(new ImageCursor(image));
        if (Account.getLoggedInAccount() != null) {
            RootsController.openMainMenu();
//            RootsController.openCustomCardGenerator();
        }
//        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        while (!Client.isConnected()) {
            if (Client.setClientUp()) {
//                AccountController.loadLoggedInAccount();
                ShopController.initialize();
                launch(args);
            } else {
                System.err.println("connection failed");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
