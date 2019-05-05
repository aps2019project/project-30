package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckController {
    private Deck deck;
    private int cardToAddInHandIndex = 0;

    public DeckController(Deck deck) {
        this.deck = deck;
    }

    public void addRandomCardToHand() {
        Card card = deck.getDeckCards().get(cardToAddInHandIndex);
        deck.getHand().getCards().add(card);
        cardToAddInHandIndex++;
    }

    public void initializeHand() {
        generateRandomCardsOrder();
        for (int i = 0; i < 5; i++) {
            addRandomCardToHand();
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
