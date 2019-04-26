package com.company.Models.Card;

import com.company.Models.Buff.Buff;

public interface Soldier {
    boolean hasBuffByName(Buff.Name buffName);
    void attack(Card targetCard);
    void counterAttack(Card targetCard);
}
