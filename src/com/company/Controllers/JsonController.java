package com.company.Controllers;

import com.company.Models.Battle.Battle;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.User.Account;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonController {
    public static class BuffDeserializer implements JsonDeserializer<Buff> {
        @Override
        public Buff deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            try {
                return jsonDeserializationContext.deserialize(jsonElement, Class.forName(jsonObject.get("className").getAsString()));
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
    }

    public static class CardDeserializer implements JsonDeserializer<Card> {

        @Override
        public Card deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            try {
                return jsonDeserializationContext.deserialize(jsonElement, Class.forName(jsonObject.get("className").getAsString()));
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
    }


    public static class BuffSerializer implements JsonSerializer<Buff> {
        @Override
        public JsonElement serialize(Buff buff, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonElement jsonElement = jsonSerializationContext.serialize(buff);
            jsonElement.getAsJsonObject().addProperty("className", buff.getClass().getName());
            return jsonElement;
        }
    }

    public static class CardSerializer implements JsonSerializer<Card> {
        @Override
        public JsonElement serialize(Card card, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonElement jsonElement = jsonSerializationContext.serialize(card);
            jsonElement.getAsJsonObject().addProperty("className", card.getClass().getName());
            return jsonElement;
        }
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Buff.class, new BuffDeserializer())
                .registerTypeAdapter(Buff.class, new BuffSerializer())
                .registerTypeAdapter(Card.class, new CardSerializer())
                .registerTypeAdapter(Card.class, new CardDeserializer());
        return gsonBuilder.create();
    }

    public static List<Spell> getSpells() {
        try (FileReader reader = new FileReader(Spell.getSpellsJsonFilePath())) {
            Type spellListType = new TypeToken<ArrayList<Spell>>() {
            }.getType();
            return getGson().fromJson(reader, spellListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Minion> getMinions() {
        try (FileReader reader = new FileReader(Minion.getMinionsJsonFilePath())) {
            Type minionListType = new TypeToken<ArrayList<Minion>>() {
            }.getType();
            return getGson().fromJson(reader, minionListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Hero> getHeroes() {
        try (FileReader reader = new FileReader(Hero.getHeroesJsonFilePath())) {
            Type heroListType = new TypeToken<ArrayList<Hero>>() {
            }.getType();
            return getGson().fromJson(reader, heroListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Item> getItems() {
        try (FileReader reader = new FileReader(Item.getItemsJsonFilePath())) {
            Type itemListType = new TypeToken<ArrayList<Item>>() {
            }.getType();
            return getGson().fromJson(reader, itemListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Battle> getSavedGames() {
        try (FileReader reader = new FileReader(Battle.getSavedGamesFilePath())) {
            Type savedGamesType = new TypeToken<ArrayList<Battle>>() {
            }.getType();
            return getGson().fromJson(reader, savedGamesType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Account> getAccounts() {
        try (FileReader reader = new FileReader(Account.getSavedAccountsFilePath())) {
            Type accountListType = new TypeToken<ArrayList<Account>>() {
            }.getType();
            List<Account> accounts = getGson().fromJson(reader, accountListType);
            for (Account account : accounts) {
                for (Deck deck : account.getDecks()) {
                    deck.setDeckController();
                    deck.setHand();
                }
            }
            return accounts;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Account getLoggedInAccounts() {
        try (FileReader reader = new FileReader(Account.getLoggedInAccountsFilePath())) {
            Account account = getGson().fromJson(reader, Account.class);
            for (Deck deck : account.getDecks()) {
                deck.setDeckController();
                deck.setHand();
            }
            return account;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static List<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.addAll(getSpells());
        cards.addAll(getMinions());
        cards.addAll(getHeroes());
        cards.addAll(getItems());
        return cards;
    }

    public static void writeAllAccountsOnFile() {
        try (FileWriter fileWriter = new FileWriter(Account.getSavedAccountsFilePath())) {
            fileWriter.write(getGson().toJson(Account.getAccounts()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLoggedInAccountOnFile() {
        try (FileWriter fileWriter = new FileWriter(Account.getLoggedInAccountsFilePath())) {
            fileWriter.write(getGson().toJson(Account.getLoggedInAccount()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAllSavedGamesOnFile() {
        try (FileWriter fileWriter = new FileWriter(Battle.getSavedGamesFilePath())) {
            fileWriter.write(getGson().toJson(Battle.getSavedBattles()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeMinionsOnFile(List<Minion> minions) {
        try (FileWriter fileWriter = new FileWriter(Minion.getMinionsJsonFilePath())) {
            fileWriter.write(getGson().toJson(minions));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHeroesOnFile(List<Hero> heroes) {
        try (FileWriter fileWriter = new FileWriter(Hero.getHeroesJsonFilePath())) {
            fileWriter.write(getGson().toJson(heroes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportDeck(Deck deck, String address) {
        try (FileWriter fileWriter = new FileWriter(address + "\\deck.json")) {
            fileWriter.write(getGson().toJson(deck));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Deck importDeck(String address) {
        try (FileReader fileReader = new FileReader(address)) {
            return getGson().fromJson(fileReader, Deck.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean removeFile(String fileAddress) {
        File file = new File(fileAddress);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

}
