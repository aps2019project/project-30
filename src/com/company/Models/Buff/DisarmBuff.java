package com.company.Models.Buff;

public class DisarmBuff extends Buff{
    public DisarmBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
    }

    @Override
    public void cast() {
        if (isActive()) {
            decrementCounters();
        } else {
            destuct();
        }
    }
}
