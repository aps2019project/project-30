package com.company.Models;

import com.company.Views.Graphic;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Sound {

    final public static String MAIN_MENU_SOUND_ADDRESS = "graphic/sounds/mainmenu.mp3";

    public static void play(String soundAddress) {
        Media media = null;
        try {
            media = new Media(Graphic.class.getResource(soundAddress).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}