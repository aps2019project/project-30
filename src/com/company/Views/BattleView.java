package com.company.Views;

import com.company.Models.Card.Card;
import com.company.Models.Card.Minion.Minion;

import java.util.ArrayList;

public class BattleView {
    public static void showMinions(ArrayList<Minion> minions) {
        minions.forEach(minion ->
                System.out.printf(
                        "%d : %s, health : %d, location : (%d, %d), power : %d\n",
                        minion.getId(),
                        minion.getName(),
                        minion.getxCoordiante(),
                        minion.getyCoordinate(),
                        minion.getAttackPower()
                        )
        );
    }
}
