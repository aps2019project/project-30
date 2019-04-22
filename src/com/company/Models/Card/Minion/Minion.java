package com.company.Models.Card.Minion;

import com.company.Models.Card.Card;

public class Minion extends Card {

    private MinionType minionType;
    private int health;

    public Minion(MinionType minionType) {
        this.minionType = minionType;
        super.setName(minionType.getName());
        super.setManaPoint(minionType.getNeededMana());
    }

    public MinionType getMinionType() {
        return minionType;
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
}
