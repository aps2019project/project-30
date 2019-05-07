package com.company.Models.Card;

import com.company.Models.Battle.Map.Cell;

public class Flag {
    private Card flagHolder;
    private Cell cell;
    private int holdingTurn;

    public Flag(Cell cell) {
        this.cell = cell;
    }

    public Card getFlagHolder() {
        return flagHolder;
    }

    public void setFlagHolder(Card flagHolder) {
        this.flagHolder = flagHolder;
    }

    public Cell getCell(){
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getHoldingTurn() {
        return holdingTurn;
    }

    public void setHoldingTurn(int holdingTurn) {
        this.holdingTurn = holdingTurn;
    }


}
