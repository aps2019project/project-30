package com.company.Controllers.graphic;

import com.company.Models.Card.AttackType;
import com.jfoenix.controls.JFXComboBox;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    public JFXComboBox<AttackType> heroAttackType;

    public TextField heroName;
    public TextField heroNeededDrake;
    public TextField heroNeededMana;
    public TextField minionName;
    public TextField minionNeededDrake;
    public TextField minionNeededMana;
    public TextField heroDescription;
    public TextField minionDescription;
    public TextField minionFullHealth;
    public TextField heroFullHealth;
    public TextField minionAttackPower;
    public TextField heroAttackPower;
    public TextField heroAreaOfEffect;
    public TextField minionAreaOfEffect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heroAttackType.getItems().addAll(AttackType.values());
//        heroAttackType.getSelectionModel().getSelectedItem()
//        todo: to get selected item
    }

    public void createNewCustomHero(ActionEvent actionEvent) {
        Hero newHero = (Hero) createNewCustomCard(
                heroName.getText(),
                Integer.parseInt(heroNeededDrake.getText()),
                Integer.parseInt(heroNeededMana.getText()),
                Integer.parseInt(heroFullHealth.getText()),
                Integer.parseInt(heroAttackPower.getText()),
                Integer.parseInt(heroAreaOfEffect.getText())
        );
    }

    private Soldier createNewCustomCard(String name, int priceInDrake, int manaPoint, int fullHealth, int attackPower, int areaOfEffect) {
        Soldier newSoldier = new Soldier();
        newSoldier.setName(name);
        newSoldier.setPriceInDrake(priceInDrake);
        newSoldier.setManaPoint(manaPoint);
        newSoldier.setDescription(heroDescription.getText());
        newSoldier.setFullHealth(fullHealth);
        newSoldier.setAttackPower(attackPower);
        newSoldier.setAreaOfEffect(areaOfEffect);
        return newSoldier;
    }

    public void createNewCustomMinion(ActionEvent actionEvent) {
        Minion newMinion = (Minion) createNewCustomCard(
                minionName.getText(),
                Integer.parseInt(minionNeededDrake.getText()),
                Integer.parseInt(minionNeededMana.getText()),
                Integer.parseInt(minionFullHealth.getText()),
                Integer.parseInt(minionAttackPower.getText()),
                Integer.parseInt(minionAreaOfEffect.getText())
        );
    }
}