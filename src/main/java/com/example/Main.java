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

        }while(true);
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