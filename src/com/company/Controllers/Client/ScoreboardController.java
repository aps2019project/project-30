package com.company.Controllers.Client;

import com.company.Controllers.graphic.RootsController;
import com.company.Models.Sound;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {
    public ImageView back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back.setOnMouseClicked(event -> {
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS, false);
            RootsController.backToMainMenu();
        });
    }

    public static void updateScoreBoard(){

    }

    public void initValues(List<String> list) {

    }
}
