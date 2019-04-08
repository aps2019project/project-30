package com.company;

public abstract class Minion extends Card{
    enum AttackType {RANGED, MELEE, HYBRID}
    enum ActivationTime {PASSIVE, ON_ATTACK}
    String name;
    AttackType attackType;
    ActivationTime activationTime;
    int priceInDrake;
    int neededMana;
    int health;
    int attackPower;
    int areaOfEffect;

    abstract void doAction();
}
