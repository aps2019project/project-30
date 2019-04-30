package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;

public class HolyBuff extends Buff {
    public HolyBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HOLY;
        super.type = Type.POSSETIVE;
    }

    // This Methode Runs When Any Soldiers Attack
    @Override
    public void cast() {
        if (isActive()) {
            if (canCastThisTurn()) {
                ((Soldier) super.cardToCast).incrementHealth(1);
            }
        } else {
            destruct();
        }
        decrementCounters();
    }

    public HolyBuff clone() {
        HolyBuff holyBuff = new HolyBuff(this.antiBuff, this.remTurnToBeInactive, this.remTurnToCast, this.value);
        holyBuff.name = this.name;
        holyBuff.type = this.type;
        return holyBuff;
    }
}
