package com.company.Views.graphic;

import com.company.Views.Graphic;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Sound {
    public static void play(String soundAddress){
        Media media = null;
        try {
            media = new Media(Graphic.class.getResource("graphic/sounds/mainmenu.mp3").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        play(null);
    }
}