package com.company.Controllers.Client;

import com.company.Controllers.graphic.RootsController;
import com.company.Models.Client.Client;
import com.company.Models.Request;
import com.company.Models.Sound;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {
    public ImageView back;
    public VBox scoreBoard;
    public ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back.setOnMouseClicked(event -> {
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS, false);
            RootsController.backToMainMenu();
        });
    }

    public void updateScoreBoard(){
        Client.getRequestController().sendRequest(
                new Request(Request.Type.SCOREBOARD)
        );
    }

    public void initValues(List<String> list) {
        for (String string:list) {
            scoreBoard.getChildren().add(createAccountsLabel(string));
        }
    }

    private Label createAccountsLabel(String string){
        Label label = new Label(string);
        label.setVisible(true);
        return label;
    }
}
