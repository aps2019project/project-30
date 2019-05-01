package com.company.Models.Card.Groups;

import com.company.Controllers.CollectionController;
import com.company.Models.Card.Card;
import com.company.Models.User.Account;

import java.util.ArrayList;

public class Collection {
    private CollectionController collectionController = new CollectionController();
    private ArrayList<Card> cards = new ArrayList<>();


    public void setCollectionController(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    public CollectionController getCollectionController() {
        return collectionController;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static Deck getDeckByName(String deckName){
        for (Deck deck: Account.getLoggedInAccount().getDecks()) {
            if(deck.getName().equals(deckName))
                return deck;
        }
        return null;
    }

}
