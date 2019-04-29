package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

public class ShopController {

    public static void search(String cardName) {
        if (Shop.cardNameExistsInShop(cardName)) {
            System.out.println(Shop.getCardByName(cardName).getId());
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static void buy(Account account, String cardName) {
        if (Shop.cardNameExistsInShop(cardName)) {
            if (account.getDrake() >= Shop.getCardByName(cardName).getPriceInDrake()) {
                switch (Card.getCardType(cardName)) {
                    case "Item":
                        AccountController.addCardToCollection(account, ((Item) Shop.getCardByName(cardName)).clone());
                        break;
                    case "Minion":
                        AccountController.addCardToCollection(account, ((Minion) Shop.getCardByName(cardName)).clone());
                        break;
                    case "Herp":
                        AccountController.addCardToCollection(account, ((Hero) Shop.getCardByName(cardName)).clone());
                        break;
                }
                account.decrementDrake(Shop.getCardByName(cardName).getPriceInDrake());
            } else {
                ConsoleOutput.printErrorMessage(ErrorType.NOTENOUGH_DRAKE);
            }
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }


    public static void sell(Account account, String cardId) {
        if (Shop.cardIdExistsInShop(cardId)) {
            account.incrementDrake(Shop.getCardById(cardId).getPriceInDrake());
            AccountController.removeCardFromCollection(account, Shop.getCardById(cardId));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

}
