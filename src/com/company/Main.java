package com.company;

import com.company.Models.Buff.Buff;
import com.company.Models.Buff.HolyBuff;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.TargetType;
import com.company.Views.ConsoleInput;
import com.google.gson.Gson;

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
        gson.toJson(spell);
    }
}
