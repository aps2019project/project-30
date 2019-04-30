package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class AttackPowerBuff extends Buff {
    public AttackPowerBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HOLY;
        super.type = Type.POSSETIVE;
    }

    @Override
    public void cast() {
        if (isActive()) {
            if (!isCasted) {
                if (super.cardToCast instanceof Hero)
                    ((Hero) super.cardToCast).incrementAttackPower(value);
                else if (super.cardToCast instanceof Minion)
                    ((Minion) super.cardToCast).incrementAttackPower(value);
                setCasted(true);
                decrementCounters();
            }
        } else {
            if (super.cardToCast instanceof Hero)
                ((Hero) super.cardToCast).decrementAttackPower(value);
            else if (super.cardToCast instanceof Minion)
                ((Minion) super.cardToCast).decrementAttackPower(value);
            destuct();
        }
    }
    public AttackPowerBuff clone() {
        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(this.antiBuff, this.remTurnToBeInactive, this.remTurnToCast, this.value);
        attackPowerBuff.name = this.name;
        attackPowerBuff.type = this.type;
        return attackPowerBuff;
    }
}
