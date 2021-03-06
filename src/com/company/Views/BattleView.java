package com.company.Views;

import com.company.Controllers.graphic.ChooseGameController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Soldier;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.User.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.WHITE;

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

    public static VBox cardDesciption(Card card,String state) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(262);
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setPrefWidth(200);
        anchorPane.setPrefHeight(262);
        vBox.getChildren().add(anchorPane);
        //vBox.setSpacing(70);
        Image cardGif;
        try {
            if (card instanceof Spell || card instanceof Item)
                cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_actionbar.gif");
            else
                cardGif = new Image("com/company/Views/graphic/images/gifs/" + card.getName() + "_idle.gif");
            ImageView imageView = new ImageView(cardGif);
            imageView.setFitWidth(120);
            imageView.setFitHeight(120);

            if(card instanceof Spell){
                anchorPane.getChildren().add(imageView);
                anchorPane.setTopAnchor(imageView, 70.5);
                anchorPane.setLeftAnchor(imageView, 78.0);
            }
            else {
                anchorPane.getChildren().add(imageView);
                anchorPane.setTopAnchor(imageView, 30.5);
                anchorPane.setLeftAnchor(imageView, 50.0);
            }
        }catch (Exception e) { }

        if(state.equals("collection")){
            vBox.getStyleClass().add("collection-cards-description");
        }

        if (!(card instanceof Spell)) {
            if(state.equals("game"))vBox.getStyleClass().add("solder-description");
            HBox powers = new HBox();
            powers.setSpacing(100);
            Label heath = new Label(String.valueOf(((Soldier) card).getHealth()));
            Label attack = new Label(String.valueOf(((Soldier) card).getAttackPower()));
            attack.setTextFill(WHITE);
            heath.setTextFill(WHITE);
            powers.getChildren().add(attack);
            powers.getChildren().add(heath);
            anchorPane.setLeftAnchor(powers,38.0);
            anchorPane.setTopAnchor(powers,145.0);
            anchorPane.getChildren().add(powers);
        }
        else {
            if(state.equals("game"))vBox.getStyleClass().add("spell-description");
        }
        Label name = new Label(card.getName());
        anchorPane.getChildren().add(name);
        anchorPane.setBottomAnchor(name,35.0);
        if(card instanceof Spell){
            anchorPane.setLeftAnchor(name,80.8);
        }
        else
        anchorPane.setLeftAnchor(name,60.8);
        name.setTextFill(WHITE);

        return vBox;
    }


    public static VBox getInfo(){
        VBox vBox=new VBox();
        AnchorPane anchorPane=new AnchorPane();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(262);
        vBox.getChildren().add(anchorPane);
        vBox.getStyleClass().add("collection-cards-description");
       // vBox.setSpacing(20);
        switch (ChooseGameController.mode){
            case CAPTURE_THE_FLAG:
                Label flagLocation=new Label();
                Label flagHolder=new Label();
                flagLocation.setText(String.valueOf(Battle.getPlayingBattle().getFlags().get(0).getCell().getxCoordinate())+"   "+String.valueOf(Battle.getPlayingBattle().getFlags().get(0).getCell().getyCoordinate()));
                if(Battle.getPlayingBattle().getFlags().get(0).getFlagHolder()==null){
                    flagHolder.setText("no one");
                }
                else
                flagHolder.setText(Battle.getPlayingBattle().getFlags().get(0).getFlagHolder().getName());

                anchorPane.getChildren().add(flagHolder);
                anchorPane.getChildren().add(flagLocation);

                break;
            case KILLING_GENERAL:
                Label label=new Label();
                Label label1=new Label();

                label.setText(Battle.getPlayingBattle().getPlayers()[0].getDeck().getHeroCard().getName()+"  : "+((Soldier)(Battle.getPlayingBattle().getPlayers()[0].getDeck().getHeroCard())).getHealth());
                label1.setText(Battle.getPlayingBattle().getPlayers()[1].getDeck().getHeroCard().getName()+"  : "+((Soldier)(Battle.getPlayingBattle().getPlayers()[1].getDeck().getHeroCard())).getHealth());
                anchorPane.getChildren().add(label);
                anchorPane.getChildren().add(label1);
                anchorPane.setTopAnchor(label,50.0);
                anchorPane.setTopAnchor(label1,150.0);
                anchorPane.setLeftAnchor(label,50.0);
                anchorPane.setLeftAnchor(label1,50.0);
                label.setTextFill(WHITE);
                label1.setTextFill(WHITE);

                break;
            case COLLECTING_FLAGS:
                int i=1;
                for(Flag flag:Battle.getPlayingBattle().getFlags()){
                    i++;
                    Label l=new Label();
                    Label flagDescription=new Label();
                    if(flag.getFlagHolder()==null){
                        flagDescription.setText("no one");
                    }
                    else {

                        if(ex(flag.getFlagHolder(),Battle.getPlayingBattle().getPlayers()[0])){
                            flagDescription.setText(flag.getFlagHolder().getName() + " from "+Battle.getPlayingBattle().getPlayers()[0].getName());
                        }
                        else {
                            flagDescription.setText(flag.getFlagHolder().getName() + " from "+Battle.getPlayingBattle().getPlayers()[1].getName());
                        }
                    }
                    anchorPane.getChildren().add(flagDescription);
                    anchorPane.setTopAnchor(flagDescription,i*20.0);
                    anchorPane.setLeftAnchor(flagDescription,50.0);
                    flagDescription.setTextFill(WHITE);
                }
                break;


        }
        return vBox;
    }

    public static boolean ex(Card card ,Player player){
        for(Card c:player.getDeck().getDeckCards()){
            if(c.getId().equals(card.getId()))
                return true;
        }
        return false;
    }

}

