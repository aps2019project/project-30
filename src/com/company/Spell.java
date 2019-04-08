package com.company;

public abstract class Spell extends Card {
    String name;
    int priceInDrake;
    int neededMana;
    Buff buff;

    public String getName() {
        return name;
    }

    abstract void getTarget();

    public int getPriceInDrake() {
        return priceInDrake;
    }

    public int getNeededMana() {
        return neededMana;
    }

    abstract void doAction();
}
