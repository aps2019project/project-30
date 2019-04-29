package com.company.Models.Buff;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class ManaBuff extends Buff {
    int numberOfManasToincrement;
    public ManaBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value, int numberOfManasToincrement) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        this.numberOfManasToincrement = numberOfManasToincrement;
    }

    @Override
    public void cast() {
        if (isActive()) {

        }
    }
}
