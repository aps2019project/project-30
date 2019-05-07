package com.company.Models.Battle;

import com.company.Controllers.BattleController;
import com.company.Controllers.ShopController;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Soldier;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Models.Battle.Map.Map;
import com.company.Views.BattleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int turn = 1;
    private int timePassedInSeconds = 0;
    private boolean botIsActive = false;

    public Battle(Mode mode, Account opponent, int flags) {
        beginTimer();
        this.map = new Map();
        initPlayersHand(new Player(opponent));
        this.turnToPlay = players[0];
        this.mode = mode;
        mode.setBattle(this);
        initFlagMode();
        this.battleType = BattleType.MULTI;
        playingBattle = this;
        this.winningPrize = 1000;
        initHeroes();
        initCardsHealth();
    }

    public Battle(int storyLevel, int flags) {
        this.map = new Map();
        setModeByStoryLevel(storyLevel, flags);
        botIsActive = true;
        beginTimer();
        this.turnToPlay = players[0];
        players[0] = new Player(Account.getLoggedInAccount());
        players[1] = getBotPlayer(mode);
        players[0].getDeck().getDeckController().initializeHand();
        players[1].getDeck().getDeckController().initializeHand();
        this.turnToPlay = players[0];
        this.battleType = BattleType.STORY;
        mode.setBattle(this);
        this.winningPrize = 500 * storyLevel;
        playingBattle = this;
//        initPlayersHand(players[1]);
        initHeroes();
        initCardsHealth();
        System.out.println(map.toString());
    }

    public Battle(Mode mode, int flags) {
        this.map = new Map();
        initFlagMode();
        botIsActive = true;
        beginTimer();
        this.turnToPlay = players[0];
        players[0] = new Player(Account.getLoggedInAccount());
        players[1] = getBotPlayer(mode);
        players[0].getDeck().getDeckController().initializeHand();
        players[1].getDeck().getDeckController().initializeHand();
        this.turnToPlay = players[0];
        this.battleType = BattleType.STORY;
        mode.setBattle(this);
        this.winningPrize = 1500;
        playingBattle = this;
//        initPlayersHand(players[1]);
        initHeroes();
        initCardsHealth();
        System.out.println(map.toString());
    }

    private void setModeByStoryLevel(int storyLevel, int flagsNum) {
        switch (storyLevel) {
            case 1:
                this.mode = Mode.KILLING_GENERAL;
                break;
            case 2:
                this.mode = Mode.CAPTURE_THE_FLAG;
                Flag flag = new Flag(map.getCellByCoordinates(5, 3));
                map.getCellByCoordinates(5, 3).setFlag(flag);
                flags.add(flag);
                break;
            case 3:
                this.mode = Mode.COLLECTING_FLAGS;
                for (int i = 0; i < flagsNum; i++) {
                    this.getBattleController().putFlagsOnMap();
                }
                break;
        }
    }

    private void beginTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePassedInSeconds ++;
            }
        });
        timer.start();
    }

    private void initPlayersHand(Player opponent) {
        players[0] = new Player(Account.getLoggedInAccount());
        players[1] = opponent;
        players[0].getDeck().getDeckController().initializeHand();
        players[1].getDeck().getDeckController().initializeHand();
    }

    private void initFlagMode() {
        switch (this.mode) {
            case KILLING_GENERAL:
                this.mode = Mode.KILLING_GENERAL;
                break;
            case CAPTURE_THE_FLAG:
                this.mode = Mode.CAPTURE_THE_FLAG;
                Flag flag = new Flag(map.getCellByCoordinates(5, 3));
                map.getCellByCoordinates(5, 3).setFlag(flag);
                flags.add(flag);
                break;
            case COLLECTING_FLAGS:
                this.mode = Mode.COLLECTING_FLAGS;
                this.getBattleController().putFlagsOnMap();
                break;
        }
    }

    private void initCardsHealth() {
        for (Player player : players) {
            ((Hero) player.getDeck().getHeroCard()).setHealth(((Hero) player.getDeck().getHeroCard()).getFullHealth());
            for (Card deckCard : player.getDeck().getDeckCards()) {
                if (deckCard instanceof Soldier) {
                    ((Soldier) deckCard).setHealth(((Soldier) deckCard).getFullHealth());
                }
            }
        }
    }

    public boolean isBotIsActive() {
        return botIsActive;
    }

    public void setBotIsActive(boolean botIsActive) {
        this.botIsActive = botIsActive;
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
        map.getCellByCoordinates(9, 2).setCardInCell(players[1].getDeck().getHeroCard());
        map.getCellByCoordinates(1, 2).setCardInCell(players[0].getAccount().getMainDeck().getHeroCard());
        players[0].getUsedCards().add(players[0].getDeck().getHeroCard());
        players[1].getUsedCards().add(players[1].getDeck().getHeroCard());
        ((Soldier)players[1].getDeck().getHeroCard()).setCell(map.getCellByCoordinates(9, 2));
        ((Soldier)players[0].getAccount().getMainDeck().getHeroCard()).setCell(map.getCellByCoordinates(1, 2));
        ((Hero)players[0].getAccount().getMainDeck().getHeroCard()).setRemainingCoolDownByCooldown();
        ((Hero)players[1].getDeck().getHeroCard()).setRemainingCoolDownByCooldown();
    }

    public void incrementTurn() {
        this.turn ++;
    }

    public int getTurn() {
        return turn;
    }

    public int getTimePassedInSeconds() {
        return timePassedInSeconds;
    }

    public static Player getBotPlayer(Mode mode) {
        Player player = new Player();
        player.setName("BOT");
        switch (mode) {
            case KILLING_GENERAL:
                player.getDeck().setHeroCard(ShopController.makeCopyForCreatingNewCardInShop("Simorgh"));
                player.getDeck().setItemCard(ShopController.makeCopyForCreatingNewCardInShop("Taj Danayi"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Kamandar Fars"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Neyzedar Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Gorzdar Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Gorzdar Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Ghool Tak Cheshm"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Mar Sami"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Mar Ghool Peykar"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Gorge Sefid"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Jadougar Azam"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Siavash"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Nane Sarma"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Arzhang Div"));
                //Spells
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Total Disarm"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Lighting Bolt"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("All Disarm"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Dispel"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Sacrifice"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Shock"));
                break;
            case CAPTURE_THE_FLAG:
                player.getDeck().setHeroCard(ShopController.makeCopyForCreatingNewCardInShop("Zahak"));
                player.getDeck().setItemCard(ShopController.makeCopyForCreatingNewCardInShop("Soul Eater"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Shamshirzan Fars"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Neyzedar Fars"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Siavash"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Ghollabsangdar Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Shahzade Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Oghab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Oghab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Ezhdehaye Atsh Andaz"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Palang"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Giv"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Jen"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Iraj"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Shah Ghool"));
                //Spells
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Area Dispel"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Empower"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("God Strength"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Madness"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Poison Lake"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Health With Profit"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Kings Gaurd"));
                break;
            case COLLECTING_FLAGS:
                player.getDeck().setHeroCard(ShopController.makeCopyForCreatingNewCardInShop("Arash"));
                player.getDeck().setItemCard(ShopController.makeCopyForCreatingNewCardInShop("Terror Hood"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Sepahsalar Fars"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Kamandar Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Jasoos Arab"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Ghool Sang Andaz"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Div Goraz Savar"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Div Goraz Savar"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Shir Darande"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Gorg"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Jadoogar"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Goraz Vahshi"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Piran"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Bahman"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Ghool Bozorg"));
                //Spells
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Hell Fire"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("All Disarm"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Dispel"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Power Up"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("All Power"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("All Attack"));
                player.getDeck().getDeckCards().add(ShopController.makeCopyForCreatingNewCardInShop("Weakening"));
                break;
        }
        return player;
    }
}
