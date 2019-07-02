package com.company.Controllers;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageWriter extends Thread {
    private PrintStream printer;
    private BlockingQueue<String> pipeline = new LinkedBlockingQueue<>();

    MessageWriter(OutputStream output) {
        printer = new PrintStream(output);
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                printer.println(pipeline.take());
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void writeln(String message) {
        try {
            pipeline.put(message);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
