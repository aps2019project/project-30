package com.company.Controllers.graphic;

import com.company.Models.Battle.Battle;
import com.company.Views.Graphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController implements Initializable {
    public ImageView back;
    public Button single;
    public Button multi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
        });

    }

    public void startMultiplayer(ActionEvent actionEvent) {
        new Battle(
                3,
                2
        );
        try {
            FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/game.fxml"));
            Parent root = loader.load();
            ((GameController) loader.getController()).init();
            Graphic.stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
