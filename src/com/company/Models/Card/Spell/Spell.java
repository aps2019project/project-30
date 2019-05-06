package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;


import java.util.ArrayList;

public class Spell extends Card {

    public Spell clone() {
        Spell spell = new Spell();
        spell.setName(this.getName());
        spell.setTargetType(this.getTargetType());
        spell.setInGraveCards(this.isInGraveCards());
        spell.setManaPoint(this.getManaPoint());
        spell.getBuffsToCast().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsToCast()).clone()));
        spell.getBuffsCasted().addAll(((ArrayList<Buff>)((ArrayList<Buff>)this.getBuffsCasted()).clone()));
        spell.setPriceInDrake(this.getPriceInDrake());
        spell.setDescription(this.getDescription());
        return spell;
    }
    public Spell makeCopyForCreatingNewCardInShop(){
        Spell spell = this.clone();
        spell.setId(Card.createNewCardIdToCreatingNewCardInShop());
        return spell;
    }

    public static void showSpell(Spell spell) {
        System.out.println("- ID : " + spell.getId() +
                "- Type : Spell " +
                "- Name : " + spell.getName() +
                "- MP : " + spell.getManaPoint() +
                "- Desc : " + spell.getDescription() +
                "- Sell cost : " + spell.getPriceInDrake());
    }

    public static void showSpellInBattle(Spell spell) {
        System.out.println("- ID : " + spell.getId() +
                "- Type : Spell " +
                "- Name : " + spell.getName() +
                "- MP : " + spell.getManaPoint() +
                "- Desc : " + spell.getDescription());
    }
}
