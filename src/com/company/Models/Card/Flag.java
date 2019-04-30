package com.company.Models.Card;

import com.company.Models.Battle.Map.Cell;

public class Flag {
    private Card flagHolder;
    private Cell cell;

    public Card getFlagHolder() {
        return flagHolder;
    }

    public void setFlagHolder(Card flagHolder) {
        this.flagHolder = flagHolder;
    }

    public Cell getCell(){
        return cell;
    }

}
