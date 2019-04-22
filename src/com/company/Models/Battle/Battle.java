package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Player;

public abstract class Battle {
    private Mode mode;
    private Player turnToPlay;
    private BattleController battleController;

    public Battle(Mode mode) {
        this.mode = mode;
        this.battleController = new BattleController(this);
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

    public abstract void handler();
}
