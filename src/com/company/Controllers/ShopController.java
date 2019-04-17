package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Shop;

public class ShopController {

    public static boolean cardExistsInShop(String cardName) {
        if(Shop.getShopCollection() != null && Shop.getShopCollection().getCards() != null) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getCardIdByName(String cardName) {
        for (Card card : Shop.getShopCollection().getCards()) {
            if (card.getName().equals(cardName)) {
                return card.getId();
            }
        }
        return -1;//that means there is no card with this name
    }
}
