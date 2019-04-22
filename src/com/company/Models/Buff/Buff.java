package com.company.Models.Buff;

import com.company.Models.Card.Spell.Spell;

public abstract class Buff {
    private enum Type {POSSETIVE, NEGATIVE}
    private enum Name {HOLY, POWER, MANA, ANTI, POSION, WEAKNESS, STUN, DISARM}

    private Type type;
    private Name name;

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public abstract void doAction();
}
