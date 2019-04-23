package com.company.Views.Console;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.User.Account;

public class CollectionViews {
    public void showCardId(int cardId){
        System.out.println(cardId);
    }
    public static void show(){
        System.out.println("Heroes : ");
        for(Card card: Account.getLoggedInAccount().getCollection().getCards()){
            if(card instanceof Hero){
//                System.out.println("    1 : Name "+card.getName()+" - Ap : "+((Hero) card).getHeroType().getAttackPower()+" - Hp : "+((Hero) card).getHealth()+
//                        " - class : "+((Hero) card).getHeroType().getName()+" - spesial power : "+((Hero) card).getHeroType().getSpesioalPower()+" - sell Cost: "+((Hero) card).getHeroType().getPriceInDrake());
                break;
            }

        }
        System.out.println("Cards : ");
        int counter=0;
        for (Card card:Account.getLoggedInAccount().getCollection().getCards()){
            counter++;
            showCard(card,counter);
        }
    }
    public static void showDeck(Deck deck){
        System.out.println("Heroes : ");
//        System.out.println("    1 : Name "+deck.getHeroCard().getName()+" - Ap : "+((Hero) deck.getHeroCard()).getHeroType().getAttackPower()+" - Hp : "+((Hero) deck.getHeroCard()).getHealth()+
//                " - class : "+((Hero) deck.getHeroCard()).getHeroType().getName()+" - spesial power : "+((Hero)deck.getHeroCard()).getHeroType().getSpesioalPower()+" - sell Cost: "+((Hero) deck.getHeroCard()).getHeroType().getPriceInDrake());

        int counter=0;
        for(Card card:deck.getDeckCards()){
            counter++;
            showCard(card,counter);
        }

    }
    private static void showCard(Card card,int counter){
        if(card instanceof Minion){
//            System.out.println("    "+counter+" : Type : Minion - Name : "+card.getName()+" - Class "+((Minion) card).getMinionType().getClass()+" - Ap : "+((Minion) card).getMinionType().getAttackPower()+
//                    " - Hp : "+((Minion) card).getMinionType()+" - Mp : "+card.getManaPoint()+" - Special power : "+((Minion) card).getMinionType().getSpecialPower);
        }
        if(card instanceof Spell){
//            System.out.println("    "+counter+" : Type Spell - Name : "+card.getName()+" - Mp "+((Spell) card).getManaPoint()+" - Desc : "+((Spell) card).getSpellType().get);

        }
    }
}
