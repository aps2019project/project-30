package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Battle.Map.Map;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Views.BattleView;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static java.lang.Math.*;

public class BattleController {
    Battle battle;

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    public void showMyMinions() {

    }

    public void move(int x, int y) {
        //todo buff and item
        if (battle.getTurnToPlay().getSelectedCard() instanceof Minion) {
            if (!cellIsValidToMove(x, y, ((Minion) battle.getTurnToPlay().getSelectedCard()).getCell())) {
                ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
            } else {
                battle.getMap().getCellByCoordinates(((Minion) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Minion) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Minion) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));

            }
        } else if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
            if (!cellIsValidToMove(x, y, ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell())) {
                ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
            } else {
                battle.getMap().getCellByCoordinates(((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Hero) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));
            }
        }
    }

    private boolean cellIsValidToMove(int x1, int y1, Cell cell) {
        int x2 = cell.getxCoordinate();
        int y2 = cell.getyCoordinate();
        if (abs(x1 - x2) > 2 || abs(y1 - y2) > 2 || (abs(x1 - x2) == 2 && abs(y1 - y2) > 0) || (abs(y1 - y2) == 2 && abs(x1 - x2) > 0)) {
            return false;
        } else if ((abs(x1 - x2) == 2 && validPreviousCell(battle.getTurnToPlay(), battle.getMap().getCellByCoordinates(min(x1, x2) + 1, y1))) || (abs(y1 - y2) == 2 && validPreviousCell(battle.getTurnToPlay(), battle.getMap().getCellByCoordinates(x1, min(y1, y2) + 1)))) {
            return false;
        } else if (x1 >= 9 || x1 < 0 || y1 >= 5 || y1 < 0) {
            return false;
        } else if (battle.getMap().getCellByCoordinates(x2, y2).getCardInCell() != null) {
            return false;
        }
        return true;
    }

    private boolean cellIsValidToInsertingCard(int x, int y) {
        if (x >= 9 || x < 0 || y >= 5 || y < 0) {
            return false;
        } else if (battle.getMap().getCellByCoordinates(x, y).getCardInCell() != null) {
            return false;
        }
        return true;
    }

    private boolean validPreviousCell(Player player, Cell cell) {
        int x = cell.getxCoordinate();
        int y = cell.getyCoordinate();
        Account account;
        if (battle.getPlayers()[0] == player) {
            account = battle.getPlayers()[1].getAccount();
        } else {
            account = battle.getPlayers()[0].getAccount();
        }
        Card card;
        if (battle.getMap().getCellByCoordinates(x, y).getCardInCell() != null) {
            card = cell.getCardInCell();
            for (Card card1 : account.getCollection().getCards()) {
                if (card.getId() == card1.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void endTern() {
        Random rand = new Random();
        Card card;
        while (true) {
            int a = 0;
            int rand_int1 = rand.nextInt(19);
            card = battle.getTurnToPlay().getAccount().getMainDeck().getDeckCards().get(rand_int1);
            for (Card card1 : battle.getTurnToPlay().getGraveYard().getDestroyedCards()) {
                if (card == card1)
                    a = 1;

            }
            if (a == 1) continue;
            break;
        }
        battle.getTurnToPlay().getAccount().getMainDeck().getHand().addCard(card);
        if (battle.getTurnToPlay() == battle.getPlayers()[0]) {
            battle.setTurnToPlay(battle.getPlayers()[1]);
        } else {
            battle.setTurnToPlay(battle.getPlayers()[0]);
        }

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

    private boolean isCardIdValid(String cardName) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getName().equals(cardName))
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

    private Card getCardByName(String cardName) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getName().equals(cardName))
                return playerCard;
        }
        return null;
    }

    public void insertNewCardToMap(int x, int y, String cardName) {
        if (cellIsValidToInsertingCard(x, y)) {
            if (isCardIdValid(cardName)) {
                Card card = getCardByName(cardName);
                if (card.getManaPoint() >= battle.getTurnToPlay().getMana()) {
                    Cell cell = battle.getMap().getCellByCoordinates(x, y);
                    cell.setCardInCell(card);
                    Battle.getPlayingBattle().getTurnToPlay().addNewCardToCards(card);
                } else {
                    ConsoleOutput.printErrorMessage(ErrorType.NOTENOUGH_MANA);
                }
            } else {
                ConsoleOutput.printErrorMessage(ErrorType.CARD_ID_INVALID);
            }
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
        }
    }

    public Player playerThatHasThisCard(Card card) {
        for (int i = 0; i < 2; i++) {
            List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
            for (Card c : playerCards) {
                if (c.getId() == card.getId()) {
                    return Battle.getPlayingBattle().getPlayers()[i];
                }
            }
        }
        return null;
    }
}
