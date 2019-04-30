package com.company.Models.Buff;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class ManaBuff extends Buff {
    public ManaBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int valuet) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
    }

    @Override
    public void cast() {
        if (isActive()) {

        }
    }
    public ManaBuff clone() {
        ManaBuff manaBuff = new ManaBuff(this.antiBuff, this.remTurnToBeInactive,this.remTurnToCast, this.remTurnToCast, this.value);
        manaBuff.name = this.name;
        manaBuff.type = this.type;

        return manaBuff;
    }
}
