package com.company.Models.Buff;

import com.company.Models.Card.Spell.Spell;

public abstract class Buff {
    private enum Type {POSSETIVE, NEGATIVE}
    private enum Name {HOLY, POWER, POSION, WEAKNESS, STUN, DISARM}

    private Type type;
    private Name name;
    private Spell spell;

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public Spell getSpell() {
        return spell;
    }

    public abstract void doAction();
}
