package com.company.Models.Card.Groups;

import com.company.Models.Card.Card;

import java.util.ArrayList;

public class GraveYard {
    private ArrayList<Card> destroyedCards = new ArrayList<>();

    private void addToCards(Card card) {
        destroyedCards.add(card);
    }


    private boolean containsCard(Card card) {
        return false;
    }

    public ArrayList<Card> getDestroyedCards() {
        return destroyedCards;
    }
}
