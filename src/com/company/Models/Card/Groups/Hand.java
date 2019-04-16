package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

public class Hand {
    private Card[] cards = new Card[5];
    private Deck deck;

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public boolean isComplete() {
        return false;
    }

    public void addToCards(Card card) {

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
