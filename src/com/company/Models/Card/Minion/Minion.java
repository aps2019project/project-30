package com.company.Models.Card.Minion;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;

public class Minion extends Card {

//    private MinionType minionType;
    private int xCoordiante, yCoordinate;
    private AttackType attackType;
    private ActivationTime activationTime;
    private int fullHealth;
    private int health;
    private int attackPower;
    private int areaOfEffect;
    private boolean disarmed;

    public void attack(Card targetCard) {
        if (targetCard instanceof Hero) {
            ((Hero) targetCard).decremeantHealth(attackPower);
        }
        else if (targetCard instanceof Minion) {
            ((Minion) targetCard).decremeantHealth(attackPower);
        }
        //TODO Check Counter Buffs
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

//    public MinionType getMinionType() {
//        return minionType;
//    }


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

    public int getAttackPower() {
        return attackPower;
    }
}
