package com.company.Models.Buff;

public class WeaknessBuff extends Buff{
    public WeaknessBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
    }

    @Override
    public void cast() {

    }
}
