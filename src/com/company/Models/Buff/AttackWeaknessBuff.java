package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class AttackWeaknessBuff extends Buff {

    public AttackWeaknessBuff(Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
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
    public AttackWeaknessBuff clone() {
        AttackWeaknessBuff attackWeaknessBuff = new AttackWeaknessBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        attackWeaknessBuff.name = this.name;
        attackWeaknessBuff.type = this.type;
        return attackWeaknessBuff;
    }
}
