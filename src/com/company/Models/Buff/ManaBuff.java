package com.company.Models.Buff;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;

public class ManaBuff extends Buff {

    public ManaBuff(Buff.Type antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        super.name = Name.MANA;
        super.type = Type.POSSETIVE;
    }

    @Override
    public void cast() {
        if (isActive()) {
            if (canCastThisTurn()) {
                Battle.getPlayingBattle().getBattleController().playerThatHasThisCard(super.cardToCast).incrementMana(this.value);
            }
        } else {
            destruct();
        }
        decrementCounters();
    }

    public ManaBuff clone() {
        ManaBuff manaBuff = new ManaBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        manaBuff.name = this.name;
        manaBuff.type = this.type;

        return manaBuff;
    }
}
