package com.company.Models;

public class Property {
    final public static String USERNAME_PROPERTY = "username";
    final public static String PASSWORD_PROPERTY = "password";
    final public static String ERROR_MESSAGE_PROPERTY = "errorMessage";
    final public static String CARDID_PROPERTY ="cardid";
    final public static String CARDNAME_PROPERTY="cardid";
    final public static String SHOP_BUYCARD ="allshopcard";
    //final public static String BUY_SEARCH_BAR="searchcontent";
    final public static String SHOP_SELLCARD="cardsforsell";
    final public static String WINS_NUMBER_PROPERTY = "winsNumber";
    public static final String ACCOUNTS_INFO = "AccountsInfo";


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
