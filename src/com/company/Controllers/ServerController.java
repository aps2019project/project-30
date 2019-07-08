package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.User.Account;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    public VBox onlines;
    public VBox games;


    public void updateOnlinePlayers() {
        onlines.getChildren().clear();
        AccountController.getConnectedAccount().forEach(account -> {
            System.out.println(account.getUsername());
            Label label = new Label(account.getUsername());
            label.getStyleClass().add("online-player");
            onlines.getChildren().add(label);
        });
    }

    public void updatePlayingGames() {
        games.getChildren().clear();
        Account.getAccounts().forEach((account -> {
            Battle.getPlayingBattles().forEach(battle -> {
                Label label = new Label(String.format("%s VS %s", battle.getPlayers()[0], battle.getPlayers()[1]));
                label.getStyleClass().add("online-player");
                games.getChildren().add(label);
            });
        }));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            while (true) {
                Platform.runLater(this::updateOnlinePlayers);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Platform.runLater(this::updatePlayingGames);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
