package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 10100);
        Scanner scanner = new Scanner(System.in);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        //login
        System.out.println("Digita <nome utente> per il login e iniziare a comunicare");
        String utente = " ";
        String esitoLogin = " " ;

        do {
            utente = scanner.nextLine();
            out.writeBytes(utente + "\n");
            esitoLogin = in.readLine();
            if (esitoLogin.equals("login failed")) {
                System.out.println("Errore. Il nome utente " + utente + " è gia stato inserito.");
            }
        } while (esitoLogin.equals("login failed"));
        System.out.println("Login completato");

        //menù
        String scelta = "";
        do {
            System.out.println("\n");
            System.out.println("digitare USER LIST per ricevere la lista degli utenti e dei gruppi");
            System.out.println("digitare CONNECT <destinatario> per connettersi ad un gruppo o ad un utente");
            System.out.println("digitare CLOSE disconnettersi dal server");
            System.out.println("\n");

            scelta = scanner.nextLine();
            
            //USER LIST
            String risposta = " ";
            if(scelta.equals("USER LIST")){    //USER LIST
                out.writeBytes("*" + "\n");
                while(!risposta.equals("@")){
                    risposta = in.readLine();
                    if(!risposta.equals("@")){
                        System.out.println(risposta);
                    }
                }
            }else if(scelta.contains("CONNECT")){       //CONNECT
                String messaggio = " ";
                System.out.println("digitare LEAVE per disconnettersi e ritornare al menù");
                while(!messaggio.equals("LEAVE")){
                    messaggio = scanner.nextLine();
                    out.writeBytes(messaggio + "\n");
                }
            } else if (scelta.equals("LEAVE")){
                out.writeBytes("!" + "\n");
            } else if (scelta.equals("CLOSE")){      //CLOSE
                out.writeBytes("?" + "\n");
                break;
            } else {
                System.out.println("Errore. Il comando " + scelta + " non esiste.");
            }
        } while (true);
        socket.close();
        scanner.close();
        in.close();
        out.close();
        System.out.println("Sei stato disconnesso");
    }
}