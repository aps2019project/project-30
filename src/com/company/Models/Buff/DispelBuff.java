package com.company.Models.Buff;

public class DispelBuff extends Buff{
    public DispelBuff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        super(antiBuff, remTurnToBeInactive, remTurnToCast, value);
        name=Name.DISPELL;
        type=Type.NEGATIVE;
    }

    @Override
    public void cast() {
        if(isActive()){
            for(Buff buff:cardToCast.getBuffsCasted()){
                if(!buff.name.equals(Name.DISPELL)){
                    cardToCast.getBuffsCasted().remove(buff);
                }
            }
        }
        else
            destuct();
    }
}
