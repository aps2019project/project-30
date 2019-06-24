package com.company.Controllers.graphic;

import com.company.Models.Buff.*;
import com.company.Models.Card.AttackType;
import com.jfoenix.controls.JFXComboBox;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    public JFXComboBox<AttackType> heroAttackType;
    public JFXComboBox<AttackType> minionAttackType;
    public JFXComboBox<Buff.Name> minionSpecialPower;
    public JFXComboBox<Buff.Name> heroSpecialPower;


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
    public TextField heroCoolDown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.exit(0);
        heroAttackType.getItems().addAll(AttackType.values());
        minionAttackType.getItems().addAll(AttackType.values());
        heroSpecialPower.getItems().addAll(Buff.Name.values());
        minionSpecialPower.getItems().addAll(Buff.Name.values());
    }

    public void createNewCustomHero(ActionEvent actionEvent) {
        Hero newHero = (Hero) createNewCustomCard(
                heroName.getText(),
                Integer.parseInt(heroNeededDrake.getText()),
                Integer.parseInt(heroNeededMana.getText()),
                Integer.parseInt(heroFullHealth.getText()),
                Integer.parseInt(heroAttackPower.getText()),
                Integer.parseInt(heroAreaOfEffect.getText()),
                heroAttackType.getSelectionModel().getSelectedItem(),
                null
        );
        newHero.setCoolDown(Integer.parseInt(heroCoolDown.getText()));
    }

    private Soldier createNewCustomCard(
            String name,
            int priceInDrake,
            int manaPoint,
            int fullHealth,
            int attackPower,
            int areaOfEffect,
            AttackType attackType,
            Buff specialPower) {
        Soldier newSoldier = new Soldier();
        newSoldier.setName(name);
        newSoldier.setPriceInDrake(priceInDrake);
        newSoldier.setManaPoint(manaPoint);
        newSoldier.setDescription(heroDescription.getText());
        newSoldier.setFullHealth(fullHealth);
        newSoldier.setAttackPower(attackPower);
        newSoldier.setAreaOfEffect(areaOfEffect);
        newSoldier.setAttackType(attackType);
        newSoldier.getBuffsToCast().add(specialPower);
        return newSoldier;
    }

    public void createNewCustomMinion(ActionEvent actionEvent) {
        Minion newMinion = (Minion) createNewCustomCard(
                minionName.getText(),
                Integer.parseInt(minionNeededDrake.getText()),
                Integer.parseInt(minionNeededMana.getText()),
                Integer.parseInt(minionFullHealth.getText()),
                Integer.parseInt(minionAttackPower.getText()),
                Integer.parseInt(minionAreaOfEffect.getText()),
                minionAttackType.getSelectionModel().getSelectedItem(),
                null
        );
    }

    public Buff createNewCustomBuff(Buff.Name name, int castTime, int value) {
        Buff newBuff = null;
        switch (name) {
            case ANTI:
                newBuff = new AntiBuff(null, castTime, 0, value);
                break;
            case ATTACK_POWER:
                newBuff = new AttackPowerBuff(null, castTime, 0, value);
                break;
            case ATTACK_WEAKNESS:
                newBuff = new AttackWeaknessBuff(null, castTime, 0, value);
                break;
            case DISARM:
                newBuff = new DisarmBuff(null, castTime, 0, value);
                break;
            case DISPELL:
                newBuff = new DispelBuff(null, castTime, 0, value);
                break;
            case HEALTH_POWER:
                newBuff = new HealthPowerBuff(null, castTime, 0, value);
                break;
            case HEALTH_WEAKNESS:
                newBuff = new HealthWeaknessBuff(null, castTime, 0, value);
                break;
            case HOLY:
                newBuff = new HolyBuff(null, castTime, 0, value);
                break;
            case MANA:
                newBuff = new ManaBuff(null, castTime, 0, value);
                break;
            case POSION:
                newBuff = new PosionBuff(null, castTime, 0, value);
                break;
            case STUN:
                newBuff = new StunBuff(null, castTime, 0, value);
                break;
        }
        return newBuff;
    }
}