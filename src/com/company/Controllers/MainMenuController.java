package com.company.Controllers;

import com.company.Views.Graphic;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public ImageView friends;
    public ImageView shop;
    public Label play;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friends.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            event.consume();
        });

        shop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.shop);
            event.consume();
        });

        play.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.game);
            event.consume();
        });
//        Media media = new Media(new File("com/company/Views/graphic/sounds/mainmenu.mp3").toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
    }



}
