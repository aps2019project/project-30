package com.company.Controllers.graphic;

import com.company.Models.Battle.Battle;
import com.company.Models.Sound;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController implements Initializable {
    public ImageView back;
//    public Button single;
//    public Button multi;
    public VBox singlePlayer;
    public VBox multiPlayer;
    public String numberOfPlayers;
    public VBox secondPageSingle;
    public VBox multiorsingle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                numberOfPlayers="singlePlayer";
                multiorsingle.setVisible(false);
                secondPageSingle.setVisible(true);
            }
        });
        multiPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                numberOfPlayers="multiPlayer";
            }
        });

        back.setOnMouseClicked(event -> {
            RootsController.backToMainMenu();
        });

    }

    public void multiplayer(ActionEvent actionEvent) {
    }

    public void startGame(ActionEvent actionEvent) {
        new Battle(
                3,
                2
        );
        try {
            FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/game.fxml"));
            Parent root = loader.load();
            ((GameController) loader.getController()).init();
            Graphic.stage.getScene().setRoot(root);
            Sound.pause(Sound.MAIN_MENU_SOUND_ADDRESS);
            Sound.play(Sound.BATTLE_MAIN_MUSIC_ADDRESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void singlePlayer(ActionEvent actionEvent) {
    }
}
