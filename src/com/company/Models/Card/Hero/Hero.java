package com.company.Models.Card.Hero;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;

public class Hero extends Card implements Soldier {
    private boolean disarmed = false;
    private Cell cell;
    private AttackType attackType;
    private int fullHealth;
    private int health;
    private int areaOfEffect;
    private int attackPower;
    private int coolDown;
    private int remainingCoolDown;

    public Cell getCell() {
        return cell;
    }

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

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setAttackType(AttackType attackType){
        this.attackType = attackType;
    }

    public int getHealth() {
        return health;
    }

    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoolDownRemaining() {
        return remainingCoolDown;
    }

    public void decremeantCoolDownRemaining() {

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

    @Override
    public boolean hasBuffByName(Buff.Name buffName) {
        for (Buff buff : getBuffsCasted()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }

    public int getAttackPower() {
        return attackPower;
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

    public void setDisarmed(boolean disarmed) {
        this.disarmed = disarmed;
    }

    public void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            attack(targetCard);
        }
    }

    @Override
    public Hero clone(){
        Hero hero = new Hero();
        hero.setInGraveCards(this.isInGraveCards());
        hero.setName(this.getName());
        hero.setManaPoint(this.getManaPoint());
        hero.setPriceInDrake(this.getPriceInDrake());
        hero.setHealth(this.getHealth());
        hero.setCell(this.getCell());
        hero.setTargetType(this.getTargetType());
        hero.setAttackType(this.attackType);
        hero.setDisarmed(this.disarmed);
        hero.setAttackPower(this.attackPower);
        hero.setAreaOfEffect(this.areaOfEffect);
        return hero;
    }

    public Hero makeCopyForCreatingNewCardInShop(){
        Hero hero = this.clone();
        hero.setId(Card.createNewCardIdToCreatingNewCardInShop());
        return hero;
    }

    public Hero makeCopyForCreatingNewCardInBattle(){
        Hero hero = this.clone();
        hero.setId(Card.createNewCardIdToCreatingNewCardInBattle(this.getName()));
        return hero;
    }
}
