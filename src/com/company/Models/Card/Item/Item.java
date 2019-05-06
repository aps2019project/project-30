package com.company.Models.Card.Item;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class Item extends Card {

    @Override
    public Item clone() {
        Item item = new Item();
        item.setDescription(this.getDescription());
        item.setInGraveCards(this.isInGraveCards());
        item.setName(this.getName());
        item.setManaPoint(this.getManaPoint());
        item.getBuffsToCast().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsToCast()).clone()));
        item.getBuffsCasted().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsCasted()).clone()));
        item.setPriceInDrake(this.getPriceInDrake());
        item.setTargetType(this.getTargetType());
        return item;
    }

    public Item makeCopyForCreatingNewCardInShop() {
        Item item = this.clone();
        item.setId(Card.createNewCardIdToCreatingNewCardInShop());
        return item;
    }

    public Item makeCopyForCreatingNewCardInBattle() {
        Item item = this.clone();
        item.setId(Card.createNewCardIdToCreatingNewCardInBattle(this.getName()));
        return item;
    }

    public static void showItems(List<Card> cards) {
        System.out.println("Items :");
        int index = 1;
        for (Card card : cards) {
            if (card instanceof Item) {
                System.out.println(index++);
                showItem((Item)card);
            }
        }
    }

    public static void showItem(Item item) {
        System.out.println("- ID : " + item.getId() +
                "- Name : " + item.getName() +
                "- Desc : " + item.getDescription() +
                "- Sell cost : " + item.getPriceInDrake());
    }

    public static void showItemInBattle(Item item) {
        System.out.println("- ID : " + item.getId() +
                "- Name : " + item.getName() +
                "- Desc : " + item.getDescription());
    }
}
