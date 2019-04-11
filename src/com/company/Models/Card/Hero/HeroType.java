package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Spell.Spell;

public enum HeroType {
    DIV_SEFID("Div Sefid", AttackType.MELEE, 8000, 50, 0, 4, 1, 2) {
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

    HeroType(String name, AttackType attackType, int priceInDrake, int fullHealth, int areaOfEffect, int attackPower, int manaPoint, int coolDown) {
        this.name = name;
        this.attackType = attackType;
        this.priceInDrake = priceInDrake;
        this.fullHealth = fullHealth;
        this.areaOfEffect = areaOfEffect;
        this.attackPower = attackPower;
        this.manaPoint = manaPoint;
        this.coolDown = coolDown;
    }

    public abstract void doAction();
}
