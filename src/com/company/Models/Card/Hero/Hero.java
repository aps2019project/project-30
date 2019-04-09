package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Hero extends Card {

    private static String name;
    private static AttackType attackType;
    private static int priceInDrake;
    private static int FullHealth;
    private int health;
    private int attackPower;
    private static int AreaOfEffect;
    private static int manaPoint;
    private static int coolDown;
    private int coolDownRemaining;
    private static Spell spell;

    public String getName() {
        return name;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public static int getPriceInDrake() {
        return priceInDrake;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAreaOfEffect() {
        return AreaOfEffect;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public int getCoolDownRemaining() {
        return coolDownRemaining;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setCoolDownRemaining(int coolDownRemaining) {
        this.coolDownRemaining = coolDownRemaining;
    }

    public void decremeantHealth(int health) {

    }
}
