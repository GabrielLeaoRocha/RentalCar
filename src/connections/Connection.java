package connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {

    public static void send(Socket socket, String msg){
        OutputStream out;
        try{
            out = socket.getOutputStream();
            out.write(msg.getBytes());
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String recive(Socket socket){
        InputStream in;
        String txt = "";
        byte [] msg = new byte[100];
        int qtdBytes;
        try{
            in = socket.getInputStream();
            qtdBytes = in.read(msg);
            if(qtdBytes > 0){
                txt = new String(msg);
            }
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return txt;
    }
}
