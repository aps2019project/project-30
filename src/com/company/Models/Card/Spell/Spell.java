package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;

public class Spell extends Card {
    private SpellType spellType;

    public Spell(SpellType spellType) {
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void throwBuffs(Card target) {
        target.getBuffsCasted().addAll(this.getBuffsToCast());
    }
}
