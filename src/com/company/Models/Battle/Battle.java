package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Player;

public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private Player[] players;
    private Player turnToPlay;
    private BattleController battleController;

    public Battle(Mode mode) {
        this.mode = mode;
        this.battleController = new BattleController(this);
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
}
