package com.company.Models.Card.Hero;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Soldier {
    private int coolDown;
    private int remainingCoolDown;

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void decrementing() {
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

    public Hero clone() {
        Hero hero = new Hero();
        hero.setInGraveCards(this.isInGraveCards());
        hero.setName(this.getName());
        hero.setManaPoint(this.getManaPoint());
        hero.setPriceInDrake(this.getPriceInDrake());
        hero.setHealth(this.getHealth());
        hero.setFullHealth(getFullHealth());
        hero.setCell(this.getCell());
        hero.setTargetType(this.getTargetType());
        hero.setAttackType(getAttackType());
        hero.setAttackPower(getAttackPower());
        hero.setAreaOfEffect(getAreaOfEffect());
        return hero;
    }

    public Hero makeCopyForCreatingNewCardInShop() {
        Hero hero = this.clone();
        hero.setId(Card.createNewCardIdToCreatingNewCardInShop());
        return hero;
    }

    public Hero makeCopyForCreatingNewCardInBattle() {
        Hero hero = this.clone();
        hero.setId(Card.createNewCardIdToCreatingNewCardInBattle(this.getName()));
        return hero;
    }

    public static void showHeroes(List<Card> cards) {
        System.out.println("Heroes :");
        int index = 1;
        for (Card card : cards) {
            if (card instanceof Hero) {
                System.out.println(index++);
                showHero((Hero)card);
            }
        }
    }

    public static void showHero(Hero hero) {
        System.out.println("- ID : " + hero.getId() +
                "- Name : " + hero.getName() +
                "- AP : " + hero.getAttackPower() +
                "- HP : " + hero.getFullHealth() +
                "- Desc : " + hero.getDescription() +
                "- Sell cost : " + hero.getPriceInDrake());
    }
}
