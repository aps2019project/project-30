package com.company.Views;

import com.company.Controllers.ShopController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Graphic extends Application {
    public static Stage stage;
    public static Parent mainMenu;
    public static Parent shop;
    public static Parent game;

    static {
        try {
            mainMenu = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/main-menu.fxml"));
            shop = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/shop.fxml"));
            game = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent authenticate = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/authenticate.fxml"));
//        Scene scene = new Scene(authenticate, 1600, 900);
        Scene scene = new Scene(game, 1600, 900);
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/authenticate.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/main-menu.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/shop.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duelyst");
        Image image = new Image("com/company/Views/graphic/images/cursor.png");  //pass in the image path
        scene.setCursor(new ImageCursor(image));
//        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setFullScreen(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        ShopController.initialize();
        launch(args);
    }
}
