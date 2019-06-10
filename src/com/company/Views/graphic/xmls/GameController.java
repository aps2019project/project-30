package com.company.Views.graphic.xmls;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Models.Battle.Battle;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public HBox handContainer;
    public Button endTurn;
    public VBox graveyard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (!Battle.getPlayingBattle().getTurnToPlay().getName().equals(Account.getLoggedInAccount().getUsername())) {
//            endTurn.setDisable(true);
//        }
    }

    public void endTurn(ActionEvent actionEvent) {
        endTurn.setDisable(true);
    }
}
