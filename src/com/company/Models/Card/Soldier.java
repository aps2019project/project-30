package com.company.Models.Card;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;

public class Soldier extends Card {
    private Cell cell;
    private AttackType attackType;
    private int fullHealth;
    private int health;
    private int areaOfEffect;
    private int attackPower;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public int getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void incrementHealth(int health) {
        this.health += health;
    }

    public void decrementHealth(int health) {
        this.health -= health;
    }

    public void incrementAttackPower(int attackPower) {
        this.attackPower += attackPower;
    }

    public void decrementAttackPower(int attackPower) {
        this.attackPower -= attackPower;
    }

    public void attack(Card targetCard) {
        if(!hasBuffByName(Buff.Name.STUN)) {
            ((Soldier) targetCard).decrementHealth(getAttackPower());
            ((Soldier) targetCard).counterAttack(this);
            //TODO Check Counter Buffs
        }
    }

    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            attack(targetCard);
        }
    }

    public boolean hasBuffByName(Buff.Name buffName) {
        for (Buff buff : getBuffsCasted()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }
}
