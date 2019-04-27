package com.company.Models.Card.Hero;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;

public abstract class Hero extends Card implements Soldier {
    private boolean disarmed = false;
    private int xCoordiante, yCoordinate;
    private AttackType attackType;
    private int fullHealth;
    private int health;
    private int areaOfEffect;
    private int attackPower;
    private int coolDown;
    private int remainingCoolDown;

    public int getxCoordiante() {
        return xCoordiante;
    }

    public void setxCoordiante(int xCoordiante) {
        this.xCoordiante = xCoordiante;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getHealth() {
        return health;
    }

    public int getCoolDownRemaining() {
        return remainingCoolDown;
    }

    public void decremeantCoolDownRemaining() {

    }

    public void decremeantHealth(int number) {
        this.health -= number;
    }

    public void incrementHealth(int number) {
        this.health += number;
    }

    public void incrementAttackPower(int number) {
        this.attackPower += number;
    }

    public void decrementAttackPower(int number) {
        this.attackPower -= number;
    }

    @Override
    public boolean hasBuffByName(Buff.Name buffName) {
        for (Buff buff : getBuffsCasted()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void attack(Card targetCard) {

    }

    @Override
    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            attack(targetCard);
        }
    }
}
