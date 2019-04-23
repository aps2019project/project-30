package com.company;

import com.company.Models.Buff.Buff;
import com.company.Models.Buff.HolyBuff;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.TargetType;
import com.company.Views.ConsoleInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

    public static void main(String[] args) {
//        ConsoleInput.menusHandler();
        Buff holyBuff = new HolyBuff(null,1,0,1);
        Spell spell = new Spell();
        spell.setName("Total Disarm");
        spell.setPriceInDrake(1000);
        spell.setManaPoint(0);
        spell.getBuffsToCast().add(holyBuff);
        spell.setInGraveCards(false);
        spell.setTargetType(TargetType.ATTACKTYPE_MELEE);
        Gson gson = new Gson();
        System.out.println("gson.toJson(spell) = " + gson.toJson(spell));
        String json = "{\n" +
                "  \"id\": 0,\n" +
                "  \"name\": \"Total Disarm\",\n" +
                "  \"manaPoint\": 0,\n" +
                "  \"priceInDrake\": 1000,\n" +
                "  \"isInGraveCards\": false,\n" +
                "  \"targetType\": \"ATTACKTYPE_MELEE\",\n" +
                "  \"buffsToCast\": [\n" +
                "    {\n" +
                "      \"type\": \"POSSETIVE\",\n" +
                "      \"name\": \"HOLY\",\n" +
                "      \"remTurnToBeInactive\": 1,\n" +
                "      \"remTurnToCast\": 0,\n" +
                "      \"value\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"buffsCasted\": []\n" +
                "}";
        Spell spellFromJsonStr = gson.fromJson(json, Spell.class);
        System.out.println("spellFromJsonStr.getBuffsToCast().get(0).getName() = " + spellFromJsonStr.getBuffsToCast().get(0).getName());
    }
}
