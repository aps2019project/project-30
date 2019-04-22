package com.company.Models.Card.Minion;


import com.company.Models.Card.AttackType;

public enum MinionType {

    KAMANDAR_FARS("Kamandar Fars", AttackType.RANGED, null, 300, 2, 6, 4, 7) {
        @Override
        public void doAction() {

        }
    },
    SHAMSHIRZAN_FARS("Shamshirzan Fars", AttackType.MELEE, ActivationTime.ON_ATTACK, 400, 2, 6, 4, 0) {
        @Override
        public void doAction() {

        }
    },
    PAHLEVAN_FARS("Pahlevan Fars", AttackType.MELEE, ActivationTime.ON_ATTACK, 600, 9, 24, 6, 0){
        @Override
        public void doAction() {

        }
    };


    private final String name;
    private final AttackType attackType;
    private final ActivationTime activationTime;
    private final int priceInDrake;
    private final int neededMana;
    private final int fullHealth;
    private final int attackPower;
    private final int areaOfEffect;

    MinionType(String name, AttackType attackType, ActivationTime activationTime, int priceInDrake, int neededMana, int fullHealth, int attackPower, int areaOfEffect) {
        this.name = name;
        this.attackType = attackType;
        this.activationTime = activationTime;
        this.priceInDrake = priceInDrake;
        this.neededMana = neededMana;
        this.fullHealth = fullHealth;
        this.attackPower = attackPower;
        this.areaOfEffect = areaOfEffect;
    }

    public abstract void doAction();

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

    public int getFullHealth() {
        return fullHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }
}
