package com.company.Models.Receiver;

import java.io.InputStream;
import java.util.Scanner;

public class ClientMessageReceiver extends MessageReceiver {

    public ClientMessageReceiver(InputStream input) {
        scanner = new Scanner(input);
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String message = read();
        }
    }

}
