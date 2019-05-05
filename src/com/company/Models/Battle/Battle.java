package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Soldier;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Models.Battle.Map.Map;
import com.company.Views.BattleView;

import java.util.ArrayList;
import java.util.List;


public class Battle {
    private static Battle playingBattle;
    private Mode mode;
    private Map map = new Map();
    private BattleType battleType;
    private Player[] players = new Player[2];
    private Player turnToPlay;
    private BattleController battleController = new BattleController(this);
    private List<Flag> flags = new ArrayList<>();
    private BattleView battleView = new BattleView(this);
    private static int lastBattleCardId = 0;
    private int winningPrize;

    public Battle(Mode mode, Account opponent) {
        players[0] = new Player(Account.getLoggedInAccount());
        players[1] = new Player(opponent);
        players[0].getDeck().getDeckController().initializeHand();
        players[1].getDeck().getDeckController().initializeHand();
        this.turnToPlay = players[0];
        this.mode = mode;
        this.battleType = battleType;
        playingBattle = this;
        this.winningPrize = 1000;
        this.map = new Map();
        initHeroes();
    }

    public Battle(int storyLevel) {
        players[0] = new Player(Account.getLoggedInAccount());
        players[1] = new Player(Account.getLoggedInAccount());
        this.turnToPlay = players[0];
        this.battleType = BattleType.STORY;
        switch (storyLevel) {
            case 1:
                this.mode = Mode.KILLING_GENERAL;
                break;
            case 2:
                this.mode = Mode.CAPTURE_THE_FLAG;
                Flag flag = new Flag(map.getCellByCoordinates(5, 2));
                map.getCellByCoordinates(5, 2).setFlag(flag);
                flags.add(flag);
                break;
            case 3:
                this.mode = Mode.COLLECTING_FLAGS;
                Flag flag1 = new Flag(map.getCellByCoordinates(5, 1));
                map.getCellByCoordinates(5, 1).setFlag(flag1);
                flags.add(flag1);
                Flag flag2 = new Flag(map.getCellByCoordinates(5, 1));
                map.getCellByCoordinates(5, 1).setFlag(flag2);
                flags.add(flag2);
                break;
        }
        this.winningPrize = 500 * storyLevel;
        playingBattle = this;
//        map.getCellByCoordinates(2, 4).setCardInCell(Account.getLoggedInAccount().getMainDeck().getHeroCard());
        initHeroes();
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
        map.getCellByCoordinates(9, 2).setCardInCell(players[1].getAccount().getMainDeck().getHeroCard());
        map.getCellByCoordinates(1, 2).setCardInCell(players[0].getAccount().getMainDeck().getHeroCard());
        ((Soldier)players[0].getAccount().getMainDeck().getHeroCard()).setCell(map.getCellByCoordinates(9, 2));
        ((Soldier)players[0].getAccount().getMainDeck().getHeroCard()).setCell(map.getCellByCoordinates(1, 2));
        Player botPlayer = new Player();
        botPlayer.setMana(2);
        botPlayer.setMaxMana(2);
    }
}
