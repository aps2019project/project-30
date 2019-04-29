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
                        " - Ap : " + ((Hero) card).getAttackPower() +
                        " - Hp : " + ((Hero) card).getHealth() +
                        " - class : " + ((Hero) card).getName() +
                        " - spesial power : " + ((Hero) card).getDescription() +
                        " - sell Cost: " + ((Hero) card).getPriceInDrake());
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
                " - Ap : " + ((Hero) deck.getHeroCard()).getAttackPower() +
                " - Hp : " + ((Hero) deck.getHeroCard()).getHealth() +
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
            System.out.println("    " + counter + " : Type : Minion - Name : " + card.getName() +
                    " - Class " + ((Minion) card).getAttackType() +
                    " - Ap : " + ((Minion) card).getAttackPower() +
                    " - Hp : " + ((Minion) card).getHealth() +
                    " - Mp : " + card.getManaPoint() +
                    " - Special power : "+ card.getDescription());
        }
        if (card instanceof Spell) {
            System.out.println("    " + counter + " : Type Spell - Name : " + card.getName() +
                    " - Mp " + ((Spell) card).getManaPoint() +
                    " - Desc : "/*+((Spell) card).getSpellType().get*/);

        }
    }

}
