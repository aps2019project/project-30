package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = ArrayList<>();
    private Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public boolean isComplete() {
        return false;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
