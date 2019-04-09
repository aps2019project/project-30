package com.company;

public class Player {
    private String username;
    private int score;
    private Deck deck;
    private int mana;
    private int usedMana;

    public Player(Account account) {
    }

    public Player() {
        //Bot
    }

    public void incremeantScore(int number) {

    }

    public void incremeantUsedMana(int number) {

    }

    public void incremeantMana() {

    }

    public String getUsername() {
        return username;
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
