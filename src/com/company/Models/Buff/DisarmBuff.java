package com.company.Models.Buff;

public class DisarmBuff extends Buff {
    public DisarmBuff(Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        super.name=Name.DISARM;
        super.type=Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if (!isActive()) {
//            destruct();
        }
        decrementCounters();
    }
    public DisarmBuff clone() {
        DisarmBuff disarmBuff = new DisarmBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        disarmBuff.name = this.name;
        disarmBuff.type = this.type;
        return disarmBuff;
    }
}
