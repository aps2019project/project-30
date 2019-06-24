package com.company.Models.Card;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;

public class ObservableCard implements ObservableObjectValue<Card> {

    private Card card;

    @Override
    public Card get() {
        return card;
    }

    @Override
    public void addListener(ChangeListener<? super Card> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super Card> listener) {

    }

    @Override
    public Card getValue() {
        return card;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
