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
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.printf(
                    "Player %d: \n",playerIndex +
                            "manas: %d\n", battle.getPlayers()[playerIndex].getMana());
        }
        switch (Battle.getPlayingBattle().getMode()) {
            case KILLING_GENERAL:
                showHeroesCordinates();
                break;
            case CAPTURE_THE_FLAG:

                break;
            case COLLECTING_FLAGS:
                break;
        }
    }


    public static void printGraveYardCards(ArrayList<Card> cards) {
        //Todo : printGraveYardCards : View
    }

    private static void showHeroesCordinates(){
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.printf(
                    "Player %d Hero health: %d\n",playerIndex,Battle.getPlayingBattle().getPlayers()[playerIndex].getMana());
        }
    }
}
