package com.company.Models.Card.Minion;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;

import java.util.ArrayList;
import java.util.List;

public class
Minion extends Soldier {
    private ActivationTime activationTime;

    @Override
    public Minion clone() {
        Minion minion = new Minion();
        minion.setDescription(this.getDescription());
        minion.setInGraveCards(this.isInGraveCards());
        minion.setName(this.getName());
        minion.setManaPoint(this.getManaPoint());
        minion.setPriceInDrake(this.getPriceInDrake());
        minion.setHealth(this.getHealth());
        minion.setCell(this.getCell());
        minion.setTargetType(this.getTargetType());
        return minion;
    }

    public Minion makeCopyForCreatingNewCardInShop() {
        Minion minion = this.clone();
        minion.setId(Card.createNewCardIdToCreatingNewCardInShop());
        return minion;
    }

    public Minion makeCopyForCreatingNewCardInBattle() {
        Minion minion = this.clone();
        minion.setId(Card.createNewCardIdToCreatingNewCardInBattle(this.getName()));
        return minion;
    }

    public ActivationTime getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(ActivationTime activationTime) {
        this.activationTime = activationTime;
    }

    public static void showCards(List<Card> cards) {
        System.out.println("Cards :");
        int index = 1;
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.print(index++);
                showCard((Minion)card);
            } else if (card instanceof Spell) {
                System.out.print(index++);
                showSpell((Spell)card);
            }
        }
    }

    public static void showCard(Minion minion) {
        System.out.println("- ID : " + minion.getId() +
                "- Type : Minion " +
                "- Name : " + minion.getName() +
                "- Class : " + minion.getActivationTime() +
                "- AP : " + minion.getAttackPower() +
                "- HP : " + minion.getFullHealth() +
                "- MP : " + minion.getManaPoint() +
                "- Desc : " + minion.getDescription() +
                "- Sell cost : " + minion.getPriceInDrake());
    }

    public static void showSpell(Spell spell) {
        System.out.println("- ID : " + spell.getId() +
                "- Type : Spell " +
                "- Name : " + spell.getName() +
                "- MP : " + spell.getManaPoint() +
                "- Desc : " + spell.getDescription() +
                "- Sell cost : " + spell.getPriceInDrake());
    }
}
