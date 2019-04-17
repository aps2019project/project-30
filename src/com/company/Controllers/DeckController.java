package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;

import java.util.ArrayList;
import java.util.Calendar;

public class DeckController {
    private Deck deck;

    public DeckController(Deck deck) {
        this.deck = deck;
        generateRandomCardsOrder();
    }

    public void addRandomCardToHand() {
        ArrayList<Card> cards = deck.getDeckCards();

    }

    public void initializeHand() {

    }

    public void generateRandomCardsOrder() {

    }

    public void getAliveCards() {
        ArrayList<Card> aliveCards = new ArrayList<>();
        for (Card card : deck.getDeckCards()) {
            if (card.isInGraveCards()) {

            }
        }
    }
}
