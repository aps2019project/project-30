package com.company.Views.Console;

import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.User.Account;

public class CollectionViews {
    public void showCardId(int cardId){
        System.out.println(cardId);
    }
    public static void show(){
        System.out.println("Heroes : ");
        for(Card card: Account.getLoggedInAccount().getCollection().getCards()){
            if(card instanceof Hero){

            }

        }
    }
}
