package com.company.Models.Buff;

public class StunBuff extends Buff{
    public StunBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        name = Name.STUN;
        type = Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if(!isActive()){
            destruct();
        }
        decrementCounters();
    }
    public StunBuff clone() {
        StunBuff stunBuff = new StunBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        stunBuff.name = this.name;
        stunBuff.type = this.type;
        return stunBuff;
    }
}
