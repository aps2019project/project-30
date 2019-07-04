package com.company.Models;

public class Property {
    final public static String USERNAME_PROPERTY = "username";
    final public static String PASSWORD_PROPERTY = "password";


    private String property;
    private String value;

    public Property(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}
