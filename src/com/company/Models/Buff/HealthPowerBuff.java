package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;

public class HealthPowerBuff extends Buff {
    public HealthPowerBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HEALTH_POWER;
        super.type = Type.POSSETIVE;
    }
    @Override
    public void cast() {
        if (isActive()) {
            if (canCastThisTurn()) {
                if (!isCasted) {
                    ((Soldier) super.cardToCast).incrementHealth(value);
                    setCasted(true);
                    decrementCounters();
                }
            }
        } else {
            ((Soldier) super.cardToCast).decrementHealth(value);
            destruct();
        }
    }
    public HealthPowerBuff clone() {
        HealthPowerBuff healthPowerBuff= new HealthPowerBuff(this.antiBuff, this.remTurnToBeInactive, this.remTurnToCast, this.value);
        healthPowerBuff.name = this.name;
        healthPowerBuff.type = this.type;
        return healthPowerBuff;
    }

}
