package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Views.BattleView;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class BattleController {
    Battle battle;

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    public void showMyMinions() {

    }

    public void move(int x, int y) {
        if (battle.getTurnToPlay().getSelectedCard() instanceof Minion) {
            if (!cellIsValid(x, y, ((Minion) battle.getTurnToPlay().getSelectedCard()).getCell())) {
                ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
            } else {
                battle.getMap().getCellByCoordinates(((Minion) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Minion) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Minion) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));
            }
        } else if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
            if (!cellIsValid(x, y, ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell())) {
                ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
            } else {
                battle.getMap().getCellByCoordinates(((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Hero) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));
            }
        }
    }

    private boolean cellIsValid(int x1, int y1, Cell cell) {
        int x2 = cell.getxCoordinate();
        int y2 = cell.getyCoordinate();
        if (abs(x1 - x2) > 2 || abs(y1 - y2) > 2 || (abs(x1 - x2) == 2 && abs(y1 - y2) > 0) || (abs(y1 - y2) == 2 && abs(x1 - x2) > 0)) {
            return false;
        } else if ((abs(x1 - x2) == 2 &&validPreviousCell(battle.getTurnToPlay(),battle.getMap().getCellByCoordinates(min(x1,x2)+1,y1))) || (abs(y1 - y2) == 2 &&validPreviousCell(battle.getTurnToPlay(),battle.getMap().getCellByCoordinates(x1,min(y1,y2)+1)))) {
            return false;
        } else if (x1 >= 9 || x1 < 0 || y1 >= 5 || y1 < 0) {
            return false;
        } else if (battle.getMap().getCellByCoordinates(x2,y2).getCardInCell()!=null){
            return false;
        }
        return true;
    }
    private boolean validPreviousCell(Player player,Cell cell){
        int x=cell.getxCoordinate();
        int y=cell.getyCoordinate();
        Account account;
        if(battle.getPlayers()[0]==player) {
            account =battle.getPlayers()[1].getAccount();
        }
        else{
            account =battle.getPlayers()[0].getAccount();
        }
        Card card;
        if(battle.getMap().getCellByCoordinates(x,y).getCardInCell()!=null) {
            card=cell.getCardInCell();
            for (Card card1 : account.getCollection().getCards()) {
                if (card.getId() == card1.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showGraveYardCards() {
        ArrayList<Card> graveYardCards = new ArrayList<>();
        graveYardCards.addAll(battle.getPlayers()[0].getDeck().getDeckCards());
        graveYardCards.addAll(battle.getPlayers()[1].getDeck().getDeckCards());
        graveYardCards.removeIf(card -> !card.isInGraveCards());
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
