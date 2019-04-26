package com.company.Models.User;

import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Spell.Spell;

public class Player {
    private Account account;
    private Deck deck;
    private int score;
    private int mana;
    private int usedMana;
    private Spell spell;

    public Player(Account account) { }

    public Player() { }

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
}
