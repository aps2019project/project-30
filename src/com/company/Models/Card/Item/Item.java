package com.company.Models.Card.Item;

import com.company.Models.Card.Card;

public class Item extends Card {

    public Item clone(){
        Item item = new Item();
        item.setDescription(this.getDescription());
        item.setInGraveCards(this.isInGraveCards());
        item.setName(this.getName());
        item.setManaPoint(this.getManaPoint());
        item.setPriceInDrake(this.getPriceInDrake());
        item.setTargetType(this.getTargetType());
        item.setId(Card.createNewId());
        return item;
    }
}
