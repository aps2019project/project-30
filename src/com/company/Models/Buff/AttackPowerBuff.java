package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;

public class AttackPowerBuff extends Buff {
    public AttackPowerBuff(Buff.Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        super.name = Name.ATTACK_POWER;
        super.type = Type.POSSETIVE;
    }

    @Override
    public void cast() {
        if (isActive()) {
            if (canCastThisTurn()) {
                if (!isCasted) {
                    ((Soldier) super.cardToCast).incrementAttackPower(value);
                    setCasted(true);
                    decrementCounters();
                }
            }
        } else {
            ((Soldier) super.cardToCast).decrementAttackPower(value);
            destruct();
        }
    }
    public AttackPowerBuff clone() {
        AttackPowerBuff attackPowerBuff = new AttackPowerBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        attackPowerBuff.name = this.name;
        attackPowerBuff.type = this.type;
        return attackPowerBuff;
    }
}
