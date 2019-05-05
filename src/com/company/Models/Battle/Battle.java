package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Models.Battle.Map.Map;
import com.company.Views.BattleView;

import java.util.List;


public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private Map map = new Map();
    private BattleType battleType;
    private Player[] players = new Player[2];
    private Player turnToPlay;
    private BattleController battleController;
    private List<Flag> flags;
    private BattleView battleView;
    private static int lastBattleCardId = 0;
    private int winningPrize;

    public Battle(Mode mode, BattleType battleType) {
        this.mode = mode;
        this.battleType = battleType;
        playingBattle = this;
        this.map = new Map();
        initHeroes();
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
        map.getCellByCoordinates(2, 4).setCardInCell(Account.getLoggedInAccount().getMainDeck().getHeroCard());
//        initHeroes();
        System.out.println(map.toString());
    }

    public Map getMap() {
        return map;
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

    public List<Flag> getFlags() {
        return flags;
    }

    public void addToFlags(Flag flag) {
        this.flags.add(flag);
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


    private void initHeroes() {
        map.getCellByCoordinates(8, 2).setCardInCell(players[1].getAccount().getMainDeck().getHeroCard());
        map.getCellByCoordinates(0, 2).setCardInCell(players[0].getAccount().getMainDeck().getHeroCard());
        Player botPlayer = new Player();
        botPlayer.setMana(2);
        botPlayer.setMaxMana(2);


    }

}
