

package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 10100);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)) {

            String comando = "";
            while (true) {
                System.out.println("Comandi: LOGIN <nome>, USERS, MSG <destinatario> <messaggio>, MSG ALL <messaggio>, LOGOUT");
                comando = scanner.nextLine();
                out.writeBytes(comando + "\n");
                String risposta = in.readLine();

                if (comando.startsWith("LOGOUT")) {
                    break;
                } else if (risposta.contains("OK LOGIN")) {
                    System.out.println("Login effetuato");
                } else if (risposta.equals("ERROR LOGIN username not valid or already used")) {
                    System.out.println("Errore. Username non valido o è già stato inserito");
                } else if (risposta.equals("ERROR you must login first")) {
                    System.out.println("Per prima cosa di devi loggare");
                } else if (risposta.contains("OK USERS")) {
                    System.out.println("Ecco la lista degli utenti:");
                    String stringaUtenti = risposta.substring(9);
                    String listaUtenti[] = stringaUtenti.split(",");
                    for (String utente : listaUtenti) {
                        System.out.println(utente);
                    }
                } else if (risposta.equals("ERROR MSG invalid format")) {
                    System.out.println("Errore. Formato non valido");
                } else if (risposta.equals("ERROR MSG user not found")) {
                    System.out.println("Errore. Utente non trovato");
                } else if (risposta.equals("ERROR command not recognized")) {
                    System.out.println("Inserire un comando esistente");
                } else if (!risposta.equals("OK MSG PRIVATE") && !risposta.equals("OK LOGOUT") && !risposta.equals("OK MSG ALL")) {
                    String[] partiMessaggio = risposta.split(" ", 4);
                    System.out.println("Da " + partiMessaggio[1] + ": " + partiMessaggio[3]);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore client: " + e.getMessage());
        }
    }
}