package com.company.Models.Card.Minion;


import com.company.Models.Card.ActivationTime;
import com.company.Models.Card.AttackType;

public enum MinionType {

    KAMANDAR_FARS("Kamandar Fars", AttackType.RANGED, ActivationTime.ON_ATTACK, 300, 2, 6, 4, 7) {
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
}
