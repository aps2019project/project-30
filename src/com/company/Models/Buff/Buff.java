package com.company.Models.Buff;

import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Buff {
    public enum Type {POSSETIVE, NEGATIVE}
    public enum Name {HOLY, POWER, MANA, ANTI, POSION, WEAKNESS, STUN, DISARM}

    private Type type;
    private Name name;
    protected Card cardToCast;

    protected String description;
    protected Type antiBuff;
    protected int remTurnToBeInactive;
    protected int remTurnToCast;
    protected int value;

    public Buff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        this.antiBuff = antiBuff;
        this.remTurnToBeInactive = remTurnToBeInactive;
        this.remTurnToCast = remTurnToCast;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public abstract void cast();

    boolean isActive() {
        return remTurnToBeInactive == 0;
    }

    boolean canCastThisTurn() {
        return remTurnToCast == 0;
    }

    void decrementCounters() {
        remTurnToCast--;
        remTurnToBeInactive--;
    }

    public void setCardToCast(Card cardToCast) {
        this.cardToCast = cardToCast;
    }
}
