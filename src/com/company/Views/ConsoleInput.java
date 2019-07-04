package com.company.Views;

import com.company.Controllers.AccountController;
import com.company.Controllers.CollectionController;
import com.company.Controllers.ShopController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Card.Soldier;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;
import com.company.Views.Console.CollectionViews;
//import com.google.gson.*;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInput {
    public static final String SELL_CARD = "sell (?<cardId>\\d+)";
    public static final String CREATE_ACCOUNT = "create account (?<username>\\w+)";
    public static final String PASSWORD = "(?<password>\\w+)";
    public static final String LOGIN = "login (?<username>\\w+)";
    public static final String SHOW_LEADERBOARD = "show leaderboard";
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    public static final String LOGOUT = "logout";
    public static final String ENTER_COLLECTION = "enter collection";
    public static final String ENTER_SHOP = "enter shop";
    public static final String ENTER_BATTLE = "enter battle";
    public static final String SEARCH_CARD_NAME = "search (?<cardName>.+)";
    public static final String SHOW = "show";
    public static final String SHOW_COLLECTION = "show collection";
    public static final String SEARCH_COLLECTION_CARD_NAME = "search collection (?<cardName>.+)";
    public static final String BUY_CARD_NAME = "buy (?<cardName>.+)";
    private static Scanner scanner = new Scanner(System.in);

    public enum Menu {MAIN, ACCOUNT, COLLECTION, SHOP, NEW_BATTLE, BATTLE, GRAVEYARD, EXIT}

    private static Menu menu = Menu.ACCOUNT;

    public static Menu getMenu() {
        return menu;
    }

    public static void setMenu(Menu menu) {
        ConsoleInput.menu = menu;
    }

    public static void menusHandler() {
        while (true) {
            String command = scanner.nextLine();
            switch (menu) {
                case MAIN:
                    System.out.println("*** MAIN ***");
                    mainMenuCommandsChecker(command);
                    break;
                case ACCOUNT:
                    System.out.println("*** ACCOUNT ***");
                    accountMenuCommandsChecker(command);
                    break;
                case COLLECTION:
                    System.out.println("*** COLLECTION ***");
                    collectionMenuCommandsChecker(command);
                    break;
                case SHOP:
                    System.out.println("*** SHOP ***");
                    shopMenuCommandsChecker(command);
                    break;
                case NEW_BATTLE:
                    System.out.println("*** NEW_BATTLE ***");
                    newBattleMenuCommandsChecker(command);
                    break;
                case BATTLE:
                    System.out.println("*** BATTLE ***");
                    battleMenuCommandsChecker(command);
                    break;
                case GRAVEYARD:
                    System.out.println("*** GRAVEYARD ***");
                    graveYardMenuCommandsChecker(command);
                    break;
            }
            if (menu.equals(Menu.EXIT)) {
                return;
            }
        }
    }

    public static void mainMenuCommandsChecker(String command) {
        if (command.matches(LOGOUT)) {
            AccountController.logout();
        } else if (command.matches(ENTER_COLLECTION)) {
            setMenu(Menu.COLLECTION);
        } else if (command.matches(ENTER_SHOP)) {
            setMenu(Menu.SHOP);
        } else if (command.matches(ENTER_BATTLE)) {
            setMenu(Menu.NEW_BATTLE);
        } else if (command.matches(EXIT)) {
            setMenu(Menu.ACCOUNT);
        } else if (command.matches(HELP)) {
            MainMenuView.printMainMenuCommandsToHelp();
        }
    }

    public static void collectionMenuCommandsChecker(String command) {
        String commandParts[] = command.split("\\s+");
        if (command.matches("create deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(commandParts[2]);
        } else if (command.matches("delete deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(commandParts[2]);
        } else if (command.matches("search .+")) {
            Matcher matcher = Pattern.compile("search (?<cardName>.+)").matcher(command);
            matcher.find();
            Account.getLoggedInAccount().getCollection().getCollectionController().search(matcher.group("cardName"));
        } else if (command.matches(SHOW)) {
            CollectionViews.showAllCardsInCollection();
        } else if (command.matches("save")) {
            //todo
        } else if (command.matches("add \\d+ to deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().addCard(commandParts[1], commandParts[4]);
        } else if (command.matches("validate deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().showDeckIsValidate(commandParts[2]);
        } else if (command.matches("remove \\w+ from deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().remove(commandParts[1], commandParts[4]);
        } else if (command.matches(HELP)) {
            CollectionViews.printShopCommandsToHelp();
        } else if (command.matches("select deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck(commandParts[2]);
        } else if (command.matches("show all decks")) {
            CollectionViews.showAllDecks();
        } else if (command.matches("show deck \\w+")) {
//            if (CollectionController.validateDeck(commandParts[2])) {
                CollectionViews.showDeck(Collection.getDeckByName(commandParts[2]));
//            } else {
//                ConsoleOutput.printErrorMessage(ErrorType.DECK_NOTFOUND);
//            }
        } else if (command.matches(EXIT)) {
            setMenu(Menu.MAIN);
        }
    }

    public static void accountMenuCommandsChecker(String command) {
        if (command.matches(CREATE_ACCOUNT)) {
            createAccountFormater(command);
        } else if (command.matches(LOGIN)) {
            loginFormater(command);
        } else if (command.matches(SHOW_LEADERBOARD)) {
            AccountController.showLeaderBoard();
        } else if (command.matches(HELP)) {
            AccountView.printAccountCommandsToHelp();
        } else if (command.matches(EXIT)) {
            setMenu(Menu.EXIT);
        }
    }

    public static void shopMenuCommandsChecker(String command) {
        if (command.matches(SHOW)) {
            ShopView.showAllCardsInShop();
        } else if (command.matches(EXIT)) {
            setMenu(Menu.MAIN);
        } else if (command.matches(SEARCH_CARD_NAME)) {
            searchCardNameFormater(command);
        } else if (command.matches(SHOW_COLLECTION)) {
            CollectionViews.showAllCardsInCollection();
        } else if (command.matches(SEARCH_COLLECTION_CARD_NAME)) {
            searchCollectionFormater(command, SEARCH_COLLECTION_CARD_NAME);
        } else if (command.matches(BUY_CARD_NAME)) {
            Matcher matcher = Pattern.compile(BUY_CARD_NAME).matcher(command);
            matcher.find();
            ShopController.buy(Account.getLoggedInAccount(), matcher.group("cardName"));
        } else if (command.matches(SELL_CARD)) {
            Matcher matcher = Pattern.compile(SELL_CARD).matcher(command);
            matcher.find();
            ShopController.sell(Account.getLoggedInAccount(), matcher.group("cardId"));
        } else if (command.matches(HELP)) {
            ShopView.printShopCommandsToHelp();
        }
    }

    private static void searchCollectionFormater(String command, String searchCollectionCardName) {
        Matcher matcher = Pattern.compile(searchCollectionCardName).matcher(command);
        matcher.find();
        Account.getLoggedInAccount().getCollection().getCollectionController().search(matcher.group("cardName"));
    }

    private static void searchCardNameFormater(String command) {
        Matcher matcher = Pattern.compile(SEARCH_CARD_NAME).matcher(command);
        matcher.find();
        ShopController.search(matcher.group("cardName"));
    }

    public static void battleMenuCommandsChecker(String command) {
        //Todo : Create New Game
        if (command.matches("game info")) {
            Battle.getPlayingBattle().getBattleView().printGameInfo();
        } else if (command.matches("show my minions")) {
            Battle.getPlayingBattle().getBattleController().showMySoldiers();
        } else if (command.matches("move to \\(\\d+, \\d+\\)")) {
            Matcher matcher = Pattern.compile("move to \\((?<x>\\d+), (?<y>\\d+)\\)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().move(
                    Integer.valueOf(matcher.group("x")),
                    Integer.valueOf(matcher.group("y"))
            );
        } else if (command.matches("show opponent minions")) {
            Battle.getPlayingBattle().getBattleController().showOpponentSoldiers();
        } else if (command.matches("show card info \\d+")) {
            Matcher matcher = Pattern.compile("sell (?<cardId>\\d+)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().showDeckCardInformation(matcher.group("cardId"));
        } else if (command.matches("attack \\d+")) {
            Matcher matcher = Pattern.compile("attack (?<cardId>\\d+)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().attack(
                    ((Soldier)Battle.getPlayingBattle().getBattleController().getCardByIdInBattle(matcher.group("cardId"))).getCell(),
                    false);
        } else if (command.matches("attack combo (\\d+)+")) {
            Matcher matcher = Pattern.compile("attack combo (?<opponentId>\\d+) (\\d+)+").matcher(command);
            String opponentCardId = matcher.group("opponentId");
            command = command.replace("attack combo \\d+ ", "");
            List<String> cardIds = Arrays.asList(command.split(" "));
            if (matcher.find()) {
                Battle.getPlayingBattle().getBattleController().attackCombo(
                        opponentCardId,
                        cardIds
                );
            }
        } else if (command.matches("use special power \\(\\d+, \\d+\\)")) {
            Matcher matcher = Pattern.compile("use special power \\((?<xCordinate>\\d+), (?<yCordinate>\\d+)\\)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().useSpecialPower(
                    Integer.valueOf(matcher.group("xCordinate")),
                    Integer.valueOf(matcher.group("yCordinate"))
            );
        } else if (command.matches("show hand")) {
            BattleView.showHand();
        } else if (command.matches("insert .+ in \\(\\d+, \\d+\\)")) {
            System.out.println("Checked");
            Matcher matcher = Pattern.compile("insert (?<cardName>.+) in \\((?<xCordinate>\\d+), (?<yCordinate>\\d+)\\)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().insertNewCardToMap(
                    Integer.parseInt(matcher.group("xCordinate")),
                    Integer.parseInt(matcher.group("yCordinate")),
                    matcher.group("cardName"));
        } else if (command.matches("end turn")) {
            Battle.getPlayingBattle().getBattleController().endTurn();
        } else if (command.matches("Show collectables")) {
            //todo
        } else if (command.matches("select \\d+")) {
            Matcher matcher = Pattern.compile("select (?<cardId>\\d+)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().selectCard(matcher.group("cardId"));
        } else if (command.matches("show info")) {//showing selected card info
            Card.showCardInformation(Battle.getPlayingBattle().getTurnToPlay().getSelectedCard());
        } else if (command.matches("use \\(\\d+, \\d+\\)")) {
            //todo
        } else if (command.matches("show next card")) {
            Battle.getPlayingBattle().getBattleController().showNextCardOfBattle();
        } else if (command.matches("enter graveyard")) {
            setMenu(Menu.GRAVEYARD);
        } else if (command.matches(HELP)) {
            BattleView.printBattleCommandsToHelp();
        } else if (command.matches("show map")) {
            ConsoleOutput.printMessage(Battle.getPlayingBattle().getMap().toString());
        } else if (command.matches(EXIT)) {
            setMenu(Menu.NEW_BATTLE);
        }
    }

    private static void graveYardMenuCommandsChecker(String command) {
        if (command.matches("show info \\d+")) {
            Matcher matcher = Pattern.compile("sell (?<cardId>\\d+)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().showCardFromGraveYardInformation(matcher.group("cardId"));
        } else if (command.matches("show cards")) {
            Battle.getPlayingBattle().getBattleController().showGraveYardCards();
        } else if (command.matches(EXIT)) {
            setMenu(Menu.BATTLE);
        }
    }

    private static void newBattleMenuCommandsChecker(String command) {
        if (command.matches("story \\d+")) {
            Matcher storyLevelMatcher = Pattern.compile("story (?<level>\\d+)").matcher(command);
            storyLevelMatcher.find();
            new Battle(
                    Integer.valueOf(storyLevelMatcher.group("level")),
                    0
            );
            setMenu(Menu.BATTLE);
        } else if (command.matches("story \\d+ \\d+")) {
            Matcher storyLevelMatcher = Pattern.compile("story (?<level>\\d+) (?<flags>\\d+)").matcher(command);
            storyLevelMatcher.find();
            new Battle(
                    Integer.valueOf(storyLevelMatcher.group("level")),
                    Integer.valueOf(storyLevelMatcher.group("flags"))
            );
            setMenu(Menu.BATTLE);
        } else if (command.matches("start multiplayer game \\S+ \\w+")) {
                Matcher multiPlayerMatcher = Pattern.compile("start multiplayer game (?<mode>\\S+) (?<opponent>\\w+)").matcher(command);
                multiPlayerMatcher.find();
                new Battle(
                        Enum.valueOf(Mode.class, multiPlayerMatcher.group("mode")),
                        Account.getAccountByUsername(multiPlayerMatcher.group("opponent")),
                        0
                );
                setMenu(Menu.BATTLE);
        } else if (command.matches("start multiplayer game \\S+ \\w+ \\d+")) {
            Matcher multiPlayerMatcher = Pattern.compile("start multiplayer game (?<mode>\\S+) (?<opponent>\\w+) (?<flags>\\d+)").matcher(command);
            multiPlayerMatcher.find();
            new Battle(
                    Enum.valueOf(Mode.class, multiPlayerMatcher.group("mode")),
                    Account.getAccountByUsername(multiPlayerMatcher.group("opponent")),
                    Integer.valueOf(multiPlayerMatcher.group("flags"))
            );
            setMenu(Menu.BATTLE);
        } else if (command.matches("start custom game \\w+ \\S+")) {
            Matcher multiPlayerMatcher = Pattern.compile("start custom game (?<deckName>\\w+) (?<mode>\\S+)").matcher(command);
            multiPlayerMatcher.find();
            Account.getLoggedInAccount().setMainDeck(Collection.getDeckByName(multiPlayerMatcher.group("deckName")));
            Mode mode = Enum.valueOf(Mode.class, multiPlayerMatcher.group("mode"));
            new Battle(
                    mode,
                    0
            );
            setMenu(Menu.BATTLE);
        } else if (command.matches("start custom game \\w+ \\S+ \\d+")) {
            Matcher multiPlayerMatcher = Pattern.compile("start custom game (?<deckName>\\w+) (?<mode>\\S+) (?<flags>\\d+)").matcher(command);
            multiPlayerMatcher.find();
            Account.getLoggedInAccount().setMainDeck(Collection.getDeckByName(multiPlayerMatcher.group("deckName")));
            Mode mode = Enum.valueOf(Mode.class, multiPlayerMatcher.group("mode"));
            new Battle(
                    mode,
                    Integer.valueOf(multiPlayerMatcher.group("flags"))
            );
            setMenu(Menu.BATTLE);
        } else if (command.matches(EXIT)) {
            setMenu(Menu.MAIN);
        }
    }

    public static void getCordinatesForUseSpecialPowerOnSpawn(){
        System.out.println("please enter (x, y) to use minion special power");
        battleMenuCommandsChecker("use special power " + scanner.nextLine());
    }


    private static void loginFormater(String command) {
        Matcher usernameMatcher = Pattern.compile(LOGIN).matcher(command);
        if (usernameMatcher.find()) {
            command = scanner.nextLine();
            Matcher passwordMatcher = Pattern.compile(PASSWORD).matcher(command);
            passwordMatcher.find();
//            AccountController.loginAccount(usernameMatcher.group("username"), passwordMatcher.group("password"));
        }
    }

    private static void createAccountFormater(String command) {
        Matcher usernameMatcher = Pattern.compile(CREATE_ACCOUNT).matcher(command);
        if (usernameMatcher.find()) {
            command = scanner.nextLine();
            Matcher passwordMatcher = Pattern.compile(PASSWORD).matcher(command);
            passwordMatcher.find();
            AccountController.createAccount(usernameMatcher.group("username"), passwordMatcher.group("password"));
        }
    }

}
