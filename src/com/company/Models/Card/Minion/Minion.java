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
            System.out.print(index++);
            if (card instanceof Minion) {
                showCard(card);
            } else if (card instanceof Spell) {
                showSpell(card);
            }
        }
    }

    public static void showCard(Card card) {
        System.out.println("- ID : " + card.getId() +
                "- Type : Minion " +
                "- Name : " + card.getName() +
                "- Class : " + ((Minion) card).getActivationTime() +
                "- AP : " + ((Minion) card).getAttackPower() +
                "- HP : " + ((Minion) card).getFullHealth() +
                "- MP : " + card.getManaPoint() +
                "- Desc : " + card.getDescription() +
                "- Sell cost : " + card.getPriceInDrake());
    }

    public static void showSpell(Card card) {
        System.out.println("- ID : " + card.getId() +
                "- Type : Spell " +
                "- Name : " + card.getName() +
                "- MP : " + card.getManaPoint() +
                "- Desc : " + card.getDescription() +
                "- Sell cost : " + card.getPriceInDrake());
    }
}
