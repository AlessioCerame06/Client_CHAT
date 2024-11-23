package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 10100);
        Scanner scanner = new Scanner(System.in);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        boolean statoLogin = false;

        //menù
        String comando = "";
        do {
            System.out.println("\n");
            System.out.println("digita LOGIN <nome utente> per accedere");
            System.out.println("digita MSG <destinatario> <messaggio> per inviare un messaggio privato");
            System.out.println("digita MSG ALL <messaggio> per inviare un messaggio a tutti");
            System.out.println("digita USERS per ricevere la lista degli utenti connessi");
            System.out.println("digita LOGOUT per disconnetterti");
            System.out.println("\n");

            comando = scanner.nextLine();
            String[] elementiComando = comando.split(" ", 2);
            String esito = " ";

            if (elementiComando[0] == "LOGIN" && statoLogin == false) {
                out.writeBytes(comando + "\n");
                esito = in.readLine();
                if (esito.contains("OK LOGIN")) {
                    System.out.println("Login avvenuto con successo");
                } else {
                    System.out.println("Errore LOGIN");
                }
                statoLogin = true;
            } else if (elementiComando[0] == "LIST" && statoLogin == true){
                out.writeBytes(comando);
                String risposta = in.readLine();
                if (risposta.contains("OK USERS")) {
                    String listaElementi[] = risposta.split(" ", 3);
                    String listaUtenti[] = listaElementi[2].split(",");
                    System.out.println("Lista utenti:");
                    for (int i = 0; i < listaUtenti.length; i++) {
                        System.out.println(listaUtenti[i]);
                    }
                } else {
                    System.out.println("Errore nella richiesta");
                }
            } else if (elementiComando[0] == "MSG" && statoLogin == true) {
                out.writeBytes(comando);
                esito = in.readLine();
                if (esito.contains("ERROR MSG")) {
                    System.out.println("Messaggio non inviato");
                }
            } else if (comando.contains("MSG ALL")) {
                out.writeBytes(comando);
                esito = in.readLine();
                if (esito.contains("ERROR MSG ALL")) {
                    System.out.println("Messaggio non inviato");
                }
            }else if (comando.contains("LOGOUT")) {
                break;
            } else {
                System.out.println("Errore. Digitare un comando esistente");
            }

        }while(true);
        socket.close();
        scanner.close();
        in.close();
        out.close();
        System.out.println("Sei stato disconesso");
        /*
        String comando = "";
        do {
            System.out.println("\n");
            System.out.println("digita LOGIN <nome utente> per accedere");
            System.out.println("digita MSG <destinatario> <messaggio> per inviare un messaggio privato");
            System.out.println("digita MSG ALL <messaggio> per inviare un messaggio a tutti");
            System.out.println("digita USERS per ricevere la lista degli utenti connessi");
            System.out.println("digita LOGOUT per disconnetterti");
            System.out.println("\n");

            comando = scanner.nextLine();
            
            
            String risposta = " ";
            if(comando.equals("USER LIST")){    //USER LIST
                out.writeBytes("*" + "\n");
                while(!risposta.equals("@")){
                    risposta = in.readLine();
                    if(!risposta.equals("@")){
                        System.out.println(risposta);
                    }
                }
            }else if(comando.contains("CONNECT")){       //CONNECT
                String messaggio = " ";
                System.out.println("digitare LEAVE per disconnettersi e ritornare al menù");
                while(!messaggio.equals("LEAVE")){
                    messaggio = scanner.nextLine();
                    out.writeBytes(messaggio + "\n");
                }
            } else if (comando.equals("LEAVE")){
                out.writeBytes("!" + "\n");
            } else if (comando.equals("LOGOUT")){      //CLOSE
                out.writeBytes("?" + "\n");
                break;
            } else {
                System.out.println("Errore. Il comando non esiste.");
            }
        } while (true);
        socket.close();
        scanner.close();
        in.close();
        out.close();
        System.out.println("Sei stato disconnesso");
        */
    }
}