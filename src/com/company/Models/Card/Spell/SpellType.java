package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;

public enum SpellType {
    TOTAL_DISARM("Total Disarm", 1000, 0, null) {
        @Override
        public void doAction() {
            
        }

        @Override
        public void getTargets() {

        }
    };

    private final String name;
    private final int priceInDrake;
    private final int neededMana;
    private final Buff buff;

    SpellType(String name, int priceInDrake, int neededMana, Buff buff) {
        this.name = name;
        this.priceInDrake = priceInDrake;
        this.neededMana = neededMana;
        this.buff = buff;
    }

    public abstract void doAction();
    public abstract void getTargets();
}
