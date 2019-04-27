package com.company.Models.Card.Minion;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Soldier;

public class Minion extends Card implements Soldier {
    private int xCoordiante, yCoordinate;
    private AttackType attackType;
    private ActivationTime activationTime;
    private int fullHealth;
    private int health;
    private int attackPower;
    private int areaOfEffect;

    @Override
    public void attack(Card targetCard) {
        if (targetCard instanceof Hero) {
            ((Hero) targetCard).decremeantHealth(attackPower);
            ((Hero) targetCard).counterAttack(this);
        }
        else if (targetCard instanceof Minion) {
            ((Minion) targetCard).decremeantHealth(attackPower);
            ((Minion) targetCard).counterAttack(this);
        }
        //TODO Check Counter Buffs
    }

    @Override
    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            attack(targetCard);
        }
    }

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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
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

    public int getAttackPower() {
        return attackPower;
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
}
