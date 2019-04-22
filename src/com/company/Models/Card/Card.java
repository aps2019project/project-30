package com.company.Models.Card;

import com.company.Models.Buff.Buff;
import com.company.Models.TargetType;

import java.util.ArrayList;

public abstract class Card {
    private int id;
    private String name;
    private int manaPoint;
    private int priceInDrake;
    private String description;
    private boolean isInGraveCards = false;
    private TargetType targetType;
    private ArrayList<Buff> buffsToCast;
    private ArrayList<Buff> buffsCasted;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInGraveCards() {
        return isInGraveCards;
    }

    public void setInGraveCards(boolean inGraveCards) {
        isInGraveCards = inGraveCards;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getPriceInDrake() {
        return priceInDrake;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public void setPriceInDrake(int priceInDrake) {
        this.priceInDrake = priceInDrake;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public ArrayList<Buff> getBuffsToCast() {
        return buffsToCast;
    }

    public ArrayList<Buff> getBuffsCasted() {
        return buffsCasted;
    }
}
