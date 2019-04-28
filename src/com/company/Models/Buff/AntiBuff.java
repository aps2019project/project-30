package com.company.Models.Buff;

public class AntiBuff extends Buff {
    public AntiBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        name = Name.ANTI;
        type = Type.POSSETIVE;
    }
    @Override
    public void cast() {
        if(isActive()){
            decrementCounters();
        }
    }
}
