package com.company.Views;

import com.company.Controllers.ServerController;
import com.company.Controllers.graphic.RootsController;
import com.company.Models.Server.Server;
import com.company.Models.User.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerGraphic extends Application {
    public static Stage stage;
    public static Parent server;
    public static ServerController serverController;

    public static void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        new Thread(() -> {
            try {
                Server.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/server.fxml"));
        server = loader.load();
        serverController = loader.getController();
        Scene scene = new Scene(server, 600, 800);
        scene.getStylesheets().add("com/company/Views/graphic/stylesheets/server.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duelyst Server");
        Image image = new Image("com/company/Views/graphic/images/cursor.png");  //pass in the image path
        scene.setCursor(new ImageCursor(image));
        primaryStage.show();
    }
}
