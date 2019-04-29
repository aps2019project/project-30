package com.company.Views;

import com.company.Models.Battle.Battle;
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
                    "Player %d: \n" +
                            "manas: %d\n", battle.getPlayers()[playerIndex].getMana());
        }
        //mode info
    }


    public static void printGraveYardCards(ArrayList<Card> cards) {
        //Todo : printGraveYardCards : View
    }
}
