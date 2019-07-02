package com.company.Models.Buff;

import com.company.Models.Card.Card;

public abstract class Buff {
    public enum Type {POSSETIVE, NEGATIVE}

    public enum Name {HOLY, HEALTH_POWER, HEALTH_WEAKNESS, ATTACK_POWER, ATTACK_WEAKNESS, MANA, ANTI, POSION, STUN, DISARM, DISPELL}

    Type type;
    Name name;
    protected Card cardToCast;
    protected boolean isCasted = false;
    protected String description;
    protected Name antiBuff;
    protected int castTime;
    protected int remTurnToCast;
    protected int value;

    public Buff(Name antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
        this.antiBuff = antiBuff;
        this.castTime = remTurnToBeInactive;
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
        return (castTime > 0 || remTurnToCast >= 0) && !hasAntiBuff();
    }

    boolean canCastThisTurn() {
        return remTurnToCast <= 0;
    }

    void decrementCounters() {
        remTurnToCast--;
        if (canCastThisTurn()) {
            castTime--;
        }
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
    public void destruct() {
        cardToCast.getBuffsCasted().remove(this);
    }

    public boolean hasAntiBuff() {
        if (cardToCast != null) {
            for (Buff buff : cardToCast.getBuffsCasted()) {
                if (name.equals(buff.antiBuff)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract Buff clone();
}
