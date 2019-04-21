package com.company.Models.Battle;

import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Account;
import com.company.Models.User.Player;

public class MultiPlayer extends Battle {
    private Account player1Acc;
    private Account player2Acc;
    private Player player1;
    private Player player2;
    private Mode mode;

    public MultiPlayer(Mode mode) {
        super(mode);
    }

    public Account getPlayer1Acc() {
        return player1Acc;
    }

    public Account getPlayer2Acc() {
        return player2Acc;
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
