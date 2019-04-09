package com.company.Models.Card.Minion;

import com.company.Models.Card.Card;

public class Minion extends Card {
    MinionType minionType;

    public Minion(MinionType minionType) {
        this.minionType = minionType;
    }

    private int health;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
