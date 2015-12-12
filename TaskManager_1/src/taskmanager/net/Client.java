/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.net;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import javax.xml.stream.XMLStreamException;
import taskmanager.gui.MainFrame;

/**
 *
 * @author Александра
 */
public class Client {
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, InterruptedException, AWTException, XMLStreamException {
//        String fileTask = "fileTask.txt";//args[0];
//        String fileCommand = "fileCommand.txt";//args[1];
//        String fileTaskList = "fileTaskList.txt";//args[2];
        Socket socket = new Socket("127.0.0.1", 1069);
//            PrintWriter outTask = new PrintWriter(new FileWriter(fileTask));
//            PrintWriter outCommand =  new PrintWriter(new FileWriter(fileCommand));
//            BufferedReader readTaskList = new BufferedReader(new FileReader(fileTaskList));
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());    
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        new MainFrame().setVisible(true);
        
    }
}
