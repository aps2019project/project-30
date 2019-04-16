package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private String name;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private Card heroCard;
    private Card itemCard;
    private Hand hand;

    public Deck(String name) {
        this.name = name;
        hand = new Hand(this);
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
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
        if (!hand.isComplete()) {
            for (Card card: deckCards) {

            }
        }
    }

    public void initializeHand() {

    }
    public String getName(){
        return name;
    }

    public void generateRandomCardsOrder() {}

    public Card getIncomingCardToHand() {return null;}

    public boolean isValidate() {
        return true;
    }
}
