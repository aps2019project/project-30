package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Views.ConsoleOutput;

public class ShopController {

    public static void search(String cardName) {
        if (cardExistsInShop(cardName)) {
            System.out.println(getCardIdByName(cardName));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static boolean cardExistsInShop(String cardName) {
        if (Shop.getShopCollection() != null && Shop.getShopCollection().getCards() != null) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getCardIdByName(String cardName) {
        if (Shop.getShopCollection() != null && Shop.getShopCollection().getCards() != null) {
            for (Card card : Shop.getShopCollection().getCards()) {
                if (card.getName().equals(cardName)) {
                    return card.getId();
                }
            }
        }
        return -1;//that means there is no card with this name
    }
}
