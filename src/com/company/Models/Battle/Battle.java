package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Player;
import com.company.Models.Battle.Map.Map;


public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private Map map;
    private BattleType battleType;
    private Player[] players;
    private Player turnToPlay;
    private BattleController battleController;
    private int winningPrize;

    public Battle(Mode mode, BattleType battleType) {
        this.mode = mode;
        this.battleType = battleType;
        playingBattle = this;
        this.map = new Map();
    }

    public Map getMap() {
        return map;
    }

    public Battle(int storyLevel) {
        this.battleType = BattleType.STORY;
        switch (storyLevel) {
            case 1 :
                this.mode = Mode.KILLING_GENERAL;
                break;
            case 2 :
                this.mode = Mode.CAPTURE_THE_FLAG;
                break;
            case 3 :
                this.mode = Mode.COLLECTING_FLAGS;
                break;
        }
        this.winningPrize = 500 * storyLevel;
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
