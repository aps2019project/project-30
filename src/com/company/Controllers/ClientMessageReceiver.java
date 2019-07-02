package com.company.Controllers;

import com.company.Models.MessageReceiver;
import java.io.InputStream;
import java.util.Scanner;

public class ClientMessageReceiver extends MessageReceiver {

    ClientMessageReceiver(InputStream input) {
        scanner = new Scanner(input);
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String message = read();
        }
    }

}
