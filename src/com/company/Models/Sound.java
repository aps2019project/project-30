package com.company.Models;

import com.company.Views.Graphic;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.HashMap;

public class Sound {

    final public static String MAIN_MENU_SOUND_ADDRESS = "graphic/sounds/mainmenu.mp3";
    final public static String BATTLE_MAIN_MUSIC_ADDRESS = "graphic/sounds/battlemusic.m4a";
    private static HashMap<String, MediaPlayer> playedSongs = new HashMap<>();

    public static void play(String soundAddress) {
        Media media = null;
        try {
            media = new Media(Graphic.class.getResource(soundAddress).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        playedSongs.put(soundAddress, mediaPlayer);
        mediaPlayer.play();
    }

    public static void pause(String soundAddress) {
        playedSongs.get(soundAddress).pause();
        playedSongs.remove(soundAddress);
    }

    private static boolean isPlaying(String soundAddress) {
        return playedSongs.containsKey(soundAddress);
    }

    public static void changeMusicState(String soundAddress) {
        if (isPlaying(soundAddress))
            Sound.pause(soundAddress);
        else
            play(soundAddress);
    }

    public static void muteAndUnmute(AnchorPane root, String soundAddress) {
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            boolean ctrlPressed = false;
            boolean mPressed = false;
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case CONTROL:
                        ctrlPressed = true;
                        break;
                    case M:
                        mPressed = true;
                        break;
                }
                if (ctrlPressed && mPressed) {
                    Sound.changeMusicState(soundAddress);
                }
            }
        });
    }

}