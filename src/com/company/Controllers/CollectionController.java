package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.CollectionViews;
import com.company.Views.ConsoleOutput;

//import static com.company.Models.Shop.getCardById;


public class CollectionController {

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
            for (Card card : Collection.getDeckByName(deckName).getDeckCards()) {
                if (card.getId().equals(cardId)) {
                    ConsoleOutput.printErrorMessage(ErrorType.CARD_EXISTS);
                    return;
                }
            }
        }

        Card card = getCardById(cardId);
        if (card instanceof Hero) {
            if (Collection.getDeckByName(deckName).getHeroCard() != null) {
                ConsoleOutput.printErrorMessage(ErrorType.HERO_EXISTS);
            } else {
                Collection.getDeckByName(deckName).setHeroCard(card);
            }
        } else if (card instanceof Item) {
            if (Collection.getDeckByName(deckName).getItemCard() != null) {
                ConsoleOutput.printErrorMessage(ErrorType.ITEM_EXISTS);
            } else {
                Collection.getDeckByName(deckName).setItemCard(card);
            }
        } else {
            if (Collection.getDeckByName(deckName).getDeckCards().size() >= 20) {
                ConsoleOutput.printErrorMessage(ErrorType.DECK_FULL);
                return;
            } else
                Collection.getDeckByName(deckName).getDeckCards().add(card);
        }
    }

    private static boolean deckExist(String deckName) {
        for (Deck deck : Account.getLoggedInAccount().getDecks()) {
            if (deck.getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    private boolean cardExist(String cardId) {
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card.getId().equals(cardId)) {
                return true;
            }
        }
        return false;
    }

    public void selectDeck(String deckName) {
        if (deckExist(deckName) && validateDeck(deckName)) {
            Account.getLoggedInAccount().setMainDeck(Collection.getDeckByName(deckName));
            return;
        }
        ConsoleOutput.printErrorMessage(ErrorType.DECK_VALIDATE);
    }

    public void showDeckIsValidate(String deckName) {
        CollectionViews.printDeckValidation(
                validateDeck(deckName)
        );
    }

    public static boolean validateDeck(String deckName) {
        if (deckExist(deckName)) {
            if (Collection.getDeckByName(deckName).getDeckCards().size() == 20 && Collection.getDeckByName(deckName).getHeroCard() != null) {
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
            Account.getLoggedInAccount().getDecks().remove(Collection.getDeckByName(deckName));
        }
    }

    public Card getCardById(String cardId) {
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card.getId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

    public void remove(String cardId, String deckName) {
        Card card = getCardById(cardId);
        if (card instanceof Minion || card instanceof Spell) {
            for (Card card1 : Collection.getDeckByName(deckName).getDeckCards()) {
                if (card1.getId().equals(cardId)) {
                    Collection.getDeckByName(deckName).getDeckCards().remove(card);
                    return;
                }
            }
        } else if (card instanceof Hero) {
            if (Collection.getDeckByName(deckName).getHeroCard().getId().equals(cardId)) {
                Collection.getDeckByName(deckName).setHeroCard(null);
            }
        } else if (card instanceof Item) {
            if (Collection.getDeckByName(deckName).getItemCard().getId().equals(cardId)) {
                Collection.getDeckByName(deckName).setItemCard(null);
            }
        }
        ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUNDINDECK);
    }

}
