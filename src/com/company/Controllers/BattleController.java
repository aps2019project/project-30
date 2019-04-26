package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Views.BattleView;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;
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

    public void selectCard(int cardId) {
        if (isCardIdValid(cardId)) {
            battle.getTurnToPlay().setSelectedCard(getCardById(cardId));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_ID_INVALID);
        }
    }

    private boolean isCardIdValid(int cardId) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getId() == cardId)
                return true;
        }
        return false;
    }

    private Card getCardById(int cardId) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getId() == cardId)
                return playerCard;
        }
        return null;
    }
}
