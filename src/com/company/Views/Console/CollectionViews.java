package com.company.Views.Console;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.User.Account;

public class CollectionViews {
    public static void showNumberofDeck(int n) {
        System.out.println(n + " : deck_" + n);
    }

    public static void showCardId(int cardId) {
        System.out.println(cardId);
    }

    public static void show() {
        System.out.println("Heroes : ");
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            if (card instanceof Hero) {
                System.out.println("    1 : Name " + card.getName() +
                        " - Ap : " + ((Hero) card).getHeroType().getAttackPower() +
                        " - Hp : " + ((Hero) card).getHealth() +
                        " - class : " + ((Hero) card).getHeroType().getName() +
                        " - spesial power : " + ((Hero) card).getHeroType().getSpesioalPower() +
                        " - sell Cost: " + ((Hero) card).getHeroType().getPriceInDrake());
                break;
            }

        }
        System.out.println("Cards : ");
        int counter = 0;
        for (Card card : Account.getLoggedInAccount().getCollection().getCards()) {
            counter++;
            showCard(card, counter);
        }
    }

    public static void showDeck(Deck deck) {
        System.out.println("Heroes : ");
        System.out.println("    1 : Name " + deck.getHeroCard().getName() +
                " - Ap : " + ((Hero) deck.getHeroCard()).getHeroType().getAttackPower() +
                " - Hp : " + ((Hero) deck.getHeroCard()).getHealth() +
                " - class : " + ((Hero) deck.getHeroCard()).getHeroType().getName() +
                " - spesial power : " + ((Hero) deck.getHeroCard()).getHeroType().getSpesioalPower() +
                " - sell Cost: " + ((Hero) deck.getHeroCard()).getHeroType().getPriceInDrake());

        int counter = 0;
        for (Card card : deck.getDeckCards()) {
            counter++;
            showCard(card, counter);
        }

    }

    private static void showCard(Card card, int counter) {
        if (card instanceof Minion) {
            System.out.println("    " + counter + " : Type : Minion - Name : " + card.getName() +
                    " - Class " + ((Minion) card).getMinionType().getClass() +
                    " - Ap : " + ((Minion) card).getMinionType().getAttackPower() +
                    " - Hp : " + ((Minion) card).getMinionType() +
                    " - Mp : " + card.getManaPoint() +
                    " - Special power : "/*+((Minion) card).getMinionType().getSpecialPower*/);
        }
        if (card instanceof Spell) {
            System.out.println("    " + counter + " : Type Spell - Name : " + card.getName() +
                    " - Mp " + ((Spell) card).getManaPoint() +
                    " - Desc : "/*+((Spell) card).getSpellType().get*/);

        }
    }
    public static void printHelp(){
        System.out.println("*** Account Commands ***");
        System.out.println("1. exit :");
        System.out.println("2. show :to showing all card and items");
        System.out.println("3.search :  show that a card or item is in callection or not");
        System.out.println("4. save : ");
        System.out.println("5. create deck : creating a deck and add to collection ");
        System.out.println("6. delete deck :delet a deck from collection");
        System.out.println("7. add : adding a card or item to a deck");
        System.out.println("8. remove : removing a card or item from a deck");
        System.out.println("9. validate deck : showing a deck is complit or not");
        System.out.println("10. select deck : select a deck as main deck");
        System.out.println("11. show all decks : to showing content of all decks");
        System.out.println("12 . show deck : to showing content of deck");
    }

}
