package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Views.BattleView;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BattleController {
    Battle battle;

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    public void showMyMinions() {

    }

    public void showGraveYardCards() {
        ArrayList<Card> graveYardCards = new ArrayList<>();
        graveYardCards.addAll(battle.getPlayers()[0].getDeck().getDeckCards());
        graveYardCards.addAll(battle.getPlayers()[1].getDeck().getDeckCards());
        graveYardCards.removeIf(new Predicate<Card>() {
            @Override
            public boolean test(Card card) {
                return !card.isInGraveCards();
            }
        });
        BattleView.printGraveYardCards(graveYardCards);
    }
}
