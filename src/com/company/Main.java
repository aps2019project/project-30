package com.company;

import com.company.Controllers.JsonController;
import com.company.Controllers.ShopController;
import com.company.Models.Buff.*;
import com.company.Models.Card.AttackType;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.ActivationTime;
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
        ShopController.initialize();
        ConsoleInput.menusHandler();

        //AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 1, 4);
//        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);


//
//        Hero hero = new Hero();
//        hero.setName("Simorgh");
//
//        hero.setPriceInDrake(9000);
//        hero.setFullHealth(50);
//        hero.setAttackPower(4);
//        hero.setAttackType(AttackType.MELEE);
////        hero.getBuffsToCast().add(stunBuff);
//        hero.setManaPoint(5);
//        hero.setCoolDown(8);
//        hero.setTargetType(TargetType.FRIEND_HERO);
//
////        System.out.println("gson.toJson(hero) = " + gson.toJson(hero));
//
//        System.out.println("JsonController.getGson().toJson(hero) = " + JsonController.getGson().toJson(hero));
//
////        Gson gson = new Gson();

        StunBuff buff = new StunBuff(null,1, 0, 0);
        Minion minion = new Minion();
        minion.setName("Nane Sarma");
        minion.setPriceInDrake(500);
        minion.setManaPoint(3);
        minion.setFullHealth(3);
        minion.setAttackPower(4);
        minion.setAttackType(AttackType.RANGED);
        minion.getBuffsToCast().add(buff);
        minion.setActivationTime(ActivationTime.COMBO);
//
//
/*        HealthPowerBuff buff = new HealthPowerBuff(null,1000,0,6);
        Item item = new Item();
        item.setName("Nooshdaroo");
        item.setPriceInDrake(30000);
        item.getBuffsToCast().add(buff);
        item.setDescription("increase health 6 units");
        item.setTargetType(TargetType.FRIEND_HERO);
*/
        System.out.println(JsonController.getGson().toJson(minion));
    }
}
