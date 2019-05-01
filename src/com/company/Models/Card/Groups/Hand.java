package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    public static final int HAND_CARDS_MAX = 5;
    private Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }
}
