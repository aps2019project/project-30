package com.company.Models;

import com.company.Views.Graphic;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.HashMap;

public class Sound {

    final public static String MAIN_MENU_SOUND_ADDRESS = "graphic/sounds/mainmenu.mp3";
    final public static String BATTLE_MAIN_MUSIC_ADDRESS = "graphic/sounds/battlemusic.m4a";
    final public static String ATTACK_SOUND_EFFECT_ADDRESS = "graphic/sounds/attack.mp3";
    private static HashMap<String, MediaPlayer> playedSongs = new HashMap<>();

    public static void play(String soundAddress) {
        if(!playedSongs.containsKey(soundAddress)) {
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
    }

    public static void pause(String soundAddress) {
        if(playedSongs.containsKey(soundAddress)) {
            playedSongs.get(soundAddress).pause();
            playedSongs.remove(soundAddress);
        }
    }

    public static void muteAndUnmute(Scene root, String soundAddress) {
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            boolean ctrlPressed = false;
            boolean mPressed = false;
            boolean pPressed = false;
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case CONTROL:
                        ctrlPressed = true;
                        break;
                    case M:
                        mPressed = true;
                        break;
                    case P:
                        pPressed = true;
                        break;
                }
                if (ctrlPressed && mPressed) {
                    playedSongs.get(soundAddress).setMute(true);
                    mPressed = ctrlPressed = false;
                } else if (ctrlPressed && pPressed) {
                    playedSongs.get(soundAddress).setMute(false);
                    pPressed = ctrlPressed = false;
                }
            }
        });
    }

}