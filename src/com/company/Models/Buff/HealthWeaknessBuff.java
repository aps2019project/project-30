package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class HealthWeaknessBuff extends Buff{

    public HealthWeaknessBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        super.name = Name.HEALTH_WEAKNESS;
        super.type = Type.POSSETIVE;
    }

    @Override
    public void cast(){
        if(isActive()){
            if(canCastThisTurn()){
                if (super.cardToCast instanceof Hero)
                    ((Hero) super.cardToCast).decrementHealth(value);
                else if (super.cardToCast instanceof Minion)
                    ((Minion) super.cardToCast).decrementHealth(value);
                setCasted(true);
            }
        }else{
            if (super.cardToCast instanceof Hero)
                ((Hero) super.cardToCast).incrementHealth(value);
            else if (super.cardToCast instanceof Minion)
                ((Minion) super.cardToCast).incrementHealth(value);
            destruct();
        }
        decrementCounters();
    }
}
