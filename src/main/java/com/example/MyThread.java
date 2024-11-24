package com.example;

import java.io.*;
import java.net.*;

public class MyThread extends Thread {
    private Socket socket;
    private BufferedReader in;

    public MyThread(Socket socket) {
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Errore nell'inizializzare il flusso di lettura: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Messaggio ricevuto: " + message);
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura dei messaggi: " + e.getMessage());
        }
    }
}
