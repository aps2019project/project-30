package com.company.Views;

import com.company.Controllers.AccountController;
import com.company.Controllers.CollectionController;
import com.company.Controllers.JsonController;
import com.company.Controllers.ShopController;
import com.company.Models.Battle.Battle;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;
import com.company.Views.Console.CollectionViews;

import javax.management.BadAttributeValueExpException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInput {
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
                    mainMenuCommandsChecker(command);
                    break;
                case ACCOUNT:
                    accountMenuCommandsChecker(command);
                    break;
                case COLLECTION:
                    collectionMenuCommandsChecker(command);
                    break;
                case SHOP:
                    shopMenuCommandsChecker(command);
                    break;
                case NEW_BATTLE:
                    newBattleMenuCommandsChecker(command);
                    break;
                case BATTLE:
                    battleMenuCommandsChecker(command);
                    break;
                case GRAVEYARD:
                    graveYardMenuCommandsChecker(command);
                    break;
            }
            if (menu.equals(Menu.EXIT)) {
                return;
            }
        }
    }

    public static void mainMenuCommandsChecker(String command) {
        if (command.matches("save")) {
            //todo
        } else if (command.matches("logout")) {
            AccountController.logout();
        } else if (command.matches("enter collection")) {
            setMenu(Menu.COLLECTION);
        } else if (command.matches("enter shop")) {
            setMenu(Menu.SHOP);
        } else if (command.matches("enter battle")) {
            setMenu(Menu.NEW_BATTLE);
        } else if (command.matches("exit")) {
            setMenu(Menu.EXIT);
        } else if (command.matches("help")) {
            MainMenuView.printMainMenuCommandsToHelp();
        }
    }

    public static void collectionMenuCommandsChecker(String command) {
        String commandParts[] = command.split("\\s+");
        if (command.matches("create deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(commandParts[2]);
//            System.out.println("JsonController.getGson().toJson(Collection.getDeckByName(commandParts[2])) = " + JsonController.getGson().toJson(Collection.getDeckByName(commandParts[2])));
        } else if (command.matches("delete deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(commandParts[2]);
        } else if (command.matches("search .+")) {
            Matcher matcher = Pattern.compile("search (?<cardName>.+)").matcher(command);
            matcher.find();
            Account.getLoggedInAccount().getCollection().getCollectionController().search(matcher.group("cardName"));
        } else if (command.matches("show")) {
            CollectionViews.showAllCardsInCollection();
        } else if (command.matches("save")) {
            //todo
        } else if (command.matches("add \\d+ to deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().addCard(commandParts[1], commandParts[4]);
        } else if (command.matches("validate deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().showDeckIsValidate(commandParts[2]);
        } else if (command.matches("remove \\w+ from deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().remove(commandParts[1], commandParts[4]);
        } else if (command.matches("help")) {
            CollectionViews.printShopCommandsToHelp();
        } else if (command.matches("select deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck(commandParts[2]);
        } else if (command.matches("show all decks")) {
            CollectionViews.showAllDecks();
        } else if (command.matches("show deck \\w+")) {
            //Todo : MohammadHosein : Check Validations
            CollectionViews.showDeck(Collection.getDeckByName(commandParts[2]));
        } else if (command.matches("exit")) {
            setMenu(Menu.MAIN);
        }
    }

    public static void accountMenuCommandsChecker(String command) {
        if (command.matches("create account \\w+")) {
            Matcher usernameMatcher = Pattern.compile("create account (?<username>\\w+)").matcher(command);
            if (usernameMatcher.find()) {
                command = scanner.nextLine();
                Matcher passwordMatcher = Pattern.compile("(?<password>\\w+)").matcher(command);
                passwordMatcher.find();
                AccountController.createAccount(usernameMatcher.group("username"), passwordMatcher.group("password"));
            }
        } else if (command.matches("login \\w+")) {
            Matcher usernameMatcher = Pattern.compile("login (?<username>\\w+)").matcher(command);
            if (usernameMatcher.find()) {
                command = scanner.nextLine();
                Matcher passwordMatcher = Pattern.compile("(?<password>\\w+)").matcher(command);
                passwordMatcher.find();
                AccountController.loginAccount(usernameMatcher.group("username"), passwordMatcher.group("password"));
            }
        } else if (command.matches("show leaderboard")) {
            AccountController.showLeaderBoard();
        } else if (command.matches("help")) {
            AccountView.printAccountCommandsToHelp();
        } else if (command.matches("exit")) {
            setMenu(Menu.EXIT);
        }
    }

    public static void shopMenuCommandsChecker(String command) {
        if (command.matches("show")) {
            ShopView.showAllCardsInShop();
        } else if (command.matches("exit")) {
            setMenu(Menu.MAIN);
        } else if (command.matches("search .+")) {
            Matcher matcher = Pattern.compile("search (?<cardName>.+)").matcher(command);
            matcher.find();
            ShopController.search(matcher.group("cardName"));
        } else if (command.matches("show collection")) {
            CollectionViews.showAllCardsInCollection();
        } else if (command.matches("search collection")) {
            Matcher matcher = Pattern.compile("search collection (?<cardName>.+)").matcher(command);
            matcher.find();
            Account.getLoggedInAccount().getCollection().getCollectionController().search(matcher.group("cardName"));
        } else if (command.matches("buy .+")) {
            Matcher matcher = Pattern.compile("buy (?<cardName>.+)").matcher(command);
            matcher.find();
            ShopController.buy(Account.getLoggedInAccount(), matcher.group("cardName"));
        } else if (command.matches("sell \\d+")) {
            Matcher matcher = Pattern.compile("sell (?<cardId>\\d+)").matcher(command);
            matcher.find();
            ShopController.sell(Account.getLoggedInAccount(),matcher.group("cardId"));
        } else if (command.matches("help")) {
            ShopView.printShopCommandsToHelp();
        }
    }

    public static void battleMenuCommandsChecker(String command) {
        //Todo : Create New Game
        if (command.matches("Game info")) {
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
            //todo
        } else if (command.matches("select \\d+")) {
            //todo
        } else if (command.matches("attack \\d+")) {
            //todo
        } else if (command.matches("attack combo (\\d+)+")) {
            //todo
        } else if (command.matches("use special power \\(\\d+, \\d+\\)")) {
            //todo
        } else if (command.matches("show hand")) {
            //todo
        } else if (command.matches("insert \\[a-zA-Z]+ in \\(\\d+, \\d+\\)")) {
            Matcher matcher = Pattern.compile("insert (?<cardName>[a-zA-Z]+) in \\((?<xCordinate>\\d+)\\,(?<yCordinate>\\d+)\\)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().insertNewCardToMap(Integer.parseInt(matcher.group("xCordinate")), Integer.parseInt(matcher.group("yCordinate")), matcher.group("cardName"));
        } else if (command.matches("end turn")) {
            //todo
        } else if (command.matches("Show collectables")) {
            //todo
        } else if (command.matches("select \\d+")) {
            //todo
        } else if (command.matches("show info \\d+")) {
            //todo
        } else if (command.matches("use \\(\\d+, \\d+\\)")) {
            //todo
        } else if (command.matches("show next card")) {
            //todo
        } else if (command.matches("enter graveyard")) {
            setMenu(Menu.GRAVEYARD);
        } else if (command.matches("help")) {
            //todo
        }
    }


    private static void graveYardMenuCommandsChecker(String command) {
        if (command.matches("show info \\d+")) {
            //todo
        } else if (command.matches("show cards")) {
            Battle.getPlayingBattle().getBattleController().showGraveYardCards();
        } else if (command.matches("exit")) {
            setMenu(Menu.BATTLE);
        }
    }

    private static void newBattleMenuCommandsChecker(String command) {
        if (command.matches("story \\d+")) {
            Matcher storyLevelMatcher = Pattern.compile("story (?<level>\\d+)").matcher(command);
            storyLevelMatcher.find();
            new Battle(
                    Integer.valueOf(storyLevelMatcher.group("level"))
            );
            setMenu(Menu.BATTLE);
        }
    }
}
