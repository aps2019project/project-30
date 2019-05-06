package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;

public class HolyBuff extends Buff {
    public HolyBuff(Buff.Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        super.name = Name.HOLY;
        super.type = Type.POSSETIVE;
    }

    // This Methode Runs When Any Soldiers Attack
    @Override
    public void cast() {
        if (isActive()) {
            if (canCastThisTurn()) {
                ((Soldier) super.cardToCast).incrementHealth(value);
            }
        } else {
//            destruct();
        }
        decrementCounters();
    }

    public HolyBuff clone() {
        HolyBuff holyBuff = new HolyBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        holyBuff.name = this.name;
        holyBuff.type = this.type;
        return holyBuff;
    }
}
