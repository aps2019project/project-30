package com.company.Models.User;

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
    private String name;
    private Deck deck = new Deck("Bot Deck");
    private int score = 0;
    private int mana = 2;
    private int maxMana = 2;
    private Spell spell;
    private Card selectedCard;
    private ArrayList<Card> usedCardsToAttack = new ArrayList<>();
    private ArrayList<Card> usedCardsToMove = new ArrayList<>();
    private ArrayList<Card> usedCards = new ArrayList<>();

    public Player(Account account) {
        this.account = account;
        this.name = account.getUsername();
        this.deck = account.getMainDeck();
    }

    public Player() {

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

    public ArrayList<Card> getAliveCards() {
        ArrayList<Card> aliveCards = new ArrayList<>();
        for (Card card : getUsedCards()) {
            if (!card.isInGraveCards()) {
                aliveCards.add(card);
            }
        }
        return aliveCards;
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

    public void decrementMana(int number) {
        mana -= number;
    }

    public void incrementMana(int number) {
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

    public ArrayList<Card> getUsedCardsToAttack() {
        return usedCardsToAttack;
    }

    public ArrayList<Card> getUsedCards() {
        return usedCards;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public ArrayList<Card> getUsedCardsToMove() {
        return usedCardsToMove;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public void setUsedCards(ArrayList<Card> usedCards) {
        this.usedCards = usedCards;
    }
}
