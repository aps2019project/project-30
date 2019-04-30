package com.company.Models.Buff;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class PosionBuff extends Buff{
    public PosionBuff(Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff,castTime, remTurnToCast, value);
        name = Name.POSION;
        type = Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if(isActive()){
            if(canCastThisTurn()) {
                if (cardToCast instanceof Hero) {
                    ((Hero) cardToCast).decrementHealth(1);
                } else if (cardToCast instanceof Minion) {
                    ((Minion) cardToCast).decrementHealth(1);
                }
            }
        } else{
            destruct();
        }
        decrementCounters();
    }
    public PosionBuff clone() {
        PosionBuff posionBuff = new PosionBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        posionBuff.name = this.name;
        posionBuff.type = this.type;
        return posionBuff;
    }
}
