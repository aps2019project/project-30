package com.company.Views.graphic.xmls;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
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
        for (int i = 0; i < 5; i++) {
            AnchorPane pane = new AnchorPane();
            StackPane handCard = new StackPane();
            ImageView imageView = new ImageView(new Image("com/company/Views/graphic/images/test1.gif"));
            handCard.setPrefWidth(180);
            handCard.setPrefHeight(180);
            handCard.getChildren().add(imageView);
            handCard.getStyleClass().add("game-hand-card-container");

            ImageView mana = new ImageView(new Image("com/company/Views/graphic/images/mana.png"));
            mana.setFitWidth(45);
            mana.setFitHeight(45);
            pane.getChildren().add(handCard);
            pane.getChildren().add(mana);
            AnchorPane.setTopAnchor(handCard, 0.0);
            AnchorPane.setTopAnchor(mana, 140.0);
            AnchorPane.setLeftAnchor(mana, 70.0);
            handContainer.getChildren().add(pane);
        }
    }

    public void endTurn(ActionEvent actionEvent) {
        endTurn.setDisable(true);
    }
}
