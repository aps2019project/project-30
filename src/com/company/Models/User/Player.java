package com.company.Models.User;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.GraveYard;
import com.company.Models.Card.Spell.Spell;

import java.util.ArrayList;

public class Player {
    private Account account;
    private GraveYard graveYard;
    private Deck deck;
    private int score;
    private int mana;
    private int usedMana;
    private Spell spell;
    private Card selectedCard;
    private ArrayList<Card> cards;

    public Player(Account account) {
        this.account = account;
        this.graveYard = new GraveYard();
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

    public int getUsedMana() {
        return usedMana;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addNewCardToCards(Card card){
        cards.add(card);
    }
}
