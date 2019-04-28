package com.company.Views;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
import com.company.Controllers.ShopController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Modes.CaptureTheFlag;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;

import java.nio.channels.AcceptPendingException;
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
        } else if (command.matches("enter Battle")) {
            setMenu(Menu.COLLECTION);
        } else if (command.matches("exit")) {
            setMenu(Menu.EXIT);
        } else if (command.matches("help")) {
            //todo
        }
    }

    public static void collectionMenuCommandsChecker(String command) {
        String strings[] = command.split("\\s+");
        if (command.matches("create deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(strings[2]);
        } else if (command.matches("delete deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(strings[2]);
        } else if (command.matches("search \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(strings[1]);
        } else if (command.matches("show")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().show();
        } else if (command.matches("save")) {
            //save
            //todo
        } else if (command.matches("add \\w+ to deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().addCard(Integer.parseInt(strings[1]), strings[4]);
        } else if (command.matches("validate deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().validateDeck(strings[2]);
        } else if (command.matches("remove \\w+ from deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().remove(Integer.parseInt(strings[1]), strings[4]);
        } else if (command.matches("help")) {
            //help
            //todo
        } else if (command.matches("select deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck(strings[2]);
        } else if (command.matches("show all decks")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().showAllDecks();
        } else if (command.matches("show deck \\w+")) {
            Account.getLoggedInAccount().getCollection().getCollectionController().showDeck(strings[2]);
        } else if (command.matches("exit")) {
            //exit
            //todo
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
            ShopView.showAll();
        } else if (command.matches("exit")) {
            //exit
        } else if (command.matches("search [a-zA-Z]+")) {
            Matcher matcher = Pattern.compile("search (?<cardName>[a-zA-Z]+)").matcher(command);
            matcher.find();
            ShopController.search(matcher.group("cardName"));
        } else if (command.matches("show collection")) {
            //show collection
        } else if (command.matches("search collection")) {
            //search collection
        } else if (command.matches("buy [a-zA-Z]+")) {
            Matcher matcher = Pattern.compile("search (?<cardName>[a-zA-Z]+)").matcher(command);
            matcher.find();
            ShopController.buy(Account.getLoggedInAccount(), matcher.group("cardName"));
        } else if (command.matches("sell \\d+")) {
            Matcher matcher = Pattern.compile("search (?<cardId>\\d+)").matcher(command);
            matcher.find();
            ShopController.sell(Account.getLoggedInAccount(), Integer.parseInt(matcher.group("cardId")));
        } else if (command.matches("help")) {
            ShopView.printShopCommandsToHelp();
        }
    }

    public static void battleMenuCommandsChecker(String command) {
        //Todo : Create New Game
        if (command.matches("game info")) {
            //todo
        } else if (command.matches("show my minions")) {
            //todo
        } else if (command.matches("move to (\\d+,\\d+)")) {
            //todo
        } else if (command.matches("show opponent minions")) {
            //todo
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
