package com.company.Views;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.ErrorType;
import com.company.Views.Console.CollectionViews;

import java.util.ArrayList;
import java.util.List;

public class BattleView {
    Battle battle;

    public BattleView(Battle battle) {
        this.battle = battle;
    }

    public static void printSoldiersInfo(List<Card> cards) {
        cards.forEach(soldier ->
                System.out.printf(
                        "\t%s - %s, health : %d, location : (%d, %d), power : %d\n",
                        soldier.getId(),
                        soldier.getName(),
                        ((Soldier) soldier).getHealth(),
                        ((Soldier) soldier).getCell().getxCoordinate(),
                        ((Soldier) soldier).getCell().getyCoordinate(),
                        ((Soldier) soldier).getAttackPower()
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

    public static void showSelectedCardInformation(){
        Card card = Battle.getPlayingBattle().getTurnToPlay().getSelectedCard();
        if(card != null) {
            if (card instanceof Minion) {
                Minion.showMinion((Minion) card);
            } else if (card instanceof Item) {
                Item.showItem((Item) card);
            } else if (card instanceof Hero) {
                Hero.showHero((Hero) card);
            } else if (card instanceof Spell) {
                Spell.showSpell((Spell) card);
            }
        } else{
            ConsoleOutput.printErrorMessage(ErrorType.NO_SELECTED_CARD);
        }
    }

    public static void printGraveYardCards(ArrayList<Card> cards) {
        ConsoleOutput.showAllCards(cards);
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
