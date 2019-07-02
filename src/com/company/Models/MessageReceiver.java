package com.company.Models;

import java.util.Scanner;

public abstract class MessageReceiver extends Thread {
    static Scanner scanner;

    String read() {
        String message = scanner.nextLine();
        System.out.println(message);
        return message;
    }

}
