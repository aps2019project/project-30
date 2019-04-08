package com.company;

import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private String username;
    private String password;
    private boolean isLoggedIn = false;
    private int drake = 0;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck;
    private GCollection gCollection;
    private ArrayList<Game> gameHistories = new ArrayList<>();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void login(String username, String password) {

    }

    public void logout() {

    }

    public void addToDecks(Deck deck) {

    }

    public int getDrake() {
        return drake;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public GCollection getgCollection() {
        return gCollection;
    }

    public ArrayList<Game> getGameHistories() {
        return gameHistories;
    }

    public void setDrake(int drake) {
        this.drake = drake;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
