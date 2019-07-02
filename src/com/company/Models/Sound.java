package com.company.Models;

import com.company.Views.Graphic;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.HashMap;

public class Sound {

    final public static String MAIN_MENU_SOUND_ADDRESS = "graphic/sounds/mainmenu.mp3";
    final public static String BATTLE_MAIN_MUSIC_ADDRESS = "graphic/sounds/battlemusic.m4a";
    final public static String ATTACK_SOUND_EFFECT_ADDRESS = "graphic/sounds/attack.mp3";
    final public static String SELECT_SOUND_EFFECT_ADDRESS = "graphic/sounds/select.m4a";
    private static HashMap<String, MediaPlayer> playingSounds = new HashMap<>();

    public static void play(String soundAddress,boolean repeat) {
        if(!playingSounds.containsKey(soundAddress)) {
            Media media = null;
            try {
                media = new Media(Graphic.class.getResource(soundAddress).toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            playingSounds.put(soundAddress, mediaPlayer);
            mediaPlayer.play();
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        if(repeat) {
                            mediaPlayer.seek(Duration.ZERO);
                    }else {
                            playingSounds.remove(soundAddress);
                        }
                    }
                });
        }
    }

    public static void pause(String soundAddress) {
        if(playingSounds.containsKey(soundAddress)) {
            playingSounds.get(soundAddress).pause();
            playingSounds.remove(soundAddress);
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
                    playingSounds.get(soundAddress).setMute(true);
                    mPressed = ctrlPressed = false;
                } else if (ctrlPressed && pPressed) {
                    playingSounds.get(soundAddress).setMute(false);
                    pPressed = ctrlPressed = false;
                }
            }
        });
    }

}