package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class PosionBuff extends Buff{
    public PosionBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        name = Name.POSION;
        type = Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if(isActive()){
            if(cardToCast instanceof Hero){
                ((Hero) cardToCast).decremeantHealth(1);
            }
            else if(cardToCast instanceof Minion){
                ((Minion) cardToCast).decremeantHealth(1);
            }
            decrementCounters();
        }
        else
            destuct();
    }
}
