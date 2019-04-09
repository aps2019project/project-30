package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;

public abstract class Spell extends Card {
    private String name;
    private int priceInDrake;
    private int neededMana;
    private Buff buff;

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
