package com.company;

import com.company.Controllers.JsonController;
import com.company.Controllers.ShopController;
import com.company.Models.Buff.*;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.TargetType;
import com.company.Views.ConsoleInput;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        for (Hero hero : JsonController.getHeroes()) {
//            System.out.println(hero.getName());
//        }
        ShopController.initialize();
        ConsoleInput.menusHandler();

        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000,1, 4);
        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);

        Hero hero = new Hero();
        hero.setName("Simorgh");

        hero.setPriceInDrake(9000);
        hero.setFullHealth(50);
        hero.setAttackPower(4);
        hero.setAttackType(AttackType.MELEE);
        hero.getBuffsToCast().add(stunBuff);
        hero.setManaPoint(5);
        hero.setCoolDown(8);
        System.out.println("JsonController.getGson().toJson(hero) = " + JsonController.getGson().toJson(hero));
    }
}
