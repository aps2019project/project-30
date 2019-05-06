package com.company.Models.Card.Hero;

import com.company.Models.Battle.Battle;
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

    public void setRemainingCoolDownByCooldown() {
        this.remainingCoolDown = coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void decrementRemainingCoolDown() {
        if(remainingCoolDown!=0)
            remainingCoolDown--;
    }

    @Override
    public void decrementHealth(int health) {
        super.decrementHealth(health);
        Battle.getPlayingBattle().getBattleController().
    }

    public int getCoolDownRemaining() {
        return remainingCoolDown;
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
        hero.setCoolDown(this.coolDown);
        hero.getBuffsToCast().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsToCast()).clone()));
        hero.getBuffsCasted().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsCasted()).clone()));
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

    public static void showHeroInBattle(Hero hero) {
        System.out.println("- ID : " + hero.getId() +
                "- Name : " + hero.getName() +
                "- AP : " + hero.getAttackPower() +
                "- HP : " + hero.getHealth() +
                "- Desc : " + hero.getDescription());
    }


}
