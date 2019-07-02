package com.company.Models;

import java.util.Scanner;

public abstract class MessageReceiver extends Thread {
    public static Scanner scanner;

    public String read() {
        String message = scanner.nextLine();
        System.out.println(message);
        return message;
    }

}
