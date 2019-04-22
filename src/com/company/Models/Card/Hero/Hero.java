package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Hero extends Card {
    private boolean disarmed = false;
    private int xCoordiante, yCoordinate;
    private HeroType heroType;
    private AttackType attackType;
    private int fullHealth;
    private int areaOfEffect;
    private int attackPower;
    private int coolDown;
    private int health;
    private int coolDownRemaining;

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

    public HeroType getHeroType() {
        return heroType;
    }

    public int getHealth() {
        return health;
    }

    public int getCoolDownRemaining() {
        return coolDownRemaining;
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
}
