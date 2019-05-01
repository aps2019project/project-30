package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckController {
    private Deck deck;

    public DeckController(Deck deck) {
        this.deck = deck;
        generateRandomCardsOrder();
//        initializeHand();
    }

    public void addRandomCardToHand() {
        Card card = getAliveCards().get(0);
        deck.getHand().getCards().add(card);
    }

    public void initializeHand() {
        for (int i = 0; i < 5; i++) {
            deck.getHand().getCards().add(
              deck.getDeckCards().get(i)
            );
        }
    }

    public void generateRandomCardsOrder() {
        Collections.shuffle(deck.getDeckCards());
    }

    public ArrayList<Card> getAliveCards() {
        ArrayList<Card> aliveCards = new ArrayList<>();
        for (Card card : deck.getDeckCards()) {
            if (!card.isInGraveCards()) {
                aliveCards.add(card);
            }
        }
        return aliveCards;
    }

    private boolean isHandFull() {
        return deck.getHand().getCards().size() == Hand.HAND_CARDS_MAX;
    }
}
