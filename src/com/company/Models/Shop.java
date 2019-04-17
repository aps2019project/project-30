package com.company.Models;

import com.company.Controllers.ShopController;
import com.company.Controllers.ShopController;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;

public class Shop {

    static Collection shopCollection;

    public static Collection getShopCollection() {
        return shopCollection;
    }

    public static void search(String name) {
        if (Shop.cardExistsInShop(name)) {
            ShopController.search(name);
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static void buy(Account account, String name) {
        if (cardExistsInShop(name)) {
            if (account.getDrake() >= getCardByName(name).getPriceInDrake()) {
                ShopController.buy(account, getCardByName(name));
            }else {
                ConsoleOutput.printErrorMessage(ErrorType.NOTENOUGH_DRAKE);
            }
        }else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public void sell(Account account, int cardId) {
        if(cardExistsInShop(cardId)){
            ShopController.sell(account,getCardById(cardId));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static void show() {

    }

    public static void showCollection() {

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
