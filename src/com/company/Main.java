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
//        ShopController.initialize();
//        ConsoleInput.menusHandler();

//        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 1, 4);
//        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);
//
///*
//
//
//        Hero hero = new Hero();
//        hero.setName("Simorgh");
//
//        hero.setPriceInDrake(9000);
//        hero.setFullHealth(50);
//        hero.setAttackPower(4);
//        hero.setAttackType(AttackType.MELEE);
//        hero.getBuffsToCast().add(stunBuff);
//        hero.setManaPoint(5);
//        hero.setCoolDown(8);
//
////        System.out.println("gson.toJson(hero) = " + gson.toJson(hero));
//
//        System.out.println("JsonController.getGson().toJson(hero) = " + JsonController.getGson().toJson(hero));*/
//
//
//
//
//        Minion minion = new Minion();
//        minion.setName("Shamshirzan Fars");
//        minion.setPriceInDrake(400);
//        minion.setManaPoint(2);
//        minion.setFullHealth(6);
//        minion.setAttackPower(4);
//        minion.setAttackType(AttackType.MELEE);
//        minion.getBuffsToCast().add(stunBuff);
//        minion.setActivationTime(ActivationTime.ON_ATTACK);
//
//
//
//        ManaBuff manaBuff = new ManaBuff(null, 3, 0, 1);
//        Item item = new Item();
//        item.setName("Taj Danayi");
//        item.setPriceInDrake(300);
//        item.getBuffsToCast().add(manaBuff);
//        item.setDescription("ManaBuff : 3 Rounds - 1 Mana");
//        item.setTargetType(TargetType.FRIEND_HERO);
//
//




        PosionBuff posionBuff= new PosionBuff(null, 4, 0, 1);
        Spell spell = new Spell();
        spell.setName("All Poisen");
        spell.setManaPoint(8);
        spell.setPriceInDrake(1500);
        spell.setTargetType(TargetType.WHOLE_ENEMY);
        spell.setInGraveCards(false);
        spell.getBuffsToCast().add(posionBuff);


        System.out.println(JsonController.getGson().toJson(spell));


    }
}
