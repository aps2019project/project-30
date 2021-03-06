package com.company.Models;

public enum ErrorType {
    USERNAME_EXISTS("An account already exists with this username."),
    USERNAME_NOTFOUND("This username does not exists"),
    CARD_NOTFOUND("This card does not exists"),
    NOTENOUGH_DRAKE("You have not enough drake to buy this card"),
    NOTENOUGH_MANA("You don't have enough mana"),
    PASSWORD_INVALID("Invalid password."),
    DECK_EXISTS("An deck alredy exists with this deckname"),
    CARD_EXISTS("An card alredy exists with this cardid"),
    CARD_NOTFOUNDINDECK("This card does not exist in this deck"),
    CARD_NOTFOUNDINGRAVEYARD("This card does not exist in grave yard"),
    DECK_FULL("Deck capacity is complite"),
    DECK_NOTFOUND("This deck does not exists"),
    HERO_EXISTS("This deck has hero"),
    ITEM_EXISTS("This deck has item"),
    DECK_VALIDATE("Deck is not validate"),
    CARD_ID_INVALID("Invalid card id"),
    INVALID_CELL("Invalid cell"),
    UNAVAILABLE_OPPONENT_SOLDIER("Opponent minion is unavailable for attack"),
    CARD_CANT_ATTACK("Card Can't Attack"),
    CELL_VALIDATE("selected cell is invalid"),
    NO_SELECTED_CARD("select a card first"),
    COOLDOWN_VALIDATE("cooldown is not zero"),
    INVALID_CARD("choose one of your cards"),
    COLLECTIBLE_ITEM("this is a collectible item, you can buy it"),
    CONNECTING_SERVER_FAILD("server not found");



    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
