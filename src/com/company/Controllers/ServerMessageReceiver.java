package com.company.Controllers;

import com.company.Models.MessageReceiver;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerMessageReceiver extends MessageReceiver {

    private MessageWriter writer;

    public ServerMessageReceiver(Socket socket) throws IOException {
        scanner = new Scanner(socket.getInputStream());
        writer = new MessageWriter(socket.getOutputStream());
        writer.start();
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String message = read();
            writer.writeln(message);
        }
    }
}
