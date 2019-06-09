package com.company.Views;

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

    static {
        try {
            mainMenu = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/main-menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent authenticate = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/authenticate.fxml"));
        Parent mainMenu = FXMLLoader.load(Graphic.class.getResource("graphic/xmls/main-menu.fxml"));
        Scene scene = new Scene(authenticate, 1600, 900);
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/authenticate.css");
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/main-menu.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duelyst");
        Image image = new Image("com/company/Views/graphic/images/cursor.png");  //pass in the image path
        scene.setCursor(new ImageCursor(image));
//        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setFullScreen(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
