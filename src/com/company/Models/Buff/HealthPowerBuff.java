package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class HealthPowerBuff extends Buff {
    public HealthPowerBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
    }

    @Override
    public void cast() {
        if (super.cardToCast instanceof Hero)
            ((Hero) super.cardToCast).incrementAttackPower(value);
        else if (super.cardToCast instanceof Minion)
            ((Minion) super.cardToCast).incrementAttackPower(value);
    }
}
