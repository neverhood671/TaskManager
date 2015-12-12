/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import static taskmanager.WorkTaskList.writeTaskListXML;

/**
 *
 * @author Александра
 */
public interface WTLInterface {
    
    public void deleteTask(Task task) throws FileNotFoundException, XMLStreamException;

    public void addTask(Task task) throws FileNotFoundException, XMLStreamException ;

    public void updateTask(int index, Task task) throws FileNotFoundException, XMLStreamException;

    public void updateString(int index, int index2, String name) throws FileNotFoundException, XMLStreamException ;
    
 
}
