package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class HealthPowerBuff extends Buff {
    public HealthPowerBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HEALTH_POWER;
        super.type = Type.POSSETIVE;
    }
    @Override
    public void cast() {
        if (isActive()) {
            if (!isCasted) {
                if (super.cardToCast instanceof Hero)
                    ((Hero) super.cardToCast).incrementHealth(1);
                else if (super.cardToCast instanceof Minion)
                    ((Minion) super.cardToCast).incrementHealth(1);
                setCasted(true);
                decrementCounters();
            }
        } else {
            if (super.cardToCast instanceof Hero)
                ((Hero) super.cardToCast).decremeantHealth(1);
            else if (super.cardToCast instanceof Minion)
                ((Minion) super.cardToCast).decremeantHealth( 1);
        }
    }

}
