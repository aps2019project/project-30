package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;

public class Spell extends Card {

    public void throwBuffs(Card target) {
        target.getBuffsCasted().addAll(this.getBuffsToCast());
        //Todo : Clone Buffs And Cast
    }

    public Spell clone() {
        Spell spell = new Spell();
        spell.setName(this.getName());
        spell.setTargetType(this.getTargetType());
        spell.setInGraveCards(this.isInGraveCards());
        spell.setManaPoint(this.getManaPoint());
        spell.setPriceInDrake(this.getPriceInDrake());
        spell.setDescription(this.getDescription());
        return spell;
    }
}
