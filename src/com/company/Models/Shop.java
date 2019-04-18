package com.company.Models;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;

public class Shop {

    static Collection shopCollection;

    public static Collection getShopCollection() {
        return shopCollection;
    }

    public static void show() {

    }

    public static void showCollection() {
        //use the same method in CollectionController
    }

    public static boolean cardExistsInShop(String cardName) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean cardExistsInShop(int cardId) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (cardId == card.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getCardIdByName(String cardName) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return card.getId();
                }
            }
        }
        return -1;//that means there is no card with this name
    }

    public static Card getCardByName(String cardName) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return card;
                }
            }
        }
        return null;//that means there is no card with this name
    }

    public static Card getCardById(int cardId) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (cardId == card.getId()) {
                    return card;
                }
            }
        }
        return null;//that means there is no card with this name
    }
}
