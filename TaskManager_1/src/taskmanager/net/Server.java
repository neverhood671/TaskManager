/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.net;

import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author Александра
 */
public class Server {
    public static void main(String[] args) throws IOException {
    
            ServerSocket ss = new ServerSocket(1069, 0,
                    InetAddress.getByName("127.0.0.1"));
            System.out.println("Server is running ");
            Socket socket = ss.accept();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()) ;
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    }      
}
