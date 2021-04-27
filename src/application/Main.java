package application;

import connections.Connection;
import entities.Car;
import entities.Contract;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static ServerSocket serverSocket;
    static Socket clientSocket;
    static Connection c = new Connection();
    static Scanner sc = new Scanner(System.in);

    public Main(){
        try{
            serverSocket = new ServerSocket(1880);
            System.out.println("Socket created");
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[]args) throws IOException {

        new Main();
        String msgRecive;
        String msgSend;

        for(;;) {
            if (accept()) {
                try {

                    msgRecive = c.recive(clientSocket);
                    String msg[] = msgRecive.split("/");

                    System.out.print("Enter price per hour: $");
                    double pricePerHour = sc.nextDouble();
                    System.out.print("Enter price per day: $");
                    double pricePerDay = sc.nextDouble();
                    Date pickup = new Date(Long.parseLong(msg[1].trim()));
                    Date delivery = new Date(Long.parseLong(msg[2].trim()));

                    Car car = new Car(msg[0]);

                    Contract contract = new Contract(car, pickup, delivery, pricePerHour, pricePerDay);

                    String invoice = contract.toString();

                    System.out.println("< Invoice " + car.getModel().toLowerCase() + "/$" + String.format("%.2f",contract.getTotalAmount()) + " sent >");

                    c.send(clientSocket, invoice);


                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

                clientSocket.close();
            }
        }

    }

    public static boolean accept(){
        try{
            clientSocket = serverSocket.accept();
            return true;
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
