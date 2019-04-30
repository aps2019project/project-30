package com.company.Models.Buff;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class ManaBuff extends Buff {
    public ManaBuff(Buff.Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
    }

    @Override
    public void cast() {
        if (isActive()) {
            Battle.getPlayingBattle().getBattleController().playerThatHasThisCard(super.cardToCast).incremeantMana(this.value);
        }
    }
}
