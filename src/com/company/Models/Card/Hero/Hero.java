package com.company.Models.Card.Hero;

import com.company.Models.Card.AttackType;
import com.company.Models.Card.Card;
import com.company.Models.Card.Spell.Spell;

public abstract class Hero extends Card {

    private int health;
    private int coolDownRemaining;
    private HeroType heroType;

    public HeroType getHeroType() {
        return heroType;
    }

    public int getHealth() {
        return health;
    }

    public int getCoolDownRemaining() {
        return coolDownRemaining;
    }

    public void decremeantCoolDownRemaining() {

    }

    public void decremeantHealth(int number) {
        this.health -= number;
    }

    public void incrementHealth(int number) {
        this.health += number;
    }
}
