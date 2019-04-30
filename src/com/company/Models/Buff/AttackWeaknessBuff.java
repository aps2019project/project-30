package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class AttackWeaknessBuff extends Buff {

    public AttackWeaknessBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.ATTACK_WEAKNESS;
        super.type = Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if (isActive()) {
            if (!isCasted) {
                if (super.cardToCast instanceof Hero)
                    ((Hero) super.cardToCast).decrementAttackPower(value);
                else if (super.cardToCast instanceof Minion)
                    ((Minion) super.cardToCast).decrementAttackPower(value);
                setCasted(true);
            }
        } else {
            if (super.cardToCast instanceof Hero)
                ((Hero) super.cardToCast).incrementAttackPower(value);
            else if (super.cardToCast instanceof Minion)
                ((Minion) super.cardToCast).incrementAttackPower(value);
            destruct();
        }
        decrementCounters();
    }
}
