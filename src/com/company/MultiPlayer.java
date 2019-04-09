package com.company;

public class MultiPlayer extends Game {
    private Account player1Acc;
    private Account player2Acc;
    private Player player1;
    private Player player2;
    private Mode mode;

    public Account getPlayer1Acc() {
        return player1Acc;
    }

    public void setPlayer1Acc(Account player1Acc) {
        this.player1Acc = player1Acc;
    }

    public Account getPlayer2Acc() {
        return player2Acc;
    }

    public void setPlayer2Acc(Account player2Acc) {
        this.player2Acc = player2Acc;
    }

    @Override
    public void handler() {

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
