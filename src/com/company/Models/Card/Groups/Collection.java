package com.company.Models.Card.Groups;

import com.company.Controllers.CollectionController;
import com.company.Models.Card.Card;
import com.company.Models.User.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Collection implements Serializable {
    private CollectionController collectionController = new CollectionController();
    private ArrayList<Card> cards = new ArrayList<>();
    //private HashMap<String,Integer> cardsMap=new HashMap<String, Integer>();


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
