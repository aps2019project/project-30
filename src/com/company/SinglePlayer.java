package com.company;

public class SinglePlayer extends Game {
    private Account player1Acc;
    private Player player1;
    private Mode mode;
    private boolean isStory;
    //story saving

    public Account getPlayer1Acc() {
        return player1Acc;
    }

    public void setPlayer1Acc(Account playerAcc) {
        this.player1Acc = playerAcc;
    }

    @Override
    public void handler() {

    }

    public Player getPlayer1() {
        return player1;
    }
}
