package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.User.Account;


public class CollectionController {
    private void show() {

    }
    public void search(String cardName){
        for(Card card: Account.getLoggedInAccount().getCollection().getCards()){
            if(card.getName().equals(cardName)){
                //showCardId(card.getId());
                return;
            }
        }
    }

    public void addCard(int cardId,String deckName){
        if(cardExist(cardId)==false) {
            //payamemonaseb
            return;
        }
        if(deckExist(deckName)){
            for (Deck deck : Account.getLoggedInAccount().getDecks()) {
                if (deck.getName().equals(deckName)) {
                   for(Card card:deck.getDeckCards()){
                       if(card.getId()==cardId){
                           //payamemonaseb
                           return;
                       }
                   }
                }
            }
        }
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()){
            if(cardId==card.getId()){
                if(card instanceof Hero){
                    for (Deck deck : Account.getLoggedInAccount().getDecks()) {
                        if (deck.getName().equals(deckName)) {
                            if(deck.getHeroCard()!=null){
                                //payammonaseb
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean deckExist(String deckName){
        for(Deck deck:Account.getLoggedInAccount().getDecks()){
            if(deck.getName().equals(deckName)){
                return true;
            }
        }
        return false;
    }
    private boolean cardExist(int cardId){
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()){
            if(card.getId()==cardId){
                return true;
            }
        }
        return false;
    }
    public void selectDeck(String deckName){
        if(deckExist(deckName)){
            for(Deck deck:Account.getLoggedInAccount().getDecks()) {
                if (deck.getName().equals(deckName)) {
                    Account.getLoggedInAccount().setMainDeck(deck);
                    return;
                }
            }
        }
        //payame monaseb
    }
    public boolean validateDeck(String deckName){
        Deck deck;
        if(deckExist(deckName)) {
            if(getDeckByName(deckName).getDeckCards().)
        }
        return false;
    }
    public void creatDeck(String deckName){
        if(deckExist(deckName)){
            //payememonaseb
            return;
        }
        Deck deck=new Deck(deckName);
        Account.getLoggedInAccount().getDecks().add(deck);
    }
    public void deleteDeck(String deckName) {
        if(deckExist(deckName)){
            for (Deck deck : Account.getLoggedInAccount().getDecks()) {
                if (deck.getName().equals(deckName)) {
                    Account.getLoggedInAccount().getDecks().remove(deck);
                }
            }
        }
        //payamemonaseb
    }
    private Deck getDeckByName(String deckName){
        Deck deck1;
            for (Deck deck : Account.getLoggedInAccount().getDecks()) {
                if (deck.getName().equals(deckName)) {
                    return deck;
                }
            }
        return null;
    }
}
