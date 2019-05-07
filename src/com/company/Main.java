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
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //ShopController.initialize();
        //ConsoleInput.menusHandler();

        //AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 1, 4);
//        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);

        StunBuff stunBuff = new StunBuff(null, 1, 0, 0);
        HealthWeaknessBuff healthWeaknessBuff = new HealthWeaknessBuff(null, 1, 0, 100);
        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1, 0, 2);
        AttackPowerBuff attacBuff = new AttackPowerBuff(null, 1, 0, 4);
        AntiBuff antiBuff1 = new AntiBuff(Buff.Name.POSION, 1000, 0, 0);
        AntiBuff antiBuff2 = new AntiBuff(Buff.Name.DISARM, 1000, 0, 0);
        AntiBuff antiBuff3 = new AntiBuff(Buff.Name.MANA, 1000, 0, 0);
        AntiBuff antiBuff4 = new AntiBuff(Buff.Name.STUN, 1000, 0, 0);
        AntiBuff antiBuff5 = new AntiBuff(Buff.Name.DISPELL, 1000, 0, 0);
        AntiBuff antiBuff6 = new AntiBuff(Buff.Name.ATTACK_WEAKNESS, 1000, 0, 0);
        AntiBuff antiBuff7 = new AntiBuff(Buff.Name.ATTACK_POWER, 1000, 0, 0);
        AntiBuff antiBuff8 = new AntiBuff(Buff.Name.HEALTH_WEAKNESS, 1000, 0, 0);
        AntiBuff antiBuff9 = new AntiBuff(Buff.Name.HEALTH_POWER, 1000, 0, 0);
        AntiBuff antiBuff10 = new AntiBuff(Buff.Name.HOLY, 1000, 0, 0);
        DispelBuff dispelBuff = new DispelBuff(null, 1, 0, 0);
//        Spell spell = new Spell();
//        spell.setName("Area Dispel");
//        spell.setPriceInDrake(1500);
//        spell.setManaPoint(2);
//        spell.getBuffsToCast().add(dispelBuff);
//        spell.setTargetType(TargetType.SQUARE_2);


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

        Minion minion = new Minion();
        minion.setName("Giv");
        minion.setPriceInDrake(450);
        minion.setManaPoint(4);
        minion.setFullHealth(5);
        minion.setAttackPower(7);
        minion.setAttackType(AttackType.RANGED);
        minion.getBuffsCasted().add(antiBuff1);
        minion.getBuffsCasted().add(antiBuff2);
        minion.getBuffsCasted().add(antiBuff3);
        minion.getBuffsCasted().add(antiBuff4);
        minion.getBuffsCasted().add(antiBuff5);
        minion.getBuffsCasted().add(antiBuff6);
        minion.getBuffsCasted().add(antiBuff7);
        minion.getBuffsCasted().add(antiBuff8);
        minion.getBuffsCasted().add(antiBuff9);
        minion.getBuffsCasted().add(antiBuff10);
        minion.setActivationTime(ActivationTime.PASSIVE);
        minion.setTargetType(TargetType.SELF);
//
//
/*        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 0, 6);
//        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000, 0, 1);
        HealthPowerBuff healthPowerBuff = new HealthPowerBuff(null, 1000, 0, 2);
        AttackWeaknessBuff attackWeaknessBuff = new AttackWeaknessBuff(null, 1000, 0, 2);
        HolyBuff holyBuff = new HolyBuff(null, 2, 0, 10);
        ManaBuff manaBuff = new ManaBuff(null, 1000, 0, 1);
        HealthWeaknessBuff healthWeaknessBuff = new HealthWeaknessBuff(null, 1, 0, 8);
//        ManaBuff manaBuff = new ManaBuff(null, 1, 1, 3);
//        HolyBuff holyBuff = new HolyBuff(null,2,0,0);
        Item item = new Item();
        item.setName("King Wisdom");
        item.setPriceInDrake(9000);
//        item.getBuffsToCast().add(manaBuff);
        item.getBuffsToCast().add(manaBuff);
//        item.getBuffsToCast().add(healthPowerBuff);
        item.setDescription("increase mana by 1");
        item.setTargetType(TargetType.WHOLE_FRIEND);
        item.setName("Soul Eater");
        item.setPriceInDrake(25000);
        item.getBuffsToCast().add(attackPowerBuff);
        //item.getBuffsToCast().add();
        item.setDescription("with death of every friend minion, attack poewr buff will cast");
        item.setTargetType(TargetType.RANDOM_FRIEND_SOLDIER);*/
        System.out.println(JsonController.getGson().toJson(minion));
    }
}
