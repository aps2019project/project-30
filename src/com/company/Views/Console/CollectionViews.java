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
                System.out.println("    1 : Name "+card.getName()+" - Ap : "+((Hero) card).getHeroType().getAttackPower()+" - Hp : "+((Hero) card).getHealth()+
                        " - class : "+((Hero) card).getHeroType().getName()+" - spesial power : "+((Hero) card).getHeroType().getSpesioalPower()+" - sell Cost: "+((Hero) card).getHeroType().getPriceInDrake());
            }

        }
        System.out.println("Item : ");
        for(Card card:Account.getLoggedInAccount().getCollection().getCards()){
            if(card instanceof ){
                System.out.println("    2 : Name "+);
            }
        }
        System.out.println("Cards : ");
        for (Card card:Account.getLoggedInAccount().getCollection().getCards()){

        }
    }
}
