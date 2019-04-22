package com.company.Models.Card.Groups;

import com.company.Controllers.CollectionController;
import com.company.Models.Card.Card;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Card> cards;
    private CollectionController collectionController;

    public CollectionController getCollectionController() {
        return collectionController;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

}
