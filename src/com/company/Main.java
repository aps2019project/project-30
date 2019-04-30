package com.company;

import com.company.Controllers.JsonController;
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
        ConsoleInput.menusHandler();

        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(null, 1000,1, 4);


        Hero hero = new Hero();
        hero.setName("Div Sefid");
        hero.setPriceInDrake(8000);
        hero.setFullHealth(50);
        hero.setAttackPower(4);
        hero.setAttackType(AttackType.MELEE);
        hero.getBuffsToCast().add(attackPowerBuff);
        hero.setManaPoint(1);
        hero.setCoolDown(2);
        System.out.println("JsonController.getGson().toJson(hero) = " + JsonController.getGson().toJson(hero));
    }
}
