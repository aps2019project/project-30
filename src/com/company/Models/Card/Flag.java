package com.company.Models.Card;

import com.company.Models.User.Player;

public class Flag {
    private Player flagHolder;
    private int flagXCoordinate;
    private int flagYCoordinate;

    public Player getFlagHolder() {
        return flagHolder;
    }

    public void setFlagHolder(Player flagHolder) {
        this.flagHolder = flagHolder;
    }

    public int getFlagXCoordinate() {
        return flagXCoordinate;
    }

    public void setFlagXCoordinate(int flagXCoordinate) {
        this.flagXCoordinate = flagXCoordinate;
    }

    public int getFlagYCoordinate() {
        return flagYCoordinate;
    }

    public void setFlagYCoordinate(int flagYCoordinate) {
        this.flagYCoordinate = flagYCoordinate;
    }


}
