package com.company.Models.Card.Hero;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;

public class Hero extends Soldier {
    private int coolDown;
    private int remainingCoolDown;

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void decrementing(){
        remainingCoolDown--;
    }

    public int getRemainingCoolDown() {
        return remainingCoolDown;
    }

    public void setRemainingCoolDown(int remainingCoolDown) {
        this.remainingCoolDown = remainingCoolDown;
    }

    public int getCoolDownRemaining() {
        return remainingCoolDown;
    }

    public void decremeantCoolDownRemaining() {
        this.coolDown--;
    }

    public Hero clone(){
        Hero hero = new Hero();
        hero.setInGraveCards(this.isInGraveCards());
        hero.setName(this.getName());
        hero.setManaPoint(this.getManaPoint());
        hero.setPriceInDrake(this.getPriceInDrake());
        hero.setHealth(this.getHealth());
        hero.setCell(this.getCell());
        hero.setTargetType(this.getTargetType());
        hero.setAttackType(super.getAttackType());
        hero.setAttackPower(super.getAttackPower());
        hero.setAreaOfEffect(super.getAreaOfEffect());
        hero.setId(Card.createNewCardId(this));
        return hero;
    }
}
