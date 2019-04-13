package com.company.Models.User;

import com.company.Models.Battle.BattleLog;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Battle.Battle;

import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private String username;
    private String password;
    private boolean isLoggedIn = false;
    private int drake = 0;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck;
    private Collection collection;
    private ArrayList<BattleLog> battleHistories = new ArrayList<>();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void addToAccounts(Account account) {
        accounts.add(account);
    }

    public String getUsername() {
        return username;
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

    public Collection getCollection() {
        return collection;
    }

    public ArrayList<BattleLog> getBattleHistories() {
        return battleHistories;
    }

    public void setDrake(int drake) {
        this.drake = drake;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
