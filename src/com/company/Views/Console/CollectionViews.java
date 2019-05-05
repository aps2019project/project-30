package com.company.Views.Console;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionViews {

    public static void showCardId(String cardId) {
        System.out.println(cardId);
    }

    public static void showAllCardsInCollection() {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            ConsoleOutput.showAllCards(Account.getLoggedInAccount().getCollection().getCards());
        }
    }

    public static void showAllDecks() {
        for(int i =0;i<Account.getLoggedInAccount().getDecks().size();i++){
            System.out.println(i + "-->");
            showDeck(Account.getLoggedInAccount().getDecks().get(i));
        }
    }

    public static void showDeck(Deck deck){
        System.out.println("deck name: " + deck.getName());
        ConsoleOutput.showAllCards(deck.getDeckCards());
    }

    public static void printDeckValidation(boolean isValidate) {
        if (isValidate)
            System.out.println("Deck Is Valid");
        else
            System.out.println("Deck Is Not Valid");
    }

    public static void printShopCommandsToHelp() {
        System.out.println("##### Account Commands #####");
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
        System.out.println("11. show all decks : To showing content of all decks");
        System.out.println("12 . show : To showing content of deck");
    }

}
