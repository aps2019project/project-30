package com.company.Views;

import com.company.Models.ErrorType;

public class ConsoleOutput {
    public static void printErrorMessage(ErrorType errorType) {
        System.out.println(errorType.getMessage());
    }
}
