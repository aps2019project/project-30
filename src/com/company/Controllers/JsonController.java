package com.company.Controllers;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
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
                System.out.println(e.getException().getMessage());
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

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Buff.class, new BuffDeserializer())
                .registerTypeAdapter(Buff.class, new BuffSerializer());
        return gsonBuilder.create();
    }

    public static List<Spell> getSpells() {
        try (FileReader reader = new FileReader("data/Spells.json")) {
            Type spellListType = new TypeToken<ArrayList<Spell>>() {
            }.getType();
            return getGson().fromJson(reader, spellListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Minion> getMinions() {
        try (FileReader reader = new FileReader("data/Minions.json")) {
            Type minionListType = new TypeToken<ArrayList<Minion>>() {
            }.getType();
            return getGson().fromJson(reader, minionListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Hero> getHeroes() {
        try (FileReader reader = new FileReader("data/Heroes.json")) {
            Type heroListType = new TypeToken<ArrayList<Hero>>() {
            }.getType();
            return getGson().fromJson(reader, heroListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Item> getItems() {
        try (FileReader reader = new FileReader("data/Items.json")) {
            Type itemListType = new TypeToken<ArrayList<Item>>() {
            }.getType();
            return getGson().fromJson(reader, itemListType);
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
            accounts.removeIf(account -> (account == null));
            return accounts;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Account getLoggedInAccounts() {
        try (FileReader reader = new FileReader(Account.getLoggedInAccountsFilePath())) {
            Type accountListType = new TypeToken<ArrayList<Account>>() {
            }.getType();
            List<Account> accounts = getGson().fromJson(reader, accountListType);
            return accounts.get(0);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void writeAllAccountsOnFile(String destinationAddress) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destinationAddress, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.print('[');
        for (Account account : Account.getAccounts()) {
            printStream.print(JsonController.getGson().toJson(account));
            printStream.print(',');
        }
        printStream.print(']');
        printStream.flush();
        printStream.close();
    }

    public static void writeLoggedInAccountOnFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Account.getLoggedInAccountsFilePath());
            PrintStream printStream = new PrintStream(fileOutputStream);
            Account account = new Account("ali","1");
            printStream.print('[');
            printStream.print(JsonController.getGson().toJson(account));
            printStream.print(']');
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
