package com.company;

public abstract class Minion extends Card{
    enum AttackType {RANGED, MELEE, HYBRID}
    enum ActivationTime {PASSIVE, ON_ATTACK}
    private static String name;
    private static AttackType attackType;
    private static ActivationTime activationTime;
    private static int priceInDrake;
    private static int neededMana;
    private static int FullHealth;
    private int health;
    private static int attackPower;
    private static int areaOfEffect;

    abstract void doAction();

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public ActivationTime getActivationTime() {
        return activationTime;
    }

    public int getPriceInDrake() {
        return priceInDrake;
    }

    public int getNeededMana() {
        return neededMana;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }
}
