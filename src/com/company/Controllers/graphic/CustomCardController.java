package com.company.Controllers.graphic;

import com.company.Controllers.JsonController;
import com.company.Models.Buff.*;
import com.company.Models.Card.AttackType;
import com.jfoenix.controls.JFXComboBox;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    public StackPane stackPane;
    public StackPane buffCreationMenu;
    public StackPane movementsGifSettingMenu;


    public JFXComboBox<AttackType> heroAttackType;
    public JFXComboBox<AttackType> minionAttackType;
    public JFXComboBox<Buff.Name> minionSpecialPower;
    public JFXComboBox<Buff.Name> heroSpecialPower;


    public TextField heroName;
    public TextField heroNeededDrake;
    public TextField heroNeededMana;
    public TextField heroDescription;
    public TextField heroFullHealth;
    public TextField heroAttackPower;
    public TextField heroAreaOfEffect;
    public TextField heroCoolDown;

    public TextField minionName;
    public TextField minionNeededDrake;
    public TextField minionNeededMana;
    public TextField minionDescription;
    public TextField minionFullHealth;
    public TextField minionAttackPower;
    public TextField minionAreaOfEffect;

    public TextField buffValue;
    public TextField castTime;
    public Label buffName;

    private Buff specialPower;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heroSpecialPower.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            GaussianBlur gaussianBlur = new GaussianBlur();
            stackPane.setEffect(gaussianBlur);
            buffCreationMenu.setVisible(true);
        });
        minionSpecialPower.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            GaussianBlur gaussianBlur = new GaussianBlur();
            stackPane.setEffect(gaussianBlur);
            buffCreationMenu.setVisible(true);
        });

        heroAttackType.getItems().addAll(AttackType.values());
        minionAttackType.getItems().addAll(AttackType.values());
        heroSpecialPower.getItems().addAll(Buff.Name.values());
        minionSpecialPower.getItems().addAll(Buff.Name.values());
    }

    public void createNewCustomHero(ActionEvent actionEvent) {
        Hero newHero = createNewCustomHeroCard(
                heroName.getText(),
                Integer.parseInt(heroNeededDrake.getText()),
                Integer.parseInt(heroNeededMana.getText()),
                Integer.parseInt(heroFullHealth.getText()),
                Integer.parseInt(heroAttackPower.getText()),
                Integer.parseInt(heroAreaOfEffect.getText()),
                heroAttackType.getSelectionModel().getSelectedItem(),
                specialPower,
                Integer.parseInt(heroCoolDown.getText())
        );
        setSpecialPower(null);

        addNewHeroToHeroesJsonFile(newHero);
    }

    public void createNewCustomMinion(ActionEvent actionEvent) {
        Minion newMinion = (Minion) createNewCustomMinionCard(
                minionName.getText(),
                Integer.parseInt(minionNeededDrake.getText()),
                Integer.parseInt(minionNeededMana.getText()),
                Integer.parseInt(minionFullHealth.getText()),
                Integer.parseInt(minionAttackPower.getText()),
                Integer.parseInt(minionAreaOfEffect.getText()),
                minionAttackType.getSelectionModel().getSelectedItem(),
                specialPower
        );
        setSpecialPower(null);

        addNewMinionToMinionsJsonFile(newMinion);
    }

    private void addNewHeroToHeroesJsonFile(Hero newHero) {
        List<Hero> heroes = JsonController.getHeroes();
        heroes.add(newHero);
        JsonController.writeHeroesOnFile(heroes);
    }

    private void addNewMinionToMinionsJsonFile(Minion newMinion) {
        List<Minion> minions = JsonController.getMinions();
        minions.add(newMinion);
        JsonController.writeMinionsOnFile(minions);
    }

    private Minion createNewCustomMinionCard(
            String name,
            int priceInDrake,
            int manaPoint,
            int fullHealth,
            int attackPower,
            int areaOfEffect,
            AttackType attackType,
            Buff specialPower) {
        Minion newMinion = new Minion();
        newMinion.setName(name);
        newMinion.setPriceInDrake(priceInDrake);
        newMinion.setManaPoint(manaPoint);
        newMinion.setDescription(heroDescription.getText());
        newMinion.setFullHealth(fullHealth);
        newMinion.setAttackPower(attackPower);
        newMinion.setAreaOfEffect(areaOfEffect);
        newMinion.setAttackType(attackType);
        newMinion.getBuffsToCast().add(specialPower);
        return newMinion;
    }

    private Hero createNewCustomHeroCard(
            String name,
            int priceInDrake,
            int manaPoint,
            int fullHealth,
            int attackPower,
            int areaOfEffect,
            AttackType attackType,
            Buff specialPower,
            int coolDown) {
        Hero newHero = new Hero();
        newHero.setName(name);
        newHero.setPriceInDrake(priceInDrake);
        newHero.setManaPoint(manaPoint);
        newHero.setDescription(heroDescription.getText());
        newHero.setFullHealth(fullHealth);
        newHero.setAttackPower(attackPower);
        newHero.setAreaOfEffect(areaOfEffect);
        newHero.setAttackType(attackType);
        newHero.getBuffsToCast().add(specialPower);
        newHero.setCoolDown(coolDown);
        return newHero;
    }

    public Buff constructNewCustomBuff(Buff.Name name, int castTime, int value) {
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

    public void createNewCustomBuff(ActionEvent actionEvent) {
        setSpecialPower(constructNewCustomBuff(heroSpecialPower.getSelectionModel().getSelectedItem(),
                Integer.parseInt(castTime.getText()),
                Integer.parseInt(buffValue.getText())
        ));
        clearNewSpecialPowerFields();
        closeBuffCreationMenu();
    }

    private void clearNewSpecialPowerFields() {
        buffValue.clear();
        castTime.clear();
    }

    public void closeBuffCreationMenu() {
        buffCreationMenu.setVisible(false);
        stackPane.setEffect(null);
    }

    public void cancelBuffCreationMenu(){
        heroSpecialPower.getSelectionModel().clearSelection();
        minionSpecialPower.getSelectionModel().clearSelection();
        clearNewSpecialPowerFields();
        closeBuffCreationMenu();
    }

    public void setSpecialPower(Buff specialPower) {
        this.specialPower = specialPower;
    }

    public void openImportGifMenu(){
        stackPane.setEffect(new GaussianBlur());
        movementsGifSettingMenu.setVisible(true);
    }
}
