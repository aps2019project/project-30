package com.company.Views;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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
                showHeroesCoordinates();
                break;
            case CAPTURE_THE_FLAG:
                showFlagsCordinates();
                break;
            case COLLECTING_FLAGS:
                //showFlagsCordinates();
                break;
        }
    }

    public static void printGraveYardCards(ArrayList<Card> cards) {
        ConsoleOutput.showAllCards(cards);
    }

    private static void showHeroesCoordinates() {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.printf(
                    "Player %d Hero health: %d\n", playerIndex + 1, ((Hero) Battle.getPlayingBattle().getPlayers()[playerIndex].getDeck().getHeroCard()).getHealth());
        }
    }

    private static void showPlayersMana() {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            System.out.println("Player " + (playerIndex + 1) +
                    " ----> manas: " + Battle.getPlayingBattle().getPlayers()[playerIndex].getMana());
        }
    }

    private static void showFlagsCordinates() {
        for (int flagIndex = 0; flagIndex < Battle.getPlayingBattle().getFlags().size(); flagIndex++) {
            System.out.println("flag number " + (flagIndex + 1) +
                    " ---> X:" + Battle.getPlayingBattle().getFlags().get(flagIndex).getCell().getxCoordinate()
                    + "\t Y:" + Battle.getPlayingBattle().getFlags().get(flagIndex).getCell().getyCoordinate());
        }
    }

    public static void showHand() {
        Item.showItemsInBattle(Battle.getPlayingBattle().getTurnToPlay().getItems());
        Card.showMinionsAndSpellsInBattle(Battle.getPlayingBattle().getTurnToPlay().getDeck().getHand().getCards());
    }

    public static void printBattleCommandsToHelp() {
        System.out.println("game info: to seeing game information!\n" +
                "show my minions : to seeing your minions!\n" +
                "show opponent minions : to seeing your opppnent minions!\n" +
                "show card info [cardId] : to seeing this card information\n" +
                "select [cardId]: obviously to selecting specific card\n" +
                "move to [x][y]: nothing to explain\n" +
                "attack [opponent cardId]: :)\n" +
                "attack combo [opponent cardId][my cardId][my cardId]...: to attack to one card with many cards\n" +
                "use special power [x][y]\n" +
                "insert [cardName] in [x][y]\n" +
                "end turn\n" +
                "show collectibles: to seeing collected items\n" +
                "select [collectibleId]: to selecting an item\n" +
                "show info: to seeing selected card information\n" +
                "show info [cardId]: to seeing specific card information\n" +
                "use [x][y]: to using selected item in specific location\n" +
                "show next card: to seeing the next card that will add to hand\n" +
                "enter graveyard: to go to graveyard menu\n" +
                "show cards: to seeing graveyard cards\n" +
                "end game: to ending game!\n" +
                "exit\n" +
                "show menu\n" +
                "help: to seeing this help!\n");
    }

    public static VBox cardDesciption(Card card) {
        VBox vBox = new VBox();
        vBox.setSpacing(70);
        Image cardGif;
        if (card instanceof Spell)
            cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + ".gif");
        else
            cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_breathing.gif");
        ImageView imageView = new ImageView(cardGif);

        vBox.getChildren().add(imageView);
        if (!(card instanceof Spell)) {
            vBox.getStyleClass().add("solder-description");
            HBox powers = new HBox();
            powers.setSpacing(70);
            Label heath = new Label(String.valueOf(((Soldier) card).getHealth()));
            Label attck = new Label(String.valueOf(((Soldier) card).getAttackPower()));
            vBox.getChildren().add(powers);
        }
        else {
            vBox.getStyleClass().add("spell-description");
        }
        Label name = new Label(card.getName());
        vBox.getChildren().add(name);
        return vBox;
    }
}
