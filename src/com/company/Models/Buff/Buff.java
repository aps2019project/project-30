package com.company.Models.Buff;

import com.company.Models.Card.Card;

public abstract class Buff {
    public enum Type {POSSETIVE, NEGATIVE}

    public enum Name {HOLY, HEALTH_POWER, ATTACK_POWER, MANA, ANTI, POSION, WEAKNESS, STUN, DISARM, DISPELL}

    Type type;
    Name name;
    protected Card cardToCast;
    protected boolean isCasted = false;
    protected String description;
    protected Type antiBuff;
    protected int castTime;
    protected int remTurnToCast;
    protected int value;

    public Buff(Type antiBuff, int remTurnToBeInactive, int remTurnToCast, int value) {
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
<<<<<<< HEAD
        return (castTime > 0 || remTurnToCast > 0) && !hasAntiBuff();
=======
        if (hasAntiBuff() == false || (remTurnToBeInactive > 0 && remTurnToCast <= 0)) {
            return true;
        }
        if (remTurnToBeInactive == 0) {
            destuct();
        }
        return false;
>>>>>>> 356cb84121085659a1262106220b2f80eee2e0f1
    }

    boolean canCastThisTurn() {
        return remTurnToCast == 0;
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
        for (Buff buff : cardToCast.getBuffsCasted()) {
            if (name.equals(buff.antiBuff)) {
                return true;
            }
        }
        return false;
    }
}
