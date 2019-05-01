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

    public static void showCards(ArrayList<Card> cards) {
        System.out.println("Cards :");
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) instanceof Minion) {
                System.out.println((i + 1) + "- Type : Minion " +
                        "- Name : " + cards.get(i).getName() +
                        "- Class : " + ((Minion) cards.get(i)).getActivationTime() +
                        "- AP : " + ((Minion) cards.get(i)).getAttackPower() +
                        "- HP : " + ((Minion) cards.get(i)).getFullHealth() +
                        "- MP : " + cards.get(i).getManaPoint() +
                        "- Desc : " + Shop.getShopCollection().getCards().get(i).getDescription() +
                        "- Sell cost : " + cards.get(i).getPriceInDrake());
            } else if (cards.get(i) instanceof Spell) {
                System.out.println((i + 1) + "- Type : Spell " +
                        "- Name : " + cards.get(i).getName() +
                        "- MP : " + cards.get(i).getManaPoint() +
                        "- Desc : " + cards.get(i).getDescription() +
                        "- Sell cost : " + cards.get(i).getPriceInDrake());
            }
        }
    }
}
