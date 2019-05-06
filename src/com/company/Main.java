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

        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 1, 4);
//        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);


        DisarmBuff disarmBuff = new DisarmBuff(null, 1, 0, 0);
        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);
        PosionBuff posionBuff = new PosionBuff(null, 3, 0, 1);
        HolyBuff holyBuff = new HolyBuff(null, 3, 0, 1);
        DispelBuff dispelBuff = new DispelBuff(null, 1, 0, 0);
        HolyBuff holyBuff1 = new HolyBuff(null, 1000, 0, 3);
        Hero hero = new Hero();
        hero.setName("Rostam");
        hero.setPriceInDrake(8000);
        hero.setFullHealth(55);
        hero.setAttackPower(7);
        hero.setAttackType(AttackType.HYBRID);
        hero.setAreaOfEffect(4);
//        hero.getBuffsToCast().add(dispelBuff);
//        hero.getBuffsCasted().add(holyBuff1);
        hero.setManaPoint(0);
        hero.setCoolDown(0);
        hero.setTargetType(null);
        //todo : cell Targettype
//
////        System.out.println("gson.toJson(hero) = " + gson.toJson(hero));
//
//        System.out.println("JsonController.getGson().toJson(hero) = " + JsonController.getGson().toJson(hero));
//
////        Gson gson = new Gson();


        //HealthPowerBuff healthPowerBuff = new HealthPowerBuff(null, 1, 0, 10);
//        Minion minion = new Minion();
//        minion.setName("Arjang div");
//        minion.setPriceInDrake(600);
//        minion.setManaPoint(3);
//        minion.setFullHealth(6);
//        minion.setAttackPower(6);
//        minion.setAttackType(AttackType.MELEE);
//        //minion.getBuffsToCast().add(healthPowerBuff);
//        minion.setActivationTime(ActivationTime.COMBO);
////
//
//        ManaBuff manaBuff = new ManaBuff(null, 3, 0, 1);
//        Item item = new Item();
//        item.setName("Taj Danayi");
//        item.setPriceInDrake(300);
//        item.getBuffsToCast().add(manaBuff);
//        item.setDescription("ManaBuff : 3 Rounds - 1 Mana");
//        item.setTargetType(TargetType.FRIEND_HERO);

//        StunBuff stunBuff= new StunBuff(null, 2, 0, 0);
//        Spell spell = new Spell();
//        spell.setName("Shock");
//        spell.setManaPoint(1);
//        spell.setPriceInDrake(1200);
//        spell.setTargetType(TargetType.ENEMY_SOLDIER);
//        spell.setInGraveCards(false);
//
//        spell.getBuffsToCast().add(stunBuff);



        System.out.println(JsonController.getGson().toJson(hero));
    }
}
