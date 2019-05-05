package com.company.Models.Card;

import com.company.Models.Battle.Battle;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.TargetType;
import com.company.Models.User.Account;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    private String id;
    private String name;
    private int manaPoint;
    private int priceInDrake;
    private String description;
    private boolean isInGraveCards = false;
    private TargetType targetType;
    private List<Buff> buffsToCast = new ArrayList<>();
    private List<Buff> buffsCasted = new ArrayList<>();
    private static int lastShopCardId = 0;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInGraveCards() {
        return isInGraveCards;
    }

    public void setInGraveCards(boolean inGraveCards) {
        isInGraveCards = inGraveCards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getPriceInDrake() {
        return priceInDrake;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public void setPriceInDrake(int priceInDrake) {
        this.priceInDrake = priceInDrake;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public List<Buff> getBuffsToCast() {
        return buffsToCast;
    }

    public List<Buff> getBuffsCasted() {
        return buffsCasted;
    }

    public static String createNewCardIdToCreatingNewCardInShop(){
        lastShopCardId++;
        return String.valueOf(lastShopCardId);
    }

    public static String createNewCardIdToCreatingNewCardInBattle(String cardName) {
        Battle.incrementlastBattleCardId(1);
        return Battle.getPlayingBattle().getTurnToPlay().getAccount().getUsername() + "_" + cardName + "_" + Battle.getLastBattleCardId();
    }

    public static String getCardType(String cardName) {
        Card card = Shop.getCardByName(cardName);
        if (card instanceof Minion) {
            return "Minion";
        } else if (card instanceof Item) {
            return "Item";
        } else if (card instanceof Hero) {
            return "Hero";
        } else if (card instanceof Spell) {
            return "Spell";
        }
        return "";
    }

    public static void showCards(List<Card> cards) {
        System.out.println("Cards :");
        int index = 1;
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.print(index++);
                Minion.showMinion((Minion)card);
            } else if (card instanceof Spell) {
                System.out.print(index++);
                Spell.showSpell((Spell)card);
            }
        }
    }
}
