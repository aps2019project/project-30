package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Spell.Spell;

public enum HeroType {
    DIV_SEFID("Div Sefid", AttackType.MELEE, 8000, 50, 0, 4, 1, 2,"poweresh") {
        @Override
        public void doAction() {

        }
    },
    SIMORGH("Simorgh", AttackType.MELEE, 9000, 50, 0, 4, 5, 8,"poweresh") {
        @Override
        public void doAction() {

        }
    };


    private final String name;
    private final AttackType attackType;
    private final int priceInDrake;
    private final int fullHealth;
    private final int areaOfEffect;
    private final int attackPower;
    private final int manaPoint;
    private final int coolDown;
    private final String spesioalPower;

    HeroType(String name, AttackType attackType, int priceInDrake, int fullHealth, int areaOfEffect, int attackPower, int manaPoint, int coolDown, String spesioalPower) {
        this.name = name;
        this.attackType = attackType;
        this.priceInDrake = priceInDrake;
        this.fullHealth = fullHealth;
        this.areaOfEffect = areaOfEffect;
        this.attackPower = attackPower;
        this.manaPoint = manaPoint;
        this.coolDown = coolDown;
        this.spesioalPower=spesioalPower;
    }

    public abstract void doAction();

    public String getName() {
        return name;
    }
    public String getSpesioalPower() {
        return spesioalPower;
    }


    public AttackType getAttackType() {
        return attackType;
    }

    public int getPriceInDrake() {
        return priceInDrake;
    }

    public int getFullHealth() {
        return fullHealth;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getCoolDown() {
        return coolDown;
    }
}
