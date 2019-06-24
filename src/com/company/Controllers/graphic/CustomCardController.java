package com.company.Controllers.graphic;

import com.company.Models.Card.AttackType;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    public JFXComboBox<AttackType> heroAttackType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heroAttackType.getItems().addAll(AttackType.values());
//        heroAttackType.getSelectionModel().getSelectedItem()
//        todo: to get selected item
    }

    public void createHero(ActionEvent actionEvent) {
    }

    public void createMinion(ActionEvent actionEvent) {
    }
}
