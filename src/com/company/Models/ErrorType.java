package com.company.Models;

public enum ErrorType {
    USERNAME_EXISTS("An account already exists with this username."),
    USERNAME_NOTFOUND("This username does not exists"),
    CARD_NOTFOUND("This card does not exists"),
    NOTENOUGH_DRAKE("you have not enough drake to buy this card"),
    PASSWORD_INVALID("Invalid password."),
    DECK_EXISTS("An deck alredy exists with this deckname"),
    CARD_EXISTS("An card alredy exists with this cardid"),
    CARD_NOTFOUNDINDECK("This card does not exist in this deck"),
    DECK_FULL("Deck capacity is complite"),
    HERO_EXISTS("This deck has hero"),
    ITEM_EXISTS("This deck has item"),
    DECK_VALIDATE("Deck is not validate"),
    CARD_ID_INVALID("Invalid card id"),
    INVALID_CELL("Invalid target");



    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
