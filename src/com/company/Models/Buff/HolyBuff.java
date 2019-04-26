package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class HolyBuff extends Buff{
    public HolyBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HOLY;
        super.type = Type.POSSETIVE;
    }
    // This Methode Runs When Any Soldiers Attack
    @Override
    public void cast() {
        if (isActive()) {
            if (super.cardToCast instanceof Hero)
                ((Hero) super.cardToCast).incrementHealth(1);
            else if (super.cardToCast instanceof Minion)
                ((Minion) super.cardToCast).incrementHealth(1);
            decrementCounters();
        }
    }
}
