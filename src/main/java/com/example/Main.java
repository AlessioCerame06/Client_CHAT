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

        //login
        System.out.println("Digita Login <nome utente> per iniziare");
        String utente = " ";
        String esitoLogin = " " ;

        do{
            utente = scanner.nextLine();
            out.writeBytes(utente + "\n");
            esitoLogin = in.readLine();
        }while(esitoLogin.equals("login failed"));
        
        
        

        do {
            
        } while (true);
    }
}