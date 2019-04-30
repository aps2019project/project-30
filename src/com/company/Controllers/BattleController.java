package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Battle.Map.Map;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
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
                if(battle.getMap().getCellByCoordinates(x,y).getItem()!=null){
                    battle.getTurnToPlay().addItem(battle.getMap().getCellByCoordinates(x,y).getItem());
                }
            }
        } else if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
            if (!cellIsValidToMove(x, y, ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell())) {
                ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
            } else {
                battle.getMap().getCellByCoordinates(((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Hero) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Hero) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));
                if(battle.getMap().getCellByCoordinates(x,y).getItem()!=null){
                    battle.getTurnToPlay().addItem(battle.getMap().getCellByCoordinates(x,y).getItem());
                }
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

    public void useSpecialPawer(int x, int y) {
        if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
            if (((Hero) battle.getTurnToPlay().getSelectedCard()).getCoolDownRemaining() != 0) {
                //todo eror message
                return;
            }
        }
        if (battle.getTurnToPlay().getSelectedCard().getManaPoint() <= battle.getTurnToPlay().getMana()) {
            int newmana = battle.getTurnToPlay().getMana() - battle.getTurnToPlay().getSelectedCard().getManaPoint();
            battle.getTurnToPlay().setMana(newmana);
            if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
                ((Hero) battle.getTurnToPlay().getSelectedCard()).setRemainingCoolDown(((Hero) battle.getTurnToPlay().getSelectedCard()).getCoolDown());
            }
            switch (battle.getTurnToPlay().getSelectedCard().getTargetType()) {
                case ENEMY_MINION:
                    if (!(battle.getMap().getCellByCoordinates(x, y).getCardInCell() instanceof Minion)) {
                        //todo eror message
                    }
                    doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x,y));
                    break;
                case ENEMY_HERO:

                   break;
                case ENEMY_SOLDIER:


            }
        }
    }
    //private void do()

    private void doUseSpecialPowerSwichCase(Cell cell) {
        int x=cell.getxCoordinate();
        int y=cell.getyCoordinate();
        int startEndenx = battle.getTurnToPlay().getSelectedCard().getBuffsCasted().size();
        for (Buff buff : battle.getTurnToPlay().getSelectedCard().getBuffsToCast()) {
            Buff buff1 = buff.clone();
            buff1.setCardToCast(battle.getMap().getCellByCoordinates(x, y).getCardInCell());
            battle.getMap().getCellByCoordinates(x, y).getCardInCell().getBuffsCasted().add(buff1);
        }
        int counter = 0;
        for (Buff buff : battle.getMap().getCellByCoordinates(x, y).getCardInCell().getBuffsCasted()) {
            counter++;
            if (counter == startEndenx) {
                buff.cast();
            }
        }
    }
    private Player getEenmyPlayer(Player player){
        if(player==battle.getPlayers()[0]){
            return battle.getPlayers()[1];
        }
        return player;
    }
    public void showMyMinion(){

    }

    public void showGraveYardCards() {
        ArrayList<Card> graveYardCards = new ArrayList<>();
        graveYardCards.addAll(battle.getPlayers()[0].getDeck().getDeckCards());
        graveYardCards.addAll(battle.getPlayers()[1].getDeck().getDeckCards());
        graveYardCards.removeIf(card -> !card.isInGraveCards());
        BattleView.printGraveYardCards(graveYardCards);
    }

    public void selectCard(String cardId) {
        if (isCardIdValid(cardId)) {
            battle.getTurnToPlay().setSelectedCard(getCardById(cardId));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CARD_ID_INVALID);
        }
    }

    private boolean isCardIdValid(String cardId) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getId().equals(cardId))
                return true;
        }
        return false;
    }

    private boolean isCardNameValid(String cardName) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getName().equals(cardName))
                return true;
        }
        return false;
    }

    private Card getCardById(String cardId) {
        List<Card> playerCards = battle.getTurnToPlay().getDeck().getDeckCards();
        for (Card playerCard : playerCards) {
            if (playerCard.getId().equals(cardId))
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
            if (isCardNameValid(cardName)) {
                Card newCard = createCopyFromExistingCard(getCardByName(cardName));
                if (newCard.getManaPoint() >= battle.getTurnToPlay().getMana()) {
                    Cell cell = battle.getMap().getCellByCoordinates(x, y);
                    cell.setCardInCell(newCard);
                    Battle.getPlayingBattle().getTurnToPlay().getUsedCards().add(newCard);
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

    public static Player playerThatHasThisCard(Card card) {
        for (int i = 0; i < 2; i++) {
            List<Card> playerCards = Battle.getPlayingBattle().getTurnToPlay().getDeck().getDeckCards();
            for (Card c : playerCards) {
                if (c.getId().equals(card.getId())) {
                    return Battle.getPlayingBattle().getPlayers()[i];
                }
            }
        }
        return null;
    }

    public static Card createCopyFromExistingCard(Card card){
        switch (Card.getCardType(card.getName())) {
            case "Item":
                return ((Item) Shop.getCardByName(card.getName())).clone();
            case "Minion":
                return ((Minion) Shop.getCardByName(card.getName())).clone();
            case "Hero":
                return ((Hero) Shop.getCardByName(card.getName())).clone();
        }
        return null;
    }

    public void attack(Cell target) {
        Player turnToPlay = Battle.getPlayingBattle().getTurnToPlay();
        ErrorType errorType = null;
        if (!isCardIdValid(target.getCardInCell().getId())){
            errorType = ErrorType.CARD_ID_INVALID;
        }
        switch (((Soldier) turnToPlay.getSelectedCard()).getAttackType()){
            case MELEE :
                if (!isNearby(target, ((Soldier) turnToPlay.getSelectedCard()).getCell())) {
                    errorType = ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
            case RANGED:
                if (getDistance(target, ((Soldier) turnToPlay.getSelectedCard()).getCell()) > ((Soldier) turnToPlay.getSelectedCard()).getAreaOfEffect()
                || isNearby(target, ((Soldier) turnToPlay.getSelectedCard()).getCell())) {
                    errorType = ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
            case HYBRID:
                if (getDistance(target, ((Soldier) turnToPlay.getSelectedCard()).getCell()) > ((Soldier) turnToPlay.getSelectedCard()).getAreaOfEffect()) {
                    errorType = ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
        }
        if (!turnToPlay.getUsedCardsToAttack().contains(turnToPlay.getSelectedCard())) {
            errorType = ErrorType.CARD_CANT_ATTACK;
        }
        if (errorType != null) {
            ConsoleOutput.printErrorMessage(errorType);
        } else {
            ((Soldier) turnToPlay.getSelectedCard()).attack(target.getCardInCell());
        }
    }

    private static int getDistance(Cell cell1, Cell cell2) {
        int xDistance = cell1.getxCoordinate() - cell2.getxCoordinate();
        int yDistance = cell1.getyCoordinate() - cell2.getyCoordinate();
        return (int) Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    private static boolean isNearby(Cell cell1, Cell cell2) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (cell2.getxCoordinate() + i == cell1.getxCoordinate()
                && cell2.getyCoordinate() + j == cell1.getyCoordinate()
                && !(i == 0 && j == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

}
