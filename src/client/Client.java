package client;

import connections.Connection;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {

    static Socket socket;
    static Connection c;
    static Scanner sc = new Scanner(System.in);

    public Client(){
        try{
            socket = new Socket("192.168.1.119", 1881);
            System.out.println("Socket created");
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        new Client();
        String msgSend;
        String msgRecive;

        try {

            System.out.println("Enter rental data");
            System.out.print("Car model > ");
            String carModel = sc.nextLine();
            System.out.print("Pickup (dd/MM/yyyy hh:mm) > ");
            Date pickup = sdf.parse(sc.nextLine());
            System.out.print("Return (dd/MM/yyyy hh:mm) > ");
            Date delivery = sdf.parse(sc.nextLine());

            msgSend = carModel + "/" + pickup.getTime() + "/" + delivery.getTime();


        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            msgSend = "error";
        }

        c.send(socket, msgSend);

        msgRecive = c.recive(socket);
        System.out.println(msgRecive.trim());
    }
}
