package com.company.Models.Card.Minion;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Soldier;

public class Minion extends Card implements Soldier {
    private Cell cell;
    private AttackType attackType;
    private ActivationTime activationTime;
    private int fullHealth;
    private int health;
    private int attackPower;
    private int areaOfEffect;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void attack(Card targetCard) {
        if(!hasBuffByName(Buff.Name.STUN)) {
            if (targetCard instanceof Hero) {
                ((Hero) targetCard).decremeantHealth(attackPower);
                ((Hero) targetCard).counterAttack(this);
            } else if (targetCard instanceof Minion) {
                ((Minion) targetCard).decremeantHealth(attackPower);
                ((Minion) targetCard).counterAttack(this);
            }
            //TODO Check Counter Buffs
        }
    }

    @Override
    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            attack(targetCard);
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void decremeantHealth(int number) {
        this.health -= number;
    }

    public void incrementHealth(int number) {
        this.health += number;
    }

    public void incrementAttackPower(int number) {
        this.attackPower += number;
    }

    public void decrementAttackPower(int number) {
        this.attackPower -= number;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public boolean hasBuffByName(Buff.Name buffName) {
        for (Buff buff : getBuffsCasted()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }

    public Minion clone(){
        Minion minion = new Minion();
        minion.setDescription(this.getDescription());
        minion.setInGraveCards(this.isInGraveCards());
        minion.setName(this.getName());
        minion.setManaPoint(this.getManaPoint());
        minion.setPriceInDrake(this.getPriceInDrake());
        minion.setHealth(this.getHealth());
        minion.setCell(this.getCell());
        minion.setTargetType(this.getTargetType());
        minion.setId(Card.createNewId());
        return minion;
    }
}
