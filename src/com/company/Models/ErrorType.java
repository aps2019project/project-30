package com.company.Models;

public enum ErrorType {
    USERNAME_EXISTS("An account already exists with this username."),
    USERNAME_NOTFOUND("This username does not exists"),
    CARD_NOTFOUND("This card does not exists"),
    NOTENOUGH_DRAKE("you have not enough drake to buy this card"),
    PASSWORD_INVALID("Invalid password.");

    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
