package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.CollectionViews;
import com.company.Views.ConsoleOutput;


public class CollectionController {
    //public static void
    public void show() {
        CollectionViews.show(Account.getLoggedInAccount().getCollection().getCards());
    }

    public void showAllDecks() {
        int counter = 0;
        for (Deck deck : Account.getLoggedInAccount().getDecks()) {
            counter++;
            CollectionViews.showNumberofDeck(counter);
            CollectionViews.showDeck(deck);
        }
    }

    public void showDeck(String deckNmae) {
        CollectionViews.showDeck(getDeckByName(deckNmae));
    }

    public void search(String cardName) {
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card.getName().equals(cardName)) {
                CollectionViews.showCardId(card.getId());
            }
        }
    }

    public void addCard(String cardId, String deckName) {
        if (cardExist(cardId) == false) {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
            return;
        }
        if (deckExist(deckName)) {
            for (Card card : getDeckByName(deckName).getDeckCards()) {
                if (card.getId().equals(cardId)) {
                    ConsoleOutput.printErrorMessage(ErrorType.CARD_EXISTS);
                    return;
                }
            }
        }
        if (getDeckByName(deckName).getDeckCards().size() >= 20) {
            ConsoleOutput.printErrorMessage(ErrorType.DECK_FULL);
            return;
        }
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card.getId().equals(card.getId())) {
                if (card instanceof Hero) {
                    if (getDeckByName(deckName).getHeroCard() != null) {
                        ConsoleOutput.printErrorMessage(ErrorType.HERO_EXISTS);
                        return;
                    } else {
                        getDeckByName(deckName).setHeroCard(card);
                        return;
                    }
                } else if (card instanceof Item) {
                    if (getDeckByName(deckName).getItemCard() != null) {
                        ConsoleOutput.printErrorMessage(ErrorType.ITEM_EXISTS);
                        return;
                    } else {
                        getDeckByName(deckName).setItemCard(card);
                        return;
                    }
                } else {
                    getDeckByName(deckName).getDeckCards().add(card);
                }
            }
        }

    }

    private boolean deckExist(String deckName) {
        for (Deck deck : Account.getLoggedInAccount().getDecks()) {
            if (deck.getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    public boolean cardExist(String cardId) {
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card.getId().equals(cardId)) {
                return true;
            }
        }
        return false;
    }

    public void selectDeck(String deckName) {
        if (deckExist(deckName) && validateDeck(deckName)) {
            Account.getLoggedInAccount().setMainDeck(getDeckByName(deckName));
            return;
        }
        ConsoleOutput.printErrorMessage(ErrorType.DECK_VALIDATE);
    }

    public void showDeckIsValidate(String deckName) {
        CollectionViews.printDeckValidation(
                validateDeck(deckName)
        );
    }

    public boolean validateDeck(String deckName) {
        Deck deck;
        if (deckExist(deckName)) {
            if (getDeckByName(deckName).getDeckCards().size() == 20 && getDeckByName(deckName).getHeroCard() != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void createDeck(String deckName) {
        if (deckExist(deckName)) {
            ConsoleOutput.printErrorMessage(ErrorType.DECK_EXISTS);
            return;
        }
        Deck deck = new Deck(deckName);
        Account.getLoggedInAccount().getDecks().add(deck);
    }

    public void deleteDeck(String deckName) {
        if (deckExist(deckName)) {
            Account.getLoggedInAccount().getDecks().remove(getDeckByName(deckName));
        }
    }

    private Deck getDeckByName(String deckName) {
        for (Deck deck : Account.getLoggedInAccount().getDecks()) {
            if (deck.getName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }

    public void remove(String cardId, String deckName) {
        for (Card card : getDeckByName(deckName).getDeckCards()) {
            if (card.getId().equals(cardId)) {
                getDeckByName(deckName).getDeckCards().remove(card);
                return;
            }
        }
        ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUNDINDECK);
    }

    public void help() {
        CollectionViews.printHelp();
    }

    public Card getCardById(String cardId) {
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if(card.getId().equals(cardId))
                return card;
        }
        return null;
    }
}
