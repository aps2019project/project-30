package com.company.Models;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Card.Item.Item;

import java.util.ArrayList;

public class Shop {
    static ArrayList<Item> collectibleItems = new ArrayList<>();
    static Collection shopCollection = new Collection();

    public static Collection getShopCollection() {
        return shopCollection;
    }

    public static void showCollection() {
        //use the same method in CollectionController (maybe CollectionView , i don't know!)
    }

    public static boolean cardNameExistsInShop(String cardName) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equalsIgnoreCase(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Item> getCollectibleItems() {
        return collectibleItems;
    }

    public static boolean cardIdExistsInShop(String cardId) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getId().equals(cardId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Card getCardByName(String cardName) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equalsIgnoreCase(cardName)) {
                    return card;
                }
            }
        }
        return null;//that means there is no card with this name
    }

    public static Card getCardById(String cardId) {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getId().equals(cardId)) {
                    return card;
                }
            }
        }
        return null;//that means there is no card with this name
    }


}
