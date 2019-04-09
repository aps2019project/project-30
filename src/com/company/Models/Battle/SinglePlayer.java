package com.company.Models.Battle;

import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Account;
import com.company.Models.User.Player;

public class SinglePlayer extends Battle {
    private Account player1Acc;
    private Player player1;
    private Mode mode;
    private boolean isStory;

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
