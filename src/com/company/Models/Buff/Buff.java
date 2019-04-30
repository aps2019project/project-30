package com.company.Models.Buff;

import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Buff {
    public enum Type {POSSETIVE, NEGATIVE}

    public enum Name {HOLY, HEALTH_POWER, ATTACK_POWER, MANA, ANTI, POSION, WEAKNESS, STUN, DISARM, DISPELL}

    Type type;
    Name name;
    protected Card cardToCast;
    protected boolean isCasted = false;
    protected String description;
    protected Type antiBuff;
    protected int remTurnToBeInactive;
    protected int remTurnToCast;
    protected int value;

    public Buff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        this.antiBuff = antiBuff;
        this.remTurnToBeInactive = remTurnToBeInactive;
        this.remTurnToCast = remTurnToCast;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public abstract void cast();

    boolean isActive() {
        if (hasAntiBuff() == false || (remTurnToBeInactive > 0 && remTurnToCast <= 0)) {
            return true;
        }
        if (remTurnToBeInactive == 0) {
            destuct();
        }
        return false;
    }

    boolean canCastThisTurn() {
        return remTurnToCast == 0;
    }

    void decrementCounters() {
        remTurnToCast--;
        remTurnToBeInactive--;
    }

    public void setCardToCast(Card cardToCast) {
        this.cardToCast = cardToCast;
    }

    public boolean isCasted() {
        return isCasted;
    }

    public void setCasted(boolean casted) {
        isCasted = casted;
    }

    // destruct Method Remove buff from buffsCasted's Player
    public void destuct() {
        cardToCast.getBuffsCasted().remove(this);
    }

    public boolean hasAntiBuff() {
        for (Buff buff : cardToCast.getBuffsCasted()) {
            if (name.equals(buff.antiBuff)) {
                return true;
            }
        }
        return false;
    }
}
