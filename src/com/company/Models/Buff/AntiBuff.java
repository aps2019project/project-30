package com.company.Models.Buff;

import com.company.Models.Card.Card;

public class AntiBuff extends Buff {
    public AntiBuff(Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        name = Name.ANTI;
        type = Type.POSSETIVE;
    }
    @Override
    public void cast() {
        if(!isActive()){
//            destruct();
        }
        decrementCounters();
    }
    public AntiBuff clone() {
        AntiBuff antiBuff= new AntiBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        antiBuff.name = this.name;
        antiBuff.type = this.type;
        return antiBuff;
    }
}
