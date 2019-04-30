package com.company.Views;

import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Minion.Minion;

import java.util.ArrayList;

public class BattleView {
    Battle battle;

    public BattleView(Battle battle) {
        this.battle = battle;
    }

    public static void printMinionsInfo(ArrayList<Minion> minions) {
        minions.forEach(minion ->
                System.out.printf(
                        "%d : %s, health : %d, location : (%d, %d), power : %d\n",
                        minion.getId(),
                        minion.getName(),
                        minion.getCell().getxCoordinate(),
                        minion.getCell().getyCoordinate(),
                        minion.getAttackPower()
                )
        );
    }

    public void printGameInfo() {
        showPlayersMana();
        switch (Battle.getPlayingBattle().getMode()) {
            case KILLING_GENERAL:
                showHeroesCordinates();
                break;
            case CAPTURE_THE_FLAG:
                showFlagsCordinates();
                break;
            case COLLECTING_FLAGS:
                showFlagsCordinates();
                break;
        }
    }


    public static void printGraveYardCards(ArrayList<Card> cards) {
        //Todo : printGraveYardCards : View
    }

    private static void showHeroesCordinates() {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.printf(
                    "Player %d Hero health: %d\n", playerIndex + 1, Battle.getPlayingBattle().getPlayers()[playerIndex].getMana());
        }
    }

    private static void showPlayersMana() {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.println("Player " + playerIndex +
                    "---- > manas: " + Battle.getPlayingBattle().getPlayers()[playerIndex].getMana());
        }
    }

    private static void showFlagsCordinates() {
        for (int flagIndex = 0; flagIndex < Battle.getPlayingBattle().getFlags().size(); flagIndex++) {
            System.out.println("flag number " + (flagIndex + 1) +
                    " ---> X:" + Battle.getPlayingBattle().getFlags().get(flagIndex).getCell().getxCoordinate()
                    + "\t Y:" + Battle.getPlayingBattle().getFlags().get(flagIndex).getCell().getyCoordinate());
        }
    }
}
