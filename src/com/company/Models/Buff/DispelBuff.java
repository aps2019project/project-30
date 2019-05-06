package com.company.Models.Buff;

public class DispelBuff extends Buff{
    public DispelBuff(Name antiBuff, int castTime, int remTurnToCast, int value) {
        super(antiBuff, castTime, remTurnToCast, value);
        name=Name.DISPELL;
        type=Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if(isActive()){
            if(canCastThisTurn()) {
                for (Buff buff : cardToCast.getBuffsCasted()) {
                    if (!buff.name.equals(Name.DISPELL)) {
                        cardToCast.getBuffsCasted().remove(buff);
                    }
                }
            }
        } else{
            //destruct();
        }
        decrementCounters();
    }
    public DispelBuff clone() {
        DispelBuff dispelBuff = new DispelBuff(this.antiBuff, this.castTime, this.remTurnToCast, this.value);
        dispelBuff.name = this.name;
        dispelBuff.type = this.type;
        return dispelBuff;
    }
}
