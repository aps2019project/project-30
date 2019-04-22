package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Hero.HeroType;
import com.company.Models.Card.Item.Item;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.CollectionViews;
import com.company.Views.ConsoleOutput;


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
                CollectionViews.showCardId(card.getId());
            }
        }
    }

    public void addCard(int cardId,String deckName){
        if(cardExist(cardId)==false) {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
            return;
        }
        if(deckExist(deckName)){
            for(Card card:getDeckByName(deckName).getDeckCards()){
                if(card.getId()==cardId){
                    ConsoleOutput.printErrorMessage(ErrorType.CARD_EXISTS);
                    return;
                }
            }
        }
        if(getDeckByName(deckName).getDeckCards().size()>=20){
            ConsoleOutput.printErrorMessage(ErrorType.DECK_FULL);
            return;
        }
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()) {
            if (cardId == card.getId()) {
                if (card instanceof Hero) {
                    if(getDeckByName(deckName).getHeroCard()!=null){
                        ConsoleOutput.printErrorMessage(ErrorType.HERO_EXISTS);
                        return;
                    }
                    else {
                        getDeckByName(deckName).setHeroCard(card);
                        return;
                    }
                }
                else if (card instanceof Item) {
                    if(getDeckByName(deckName).getItemCard()!=null){
                        ConsoleOutput.printErrorMessage(ErrorType.ITEM_EXISTS);
                        return;
                    }
                    else {
                        getDeckByName(deckName).setItemCard(card);
                        return;
                    }
                }
                else{
                    getDeckByName(deckName).getDeckCards().add(card);
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
        if(deckExist(deckName)&&validateDeck(deckName)){
            Account.getLoggedInAccount().setMainDeck(getDeckByName(deckName));
           return;
        }
        ConsoleOutput.printErrorMessage(ErrorType.DECK_VALIDATE);
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
    public void createDeck(String deckName){
        if(deckExist(deckName)){
            ConsoleOutput.printErrorMessage(ErrorType.DECK_EXISTS);
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
    public void remove(int cardId,String deckName){
        for(Card card:getDeckByName(deckName).getDeckCards()){
            if(cardId==card.getId()){
                getDeckByName(deckName).getDeckCards().remove(card);
                return;
            }
        }
        ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUNDINDECK);
    }
}
