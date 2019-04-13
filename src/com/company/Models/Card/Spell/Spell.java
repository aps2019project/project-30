package com.company.Models.Card.Spell;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;

public abstract class Spell extends Card {
    private SpellType spellType;

    public Spell(SpellType spellType) {
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return spellType;
    }
}
