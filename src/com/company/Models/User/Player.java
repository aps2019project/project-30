package com.company.Models.User;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.GraveYard;
import com.company.Models.Card.Spell.Spell;

public class Player {
    private Account account;
    private GraveYard graveYard;
    private Deck deck;
    private int score;
    private int mana=2;
   // private int usedMana;
    private int maxMana;
    private Spell spell;
    private Card selectedCard;

    public Player(Account account) {
        this.account = account;
        this.graveYard = new GraveYard();
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
    public void addMaxMana(){
        if(maxMana<9) {
            maxMana += 1;
        }
    }
    public void setMana(int mana){
        this.mana=mana;
    }

    public Player() { }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public void incremeantScore(int number) { }

    public void incremeantUsedMana(int number) { }

    public void incremeantMana() { }

    public Account getAccount() {
        return account;
    }

    public int getScore() {
        return score;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getMana() {
        return mana;
    }


    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
