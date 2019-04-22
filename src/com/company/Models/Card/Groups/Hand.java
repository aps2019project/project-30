package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();
    public static final int HAND_CARDS_MAX = 5;
    private Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
