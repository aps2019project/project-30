package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

public class Deck {
    private String name;
    private Card[] deckCards = new Card[20];
    private Card heroCard;
    private Card itemCard;
    private Hand hand;

    public Deck(String name) {
        this.name = name;
        hand = new Hand(this);
    }

    public Card[] getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Card[] deckCards) {
        this.deckCards = deckCards;
    }

    public Card getHeroCard() {
        return heroCard;
    }

    public void setHeroCard(Card heroCard) {
        this.heroCard = heroCard;
    }

    public Card getItemCard() {
        return itemCard;
    }

    public void setItemCard(Card itemCard) {
        this.itemCard = itemCard;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void addRandomCardToHand() {

    }

    public void initializeHand() {

    }

    public void generateRandomCardsOrder() {}

    public Card getIncomingCardToHand() {return null;}

    public boolean isValidate() {
        return true;
    }
}
