package com.company.Models.Battle.Modes;

import com.company.Models.Card.Flag;

public class CaptureTheFlag extends Mode {
    Flag flag;

    public CaptureTheFlag() {
        this.flag = new Flag();
    }

    public Flag getFlag() {
        return flag;
    }

    public void pickUp(int playerNum) {

    }

    @Override
    public void handler() {

    }
}