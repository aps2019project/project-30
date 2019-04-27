package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Player;

public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private BattleType battleType;
    private Player[] players;
    private Player turnToPlay;
    private BattleController battleController;
    private int winningPrize;

    public Battle(Mode mode, BattleType battleType) {
        this.mode = mode;
        this.battleType = battleType;
        playingBattle = this;
    }

    public Mode getMode() {
        return mode;
    }

    public Player getTurnToPlay() {
        return turnToPlay;
    }

    public BattleController getBattleController() {
        return battleController;
    }

    public void setTurnToPlay(Player turnToPlay) {
        this.turnToPlay = turnToPlay;
    }

    public static Battle getPlayingBattle() {
        return playingBattle;
    }

    public Player[] getPlayers() {
        return players;
    }
}
