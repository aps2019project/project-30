package com.company.Models.Battle;

import com.company.Models.User.Player;

public class BattleLog {
    private String player1Username;
    private String player2Username;
    private String winnerPlayerUsername;
    private int battleTime;

    public BattleLog(String player1Username, String player2Username, String winnerPlayerUsername, int battleTime) {
        this.player1Username = player1Username;
        this.player2Username = player2Username;
        this.winnerPlayerUsername = winnerPlayerUsername;
        this.battleTime = battleTime;
    }

    public String getPlayer1Username() {
        return player1Username;
    }

    public void setPlayer1Username(String player1Username) {
        this.player1Username = player1Username;
    }

    public String getPlayer2Username() {
        return player2Username;
    }

    public void setPlayer2Username(String player2Username) {
        this.player2Username = player2Username;
    }

    public String getWinnerPlayerUsername() {
        return winnerPlayerUsername;
    }

    public void setWinnerPlayerUsername(String winnerPlayerUsername) {
        this.winnerPlayerUsername = winnerPlayerUsername;
    }

    public int getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(int battleTime) {
        this.battleTime = battleTime;
    }
}
