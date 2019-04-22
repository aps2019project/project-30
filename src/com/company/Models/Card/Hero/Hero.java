package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Hero extends Card {
    private boolean disarmed = false;
    private HeroType heroType;
    private String name;
    private AttackType attackType;
    private int priceInDrake;
    private int fullHealth;
    private int areaOfEffect;
    private int attackPower;
    private int manaPoint;
    private int coolDown;
    private int health;
    private int coolDownRemaining;


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
