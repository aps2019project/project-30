package com.company.Views;

import com.company.Controllers.AccountController;
import com.company.Controllers.BattleController;
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
            setMenu(Menu.ACCOUNT);
        } else if (command.matches("help")) {
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
//            JsonSerializer jsonDeckSerializer = new JsonSerializer<Deck>() {
//                @Override
//                public JsonElement serialize(Deck deck, Type type, JsonSerializationContext jsonSerializationContext) {
//                    JsonElement jsonElement = jsonSerializationContext.serialize(deck);
//                    jsonElement.getAsJsonObject().remove("hand");
//                    jsonElement.getAsJsonObject().remove("deckController");
//                    return jsonElement;
//                }
//            };
//            JsonDeserializer jsonBuffDeserializer = new JsonDeserializer<Buff>() {
//                @Override
//                public Buff deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                    JsonObject jsonObject = jsonElement.getAsJsonObject();
//                    try {
//                        return jsonDeserializationContext.deserialize(jsonElement, Class.forName(jsonObject.get("className").getAsString()));
//                    } catch (ClassNotFoundException e) {
//                        System.out.println(e.getException().getMessage());
//                        return null;
//                    }
//                }
//            };
//
//            JsonSerializer jsonBuffSerializer = new JsonSerializer<Buff>() {
//                @Override
//                public JsonElement serialize(Buff buff, Type type, JsonSerializationContext jsonSerializationContext) {
//                    JsonElement jsonElement = jsonSerializationContext.serialize(buff);
//                    jsonElement.getAsJsonObject().addProperty("className", buff.getClass().getName());
//                    return jsonElement;
//                }
//            };
//
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder
//                    .registerTypeAdapter(Deck.class, jsonDeckSerializer)
//                    .registerTypeAdapter(Buff.class, jsonBuffDeserializer)
//                    .registerTypeAdapter(Buff.class, jsonBuffSerializer);
//            Gson customGson = gsonBuilder.create();
//            System.out.println("JsonController.getGson().toJson(Collection.getDeckByName(commandParts[2])) = " + customGson.toJson(Collection.getDeckByName(commandParts[2])));
        } else if (command.matches("show all decks")) {
            CollectionViews.showAllDecks();
        } else if (command.matches("show deck \\w+")) {
            if (CollectionController.validateDeck(commandParts[2])) {
                CollectionViews.showDeck(Collection.getDeckByName(commandParts[2]));
            } else {
                ConsoleOutput.printErrorMessage(ErrorType.DECK_NOTFOUND);
            }
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
            ShopController.sell(Account.getLoggedInAccount(), matcher.group("cardId"));
        } else if (command.matches("help")) {
            ShopView.printShopCommandsToHelp();
        }
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
                    ((Soldier)Battle.getPlayingBattle().getBattleController().getCardById(matcher.group("cardId"))).getCell(),
                    false);
        } else if (command.matches("attack combo (\\d+)+")) {
            //todo
        } else if (command.matches("use special power \\(\\d+, \\d+\\)")) {

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
            BattleView.showCardInformation(Battle.getPlayingBattle().getTurnToPlay().getSelectedCard());
        } else if (command.matches("use \\(\\d+, \\d+\\)")) {
            //todo
        } else if (command.matches("show next card")) {
            //todo
        } else if (command.matches("enter graveyard")) {
            setMenu(Menu.GRAVEYARD);
        } else if (command.matches("help")) {
            BattleView.printBattleCommandsToHelp();
        }else if (command.matches("show map")) {
            ConsoleOutput.printMessage(Battle.getPlayingBattle().getMap().toString());
        }
    }


    private static void graveYardMenuCommandsChecker(String command) {
        if (command.matches("show info \\d+")) {
            Matcher matcher = Pattern.compile("sell (?<cardId>\\d+)").matcher(command);
            matcher.find();
            Battle.getPlayingBattle().getBattleController().showCardFromGraveYardInformation(matcher.group("cardId"));
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
        } else {
            if (command.matches("start multiplayer game \\S+ \\w+")) {
                Matcher multiPlayerMatcher = Pattern.compile("start multiplayer game (?<mode>\\S+) (?<opponent>\\w+)").matcher(command);
                multiPlayerMatcher.find();
                new Battle(
                        Enum.valueOf(Mode.class, multiPlayerMatcher.group("mode")),
                        Account.getAccountByUsername(multiPlayerMatcher.group("opponent"))
                );
                setMenu(Menu.BATTLE);
            }
        }
    }
}
