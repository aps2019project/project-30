package com.company.Controllers.graphic;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class CustomCardController {
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

    public Hero createHero(ActionEvent actionEvent) {
        Hero newHero = (Hero) createNewCustomCard(
                heroName.getText(),
                Integer.parseInt(heroNeededDrake.getText()),
                Integer.parseInt(heroNeededMana.getText()),
                Integer.parseInt(heroFullHealth.getText()),
                Integer.parseInt(heroAttackPower.getText()),
                Integer.parseInt(heroAreaOfEffect.getText())
        );
        return newHero;
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

    public Minion createMinion(ActionEvent actionEvent) {
        Minion newMinion = (Minion) createNewCustomCard(
                minionName.getText(),
                Integer.parseInt(minionNeededDrake.getText()),
                Integer.parseInt(minionNeededMana.getText()),
                Integer.parseInt(minionFullHealth.getText()),
                Integer.parseInt(minionAttackPower.getText()),
                Integer.parseInt(minionAreaOfEffect.getText())
        );
        return newMinion;
    }
}
