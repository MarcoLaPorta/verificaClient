package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws UnknownError, UnknownHostException, IOException{
        
        System.out.println("Client partito");

        Socket server = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        DataOutputStream out = new DataOutputStream(server.getOutputStream());

        System.out.println("Client collegato");

        Scanner myScan = new Scanner(System.in);

        String num;
        String risposta;
        int numT = 0;
        
        do {

            System.out.println("Inserisci il numero: ");
            num = myScan.nextLine();
            out.writeBytes(num + '\n');

            risposta = in.readLine();

            switch (risposta) {
                
                case "<":
                    System.out.println("numero troppo piccolo");        
                    break;

                case ">":
                    System.out.println("numero troppo grande");        
                    break;

                case "=":
                    numT = in.read();
                    System.out.println("HAI INDOVINATO in " + numT + " tentativi!");              
                    break;

                case "!":
                    System.out.println("errore");

            
                default:
                    break;
            }


        } while (!risposta.equals("="));

        server.close();
        out.close();
        in.close();
        myScan.close();
    }
}