package com.company;

import com.company.Controllers.ShopController;
import com.company.Views.ConsoleInput;

public class Main {

    public static void main(String[] args) {
        ShopController.initialize();
        ConsoleInput.menusHandler();
    }
}