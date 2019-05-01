package com.company.Views.Console;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.User.Account;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionViews {
    public static void showNumberOfDeck(int n) {
        System.out.println(n + " : deck_" + n);
    }

    public static void showCardId(String cardId) {
        System.out.println(cardId);
    }

    public static void showAllCardsInCollection() {
        showHeroesInCollection();
        showItemsInCollection();
        showCardsInCollection();
    }

    public static void showDeck(Deck deck) {
        //Todo : Bug
        System.out.println("Heroes : ");
        System.out.println("    1 : Name " + deck.getHeroCard().getName() +
                " - Ap : " + ((Hero) deck.getHeroCard()).getAttackPower() +
                " - Hp : " + ((Hero) deck.getHeroCard()).getFullHealth() +
                " - class : " + ((Hero) deck.getHeroCard()).getName() +
                " - spesial power : " + ((Hero) deck.getHeroCard()).getDescription() +
                " - sell Cost: " + ((Hero) deck.getHeroCard()).getPriceInDrake());

        int counter = 0;
        for (Card card : deck.getDeckCards()) {
            counter++;
            showCard(card, counter);
        }

    }

    private static void showCard(Card card, int counter) {
        if (card instanceof Minion) {
            System.out.println("    " + counter + " : Type : Minion - Name : " + card.getName() + "\tid : " + card.getId() +
                    " - Class " + ((Minion) card).getAttackType() +
                    " - Ap : " + ((Minion) card).getAttackPower() +
                    " - Hp : " + ((Minion) card).getFullHealth() +
                    " - Mp : " + card.getManaPoint() +
                    " - Special power : " + card.getDescription());
        }
        if (card instanceof Spell) {
            System.out.println("    " + counter + " : Type Spell - Name : " + card.getName() +
                    " - Mp " + ((Spell) card).getManaPoint() +
                    " - Desc : "/*+((Spell) card).getSpellType().get*/);

        }
    }

    public static void printDeckValidation(boolean isValidate) {
        if (isValidate)
            System.out.println("Deck Is Valid");
        else
            System.out.println("Deck Is Not Valid");
    }

    private static void showHeroesInCollection() {
        System.out.println("Heroes :");
        for (int i = 0; i < Account.getLoggedInAccount().getCollection().getCards().size(); i++) {
            if (Account.getLoggedInAccount().getCollection().getCards().get(i) instanceof Hero) {
                System.out.println((i + 1) + "- Name : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getName() +
                        "- AP : " + ((Hero) Account.getLoggedInAccount().getCollection().getCards().get(i)).getAttackPower() +
                        "- HP : " + ((Hero) Account.getLoggedInAccount().getCollection().getCards().get(i)).getFullHealth() +
                        "- Desc : " + Shop.getShopCollection().getCards().get(i).getDescription() +
                        "- Sell cost : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getPriceInDrake());
            }
        }
    }

    private static void showCardsInCollection() {
        System.out.println("Cards :");
        for (int i = 0; i < Account.getLoggedInAccount().getCollection().getCards().size(); i++) {
            if (Account.getLoggedInAccount().getCollection().getCards().get(i) instanceof Minion) {
                System.out.println((i + 1) + "- Type : Minion " +
                        "- Name : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getName() +
                        "- Class : " + ((Minion) Account.getLoggedInAccount().getCollection().getCards().get(i)).getActivationTime() +
                        "- AP : " + ((Minion) Account.getLoggedInAccount().getCollection().getCards().get(i)).getAttackPower() +
                        "- HP : " + ((Minion) Account.getLoggedInAccount().getCollection().getCards().get(i)).getFullHealth() +
                        "- MP : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getManaPoint() +
                        "- Desc : " + Shop.getShopCollection().getCards().get(i).getDescription() +
                        "- Sell cost : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getPriceInDrake());
            } else if (Account.getLoggedInAccount().getCollection().getCards().get(i) instanceof Spell) {
                System.out.println((i + 1) + "- Type : Spell " +
                        "- Name : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getName() +
                        "- MP : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getManaPoint() +
                        "- Desc : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getDescription() +
                        "- Sell cost : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getPriceInDrake());
            }
        }
    }

    private static void showItemsInCollection() {
        System.out.println("Items :");
        for (int i = 0; i < Account.getLoggedInAccount().getCollection().getCards().size(); i++) {
            if (Account.getLoggedInAccount().getCollection().getCards().get(i) instanceof Item) {
                System.out.println((i + 1) +
                        "- Name : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getName() +
                        "- Desc : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getDescription() +
                        "- Sell cost : " + Account.getLoggedInAccount().getCollection().getCards().get(i).getPriceInDrake());
            }
        }
    }

    public static void printHelp() {
        System.out.println("*** Account Commands ***");
        System.out.println("1. exit : obviously to exit!");
        System.out.println("2. how :To showing all card and items");
        System.out.println("3.search :  Show that a card or item is in callection or not");
        System.out.println("4. save : What you think is for");
        System.out.println("5. create deck : Creating a deck and add to collection ");
        System.out.println("6. delete deck :Delete a deck from collection");
        System.out.println("7. add : Adding a card or item to a deck");
        System.out.println("8. remove : Removing a card or item from a deck");
        System.out.println("9. validate deck : Showing a deck is complit or not");
        System.out.println("10. select deck : Select a deck as main deck");
        System.out.println("11. showAllCardsInCollection all decks : To showing content of all decks");
        System.out.println("12 . showAllCardsInCollection deck : To showing content of deck");
    }

}
