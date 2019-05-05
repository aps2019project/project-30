package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.ActivationTime;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Models.User.Player;
import com.company.Views.BattleView;
import com.company.Views.ConsoleOutput;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class BattleController {
    Battle battle;

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    public void showMySoldiers() {
        List<Card> myCards = (List<Card>) battle.getTurnToPlay().getUsedCards().clone();
        showSoldiers(myCards);
    }

    public void showSoldiers(List<Card> soldiers) {
        soldiers.removeIf(Card::isInGraveCards);
        soldiers.removeIf(card -> !(card instanceof Soldier));
        BattleView.printSoldiersInfo(soldiers);
    }

    public void showOpponentSoldiers() {
        List<Card> opponentCards = (List<Card>) getEenmyPlayer(battle.getTurnToPlay()).getUsedCards().clone();
        showSoldiers(opponentCards);
    }

    public void move(int x, int y) {
        if (!cellIsValidToMove(x, y, ((Soldier) battle.getTurnToPlay().getSelectedCard()).getCell())) {
            ConsoleOutput.printErrorMessage(ErrorType.INVALID_CELL);
        } else {
            if(!battle.getTurnToPlay().getUsedCardsToMove().contains(battle.getTurnToPlay().getSelectedCard())) {
                battle.getMap().getCellByCoordinates(((Soldier) battle.getTurnToPlay().getSelectedCard()).getCell().getxCoordinate(), ((Soldier) battle.getTurnToPlay().getSelectedCard()).getCell().getyCoordinate()).setCardInCell(null);
                ((Soldier) battle.getTurnToPlay().getSelectedCard()).setCell(battle.getMap().getCellByCoordinates(x, y));
                battle.getMap().getCellByCoordinates(x, y).setCardInCell(battle.getTurnToPlay().getSelectedCard());
                battle.getTurnToPlay().getUsedCardsToMove().add(battle.getTurnToPlay().getSelectedCard());
                if (battle.getMap().getCellByCoordinates(x, y).getItem() != null) {
                    battle.getTurnToPlay().addItem(battle.getMap().getCellByCoordinates(x, y).getItem());
                }
            }
        }
        System.out.println(battle.getMap().toString());
    }

    private boolean cellIsValidToMove(int x1, int y1, Cell cell) {
        int x2 = cell.getxCoordinate();
        int y2 = cell.getyCoordinate();
        if (abs(x1 - x2) + abs(y1 - y2) > 2) {
            return false;
        }
//        else if ((abs(x1 - x2) == 2 && !validPreviousCell(battle.getTurnToPlay(), battle.getMap().getCellByCoordinates(min(x1, x2) + 1, y1))) || (abs(y1 - y2) == 2 && !validPreviousCell(battle.getTurnToPlay(), battle.getMap().getCellByCoordinates(x1, min(y1, y2) + 1)))) {
//            return false;
//        }
        return cellIsValidToInsertingCard(x1,y1);
    }

    private boolean validRange(Cell cell) {
        int x = cell.getxCoordinate();
        int y = cell.getyCoordinate();
        if (x > 9 || x <= 0 || y > 5 || y <= 0) {
            return false;
        }
        return true;
    }

    private boolean cellIsValidToInsertingCard(int x, int y) {
        if (x > 9 || x <= 0 || y > 5 || y <= 0) {
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

    public void endTurn() {
        ((Hero)battle.getTurnToPlay().getDeck().getHeroCard()).decrementing();
        battle.getTurnToPlay().addMaxMana();
        battle.getTurnToPlay().setMana(battle.getTurnToPlay().getMaxMana());
        battle.getTurnToPlay().getAccount().getMainDeck().getDeckController().addRandomCardToHand();
        battle.getTurnToPlay().getUsedCardsToMove().clear();
        battle.getTurnToPlay().getUsedCardsToAttack().clear();

        if (battle.getTurnToPlay() == battle.getPlayers()[0]) {
            battle.setTurnToPlay(battle.getPlayers()[1]);
        } else {
            battle.setTurnToPlay(battle.getPlayers()[0]);
        }
    }
    public void useSpecialPower(int x, int y) {
        if (battle.getTurnToPlay().getSelectedCard() instanceof Hero) {
            if (((Hero) battle.getTurnToPlay().getSelectedCard()).getCoolDownRemaining() != 0) {
                ConsoleOutput.printErrorMessage(ErrorType.COOLDOWN_VALIDATE);
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
                    doSpecialPoweronEnemyMinion(x, y);
                    break;
                case FRIEND_MINION:
                    doSpecialPowerOnFriendMinion(x, y);
                    break;
                case ENEMY_HERO:
                    doSpecialPowerEnemyHero(x, y);
                    break;
                case FRIEND_HERO:
                    doSpecialPowerFriendHero(x, y);
                    break;
                case ENEMY_SOLDIER:
                    doSpecialPowerOnEnemySolder(x, y);
                    break;
                case FRIEND_SOLDIER:
                    doSpecialPowerOnFreindSolder(x, y);
                    break;
                case WHOLE_ENEMY:
                    doSpecialPowerOnWholeEnemy();
                    break;
                case WHOLE_FRIEND:
                    doSpecialPowerOnWholeFriend();
                    break;
                case NEAR_ENEMYS_HERO:
                    doSpecialPowerOnNear("enemy");
                    break;
                case NEAR_FRIENDS_HERO:
                    doSpecialPowerOnNear("friend");
                    break;
                case SQUARE_2:
                    doOnSquare2();
                    break;
                case SQUARE_3:
                    doOnSquare3();
                    break;
                case ENEMY_ROW:
                    doOnEnemyRow();
                    break;
                case ENEMY_COLUMN:
                    doOnEnemyColum();
                    break;
            }
        }
        ConsoleOutput.printErrorMessage(ErrorType.ENOUGH_MANA);
    }

    private void doOnEnemyRow() {
        int y = 0;
        for (Card card : getEenmyPlayer(battle.getTurnToPlay()).getUsedCards()) {
            if (card instanceof Hero) {
                y = ((Hero) card).getCell().getyCoordinate();
            }
        }
        for (int i = 0; i < 9; i++) {
            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(i, y));
        }
    }

    private void doOnEnemyColum() {
        int x = 0;
        for (Card card : getEenmyPlayer(battle.getTurnToPlay()).getUsedCards()) {
            if (card instanceof Hero) {
                x = ((Hero) card).getCell().getxCoordinate();
            }
        }
        for (int i = 0; i < 5; i++) {
            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, i));
        }
    }

    private void doOnSquare3() {
        for (int i = -2; i <= 0; i++) {
            for (int j = -2; j <= 0; j++) {
                if (!(i == 0 && j == 0) && validRange(battle.getMap().getCellByCoordinates(i, j))) {
                    doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(i, j));
                }
            }
        }
    }

    private void doOnSquare2() {
        for (int i = -1; i <= 0; i++) {
            for (int j = -1; j <= 0; j++) {
                if (!(i == 0 && j == 0) && validRange(battle.getMap().getCellByCoordinates(i, j))) {
                    doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(i, j));
                }
            }
        }
    }

    private void doSpecialPowerOnNear(String type) {
        int x = 0, y = 0;
        if (type.equals("enemy")) {
            for (Card card : getEenmyPlayer(battle.getTurnToPlay()).getUsedCards()) {
                if (card instanceof Hero) {
                    x = ((Hero) card).getCell().getxCoordinate();
                    y = ((Hero) card).getCell().getyCoordinate();
                    break;
                }
            }
        } else {
            for (Card card : battle.getTurnToPlay().getUsedCards()) {
                if (card instanceof Hero) {
                    x = ((Hero) card).getCell().getxCoordinate();
                    y = ((Hero) card).getCell().getyCoordinate();
                    break;
                }
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j < 1; j++) {
                if (!(i == 0 && j == 0)) {
                    doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x + i, y + j));
                }
            }
        }
    }

    private void doSpecialPowerOnWholeEnemy() {
        for (Card card : getEenmyPlayer(battle.getTurnToPlay()).getUsedCards()) {
            if (card instanceof Hero || card instanceof Minion) {
                doUseSpecialPowerSwichCase(((Soldier) card).getCell());
            }
        }
    }

    private void doSpecialPowerOnWholeFriend() {
        for (Card card : battle.getTurnToPlay().getUsedCards()) {
            if (card instanceof Hero || card instanceof Minion) {
                doUseSpecialPowerSwichCase(((Soldier) card).getCell());
            }
        }
    }

    private void doSpecialPowerOnEnemySolder(int x, int y) {
        if (playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == battle.getTurnToPlay()) {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
            return;
        }
        doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));
    }

    private void doSpecialPowerOnFreindSolder(int x, int y) {
        if (playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == getEenmyPlayer(battle.getTurnToPlay())) {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
            return;
        }
        doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));
    }

    private void doSpecialPowerEnemyHero(int x, int y) {
        if ((battle.getMap().getCellByCoordinates(x, y).getCardInCell() instanceof Hero) &&
                playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == getEenmyPlayer(battle.getTurnToPlay())) {
            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
        }
    }

    private void doSpecialPowerFriendHero(int x, int y) {
        if ((battle.getMap().getCellByCoordinates(x, y).getCardInCell() instanceof Hero) &&
                playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == battle.getTurnToPlay()) {
            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
        }
    }

    private void doSpecialPowerOnFriendMinion(int x, int y) {
        if (battle.getMap().getCellByCoordinates(x, y).getCardInCell() instanceof Minion &&
                playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == battle.getTurnToPlay()) {
            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
        }


    }

    private void doSpecialPoweronEnemyMinion(int x, int y) {
        if ((battle.getMap().getCellByCoordinates(x, y).getCardInCell() instanceof Minion) &&
                playerThatHasThisCard(battle.getMap().getCellByCoordinates(x, y).getCardInCell()) == getEenmyPlayer(battle.getTurnToPlay())) {

            doUseSpecialPowerSwichCase(battle.getMap().getCellByCoordinates(x, y));

        } else {
            ConsoleOutput.printErrorMessage(ErrorType.CELL_VALIDATE);
        }

    }


    private void doUseSpecialPowerSwichCase(Cell cell) {
        if (!validRange(cell))
            return;
        int startEndenx = battle.getTurnToPlay().getSelectedCard().getBuffsCasted().size();
        for (Buff buff : battle.getTurnToPlay().getSelectedCard().getBuffsToCast()) {
            Buff buff1 = buff.clone();
            buff1.setCardToCast(cell.getCardInCell());
            cell.getCardInCell().getBuffsCasted().add(buff1);
        }
        int counter = 0;
        for (Buff buff : cell.getCardInCell().getBuffsCasted()) {
            counter++;
            if (counter == startEndenx) {
                buff.cast();
            }
        }
    }

    private Player getEenmyPlayer(Player player) {
        if (player == battle.getPlayers()[0]) {
            return battle.getPlayers()[1];
        } else {
            return battle.getPlayers()[0];

        }
    }

    public void showMyMinion() {

    }

    public void showGraveYardCards() {
        BattleView.printGraveYardCards(getGraveYard());
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
        playerCards.add(battle.getTurnToPlay().getDeck().getHeroCard());
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

    public Card getCardById(String cardId) {
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

    private Card getCardByIdFromGraveYardCards(String cardId) {
        for (Card card :getGraveYard()) {
            if(card.getId().equals(cardId)){
                return card;
            }
        }
        return null;
    }

    private boolean cardExistsInGraveYard(String cardId){
        for (Card card :getGraveYard()) {
            if(card.getId().equals(cardId)){
                return true;
            }
        }
        return false;
    }

    private boolean cardExistsInDeck(String cardId){
        for (Card card :battle.getTurnToPlay().getDeck().getDeckCards()) {
            if(card.getId().equals(cardId)){
                return true;
            }
        }
        return false;
    }

    public void showCardFromGraveYardInformation(String cardId){
        if(cardExistsInGraveYard(cardId)){
            BattleView.showCardInformation(getCardByIdFromGraveYardCards(cardId));
        }else{
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUNDINGRAVEYARD);
        }
    }

    public void showDeckCardInformation(String cardId){
        if(cardExistsInDeck(cardId)){
            BattleView.showCardInformation(getCardById(cardId));
        }else{
            ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUNDINDECK);
        }
    }

    private ArrayList<Card> getGraveYard() {
        ArrayList<Card> graveYardCards = new ArrayList<>();
        graveYardCards.addAll(battle.getPlayers()[0].getDeck().getDeckCards());
        graveYardCards.addAll(battle.getPlayers()[1].getDeck().getDeckCards());
        graveYardCards.removeIf(card -> !card.isInGraveCards());
        return graveYardCards;
    }

    public void insertNewCardToMap(int x, int y, String cardName) {
        if (cellIsValidToInsertingCard(x, y)) {
            if (isCardNameValid(cardName)) {
                //Card newCard = createCopyFromExistingCard(getCardByName(cardName));
                Card newCard = getCardByNameFromHand(cardName);
                if (newCard.getManaPoint() <= battle.getTurnToPlay().getMana()) {
                    Cell cell = battle.getMap().getCellByCoordinates(x, y);
                    cell.setCardInCell(newCard);
                    ((Soldier) newCard).setCell(cell);
                    Battle.getPlayingBattle().getTurnToPlay().decrementMana(newCard.getManaPoint());
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

    public Card getCardByNameFromHand(String cardName) {
        for (Card card : Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards()) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;//that means there is no card with this name
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

    public static Card createCopyFromExistingCard(Card card) {
        switch (Card.getCardType(card.getName())) {
            case "Item":
                return ((Item) Shop.getCardByName(card.getName())).clone();
            case "Minion":
                return ((Minion) Shop.getCardByName(card.getName())).clone();
            case "Hero":
                return ((Hero) Shop.getCardByName(card.getName())).clone();
            case "Spell":
                return ((Spell) Shop.getCardByName(card.getName())).clone();
        }
        return null;
    }

    public void attack(Cell target, Boolean isCombo) {
        Player turnToPlay = Battle.getPlayingBattle().getTurnToPlay();
        ErrorType errorType = getErrorTypeOfAttack(target, turnToPlay);
        if (errorType != null) {
            ConsoleOutput.printErrorMessage(errorType);
        } else {
            ((Soldier) turnToPlay.getSelectedCard()).attack(target.getCardInCell(), isCombo);

        }
    }

    private ErrorType getErrorTypeOfAttack(Cell target, Player turnToPlay) {
        Cell selectedCardCell = ((Soldier) turnToPlay.getSelectedCard()).getCell();
        int selectedCardAreaOfEffect = ((Soldier) turnToPlay.getSelectedCard()).getAreaOfEffect();
        if (!isCardIdValid(target.getCardInCell().getId())) {
            return ErrorType.CARD_ID_INVALID;
        }
        switch (((Soldier) turnToPlay.getSelectedCard()).getAttackType()) {
            case MELEE:
                if (!isNearby(target, selectedCardCell)) {
                    return ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
            case RANGED:
                if (getDistance(target, selectedCardCell) > selectedCardAreaOfEffect
                        || isNearby(target, selectedCardCell)) {
                    return ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
            case HYBRID:
                if (getDistance(target, selectedCardCell) > selectedCardAreaOfEffect) {
                    return ErrorType.UNAVAILABLE_OPPONENT_SOLDIER;
                }
                break;
        }
        if (!turnToPlay.getUsedCardsToAttack().contains(turnToPlay.getSelectedCard())) {
            return ErrorType.CARD_CANT_ATTACK;
        }
        return null;
    }

    private static int getDistance(Cell beginning, Cell end) {
        int xDistance = beginning.getxCoordinate() - end.getxCoordinate();
        int yDistance = beginning.getyCoordinate() - end.getyCoordinate();
        return (int) Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    private static boolean isNearby(Cell home, Cell toCheck) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (toCheck.getxCoordinate() + i == home.getxCoordinate()
                        && toCheck.getyCoordinate() + j == home.getyCoordinate()
                        && !(i == 0 && j == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void attackCombo(String oponentId, ArrayList<String> cardsId) {
        Cell cell = ((Minion) getCardById(oponentId)).getCell();
        for (String cardId : cardsId) {
            if (getCardById(cardId) instanceof Minion) {
                if (((Minion) getCardById(cardId)).getActivationTime().equals(ActivationTime.COMBO)) {
                    selectCard(cardId);
                    attack(cell, true);
                }
            }
        }
    }
}
