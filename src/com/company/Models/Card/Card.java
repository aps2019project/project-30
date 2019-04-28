package com.company.Models.Card;

import com.company.Models.Buff.Buff;
import com.company.Models.TargetType;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    private int id;
    private String name;
    private int manaPoint;
    private int priceInDrake;
    private String description;
    private boolean isInGraveCards = false;
    private TargetType targetType;
    private List<Buff> buffsToCast = new ArrayList<>();
    private List<Buff> buffsCasted = new ArrayList<>();
    private static int lastId = 0;


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

    public void setId(int id) {
        this.id = id;
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

    public List<Buff> getBuffsToCast() {
        return buffsToCast;
    }

    public List<Buff> getBuffsCasted() {
        return buffsCasted;
    }

    public static int createNewId(){
        lastId++;
        return lastId;
    }
}
