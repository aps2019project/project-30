package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.User.Account;
import com.company.Views.Console.CollectionViews;


public class CollectionController {
    public void show() {
        CollectionViews.show();
    }
    public void ShowAllDecks(){
        int counter=0;
        for(Deck deck:Account.getLoggedInAccount().getDecks()){
            counter++;
            CollectionViews.showNumberofDeck(counter);
            CollectionViews.showDeck(deck);
        }
    }
    public void ShowDeck(String deckNmae){
        CollectionViews.showDeck(getDeckByName(deckNmae));
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
            for(Card card:getDeckByName(deckName).getDeckCards()){
                if(card.getId()==cardId){
                    //payamemonaseb
                    return;
                }
            }
        }
        if(getDeckByName(deckName).getDeckCards().size()>=20){
            //payammonaseb
        }
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()) {
            if (cardId == card.getId()) {
                if (card instanceof Hero) {
                    if(getDeckByName(deckName).getHeroCard()!=null){
                        //payamemonaseb
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
            Account.getLoggedInAccount().setMainDeck(getDeckByName(deckName));
           return;
        }
        //payame monaseb
    }
    public boolean validateDeck(String deckName){
        Deck deck;
        if(deckExist(deckName)) {
            if(getDeckByName(deckName).getDeckCards().size()==20&&getDeckByName(deckName).getHeroCard()!=null){
                return true;
            }
            return false;
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
            Account.getLoggedInAccount().getDecks().remove(getDeckByName(deckName));
        }
        //payamemonaseb
    }
    private Deck getDeckByName(String deckName){
            for (Deck deck : Account.getLoggedInAccount().getDecks()) {
                if (deck.getName().equals(deckName)) {
                    return deck;
                }
            }
        return null;
    }
}
