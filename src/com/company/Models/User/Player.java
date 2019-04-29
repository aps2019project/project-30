package com.company.Models.User;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.GraveYard;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Item> items = new ArrayList<>();
    private Account account;
    private GraveYard graveYard;
    private Deck deck;
    private int score;
    private int mana = 2;
    private int maxMana;
    private Spell spell;
    private Card selectedCard;
    private ArrayList<Card> cards;

    public Player(Account account) {
        this.account = account;
        this.graveYard = new GraveYard();
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void addMaxMana() {
        if (maxMana < 9) {
            maxMana += 1;
        }
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Player() {
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void incremeantScore(int number) {
        this.score += number;
    }

    public void incremeantMana(int number) {
        mana += number;
    }

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

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addNewCardToCards(Card card) {
        cards.add(card);
    }

}
