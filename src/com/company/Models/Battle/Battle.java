package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.User.Player;
import com.company.Models.Battle.Map.Map;
import com.company.Views.BattleView;

import java.util.List;


public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private Map map;
    private BattleType battleType;
    private Player[] players;
    private Player turnToPlay;
    private BattleController battleController;
    private BattleView battleView;
    private static int lastBattleCardId = 0;
    private int winningPrize;

    public Battle(Mode mode, BattleType battleType) {
        this.mode = mode;
        this.battleType = battleType;
        playingBattle = this;
        this.map = new Map();
        map.getCellByCoordinates(0, 2).setCardInCell(players[0].getAccount().getMainDeck().getHeroCard());
        map.getCellByCoordinates(8, 2).setCardInCell(players[1].getAccount().getMainDeck().getHeroCard());
    }

    public Map getMap() {
        return map;
    }

    public Battle(int storyLevel) {
        this.battleType = BattleType.STORY;
        switch (storyLevel) {
            case 1:
                this.mode = Mode.KILLING_GENERAL;
                break;
            case 2:
                this.mode = Mode.CAPTURE_THE_FLAG;
                break;
            case 3:
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

    public BattleView getBattleView() {
        return battleView;
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

    public static int getLastBattleCardId() {
        return lastBattleCardId;
    }

    public static void incrementlastBattleCardId(int number){
        lastBattleCardId += number;
    }

}
