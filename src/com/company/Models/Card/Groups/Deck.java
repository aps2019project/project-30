package com.company.Models.Card.Groups;

import com.company.Controllers.DeckController;
import com.company.Models.Card.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements Serializable {
    private String name;
    private List<Card> deckCards = new ArrayList<>();
    private Card heroCard;
    private Card itemCard;
    private transient Hand hand;
    private transient DeckController deckController = new DeckController(this);
    public Deck(String name) {
        this.name = name;
        setHand();
    }

    public List<Card> getDeckCards() {
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

    public boolean isValidate() {
        return true;
    }

    public String getName() {
        return name;
    }

    public DeckController getDeckController() {
        return deckController;
    }

    public void setDeckController() {
        this.deckController = new DeckController(this);
    }

    public void setHand(){
        this.hand = new Hand(this);
    }
}
