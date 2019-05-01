package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
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
                Card newCard = makeCopyForCreatingNewCardInShop(cardName);
                AccountController.addCardToCollection(account,newCard);
                account.decrementDrake(newCard.getPriceInDrake());
            } else {
                ConsoleOutput.printErrorMessage(ErrorType.NOTENOUGH_DRAKE);
            }
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static Card makeCopyForCreatingNewCardInShop(String cardName){
        switch (Card.getCardType(cardName)) {
            case "Item":
                return ((Item) Shop.getCardByName(cardName)).makeCopyForCreatingNewCardInShop();
            case "Minion":
                return ((Minion) Shop.getCardByName(cardName)).makeCopyForCreatingNewCardInShop();
            case "Hero":
                return ((Hero) Shop.getCardByName(cardName)).makeCopyForCreatingNewCardInShop();
//            case "Spell":
//                return ((Spell) Shop.getCardByName(cardName)).makeCopyForCreatingNewCardInShop();

        }
        return null;
    }

    public static void sell(Account account, String cardId) {
        if (Shop.cardIdExistsInShop(cardId)) {
            account.incrementDrake(Shop.getCardById(cardId).getPriceInDrake());
            AccountController.removeCardFromCollection(account, Shop.getCardById(cardId));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }
    }

    public static void initialize() {
        Shop.getShopCollection().getCards().addAll(JsonController.getCards());
    }
}
