package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

public class Hand {
    Card[] cards = new Card[5];
    Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
    }

    private boolean isComplete() {
        return false;
    }

    private void addToCards(Card card) {

    }

    private void removeFromCard(Card card) {

    }

    public Deck getDeck() {
        return deck;
    }

    public Card[] getCards() {
        return cards;
    }
}
