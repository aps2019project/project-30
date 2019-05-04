package com.company.Models.Card.Item;

import com.company.Models.Card.Card;

import java.util.ArrayList;

public class Item extends Card {

    @Override
    public Item clone() {
        Item item = new Item();
        item.setDescription(this.getDescription());
        item.setInGraveCards(this.isInGraveCards());
        item.setName(this.getName());
        item.setManaPoint(this.getManaPoint());
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

    public static void showItems(ArrayList<Card> cards) {
        System.out.println("Items :");
        int index = 1;
        for (Card card : cards) {
            if (card instanceof Item) {
                System.out.println(index++ +
                        "- ID : " + card.getId() +
                        "- Name : " + card.getName() +
                        "- Desc : " + card.getDescription() +
                        "- Sell cost : " + card.getPriceInDrake());
            }
        }
    }
}
