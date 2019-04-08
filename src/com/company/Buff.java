package com.company;

public abstract class Buff {
    private enum Type {POSSETIVE, NEGATIVE}
    private enum Name {HOLY, POWER}

    Type type;
    Name name;
    private Spell spell;

    public abstract void doAction();
}
