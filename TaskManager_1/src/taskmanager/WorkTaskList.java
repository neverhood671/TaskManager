/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import com.journaldev.xml.*;
import com.journaldev.xml.StaxXMLWriter;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Александра
 */
public class WorkTaskList implements Serializable, WTLInterface {

    private boolean changed = false;

   // private static volatile TaskList instance;
     
    public WorkTaskList() throws ParseException, IOException, FileNotFoundException, XMLStreamException {
        TaskList.getInstance();
    }

    public static void writeTaskList(Writer out) throws FileNotFoundException, XMLStreamException {
        try {
            PrintWriter pw = new PrintWriter(out);
            pw.print(TaskList.getInstance().getTaskList().size());
            pw.println("");
            for (int i = 0; i < TaskList.getInstance().getTaskList().size(); i++) {
                pw.print(TaskList.getInstance().getTaskByIndex(i).getName());
                pw.println("");
                pw.print(TaskList.getInstance().getTaskByIndex(i).getDescription());
                pw.println("");
                pw.print(TaskList.getInstance().toStringTime(TaskList.getInstance().getTaskByIndex(i).getTime()));
                pw.println("");
                pw.print(TaskList.getInstance().getTaskByIndex(i).getContacts());
                pw.println("");
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static TaskList readTaskList(Reader in) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(in);
        int size = Integer.parseInt(reader.readLine());
        Task[] tl = new Task[size];
        for (int i = 0; i < size; i++) {
            String name = reader.readLine();
            String description = reader.readLine();
            String date = reader.readLine();
            String contacts = reader.readLine();
            tl[i] = new Task(name, description, date, contacts);
        }
        return new TaskList(tl);

    }

    public void deleteTask(int index) throws FileNotFoundException, XMLStreamException {
        try {
            if (index < 0 || index > 1 + TaskList.getInstance().getTaskList().size()) {
                throw new IndexOutOfBoundsException();
            }
            TaskList.getInstance().getTaskList().remove(index);
            TaskList.getInstance().sortTaskList();
            writeTaskListXML(TaskList.getInstance());
            // writeTaskList(new PrintWriter("out.txt"));
        } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        changed = true;
    }

    public void deleteTask(Task task) throws FileNotFoundException, XMLStreamException {
        try {
            TaskList.getInstance().getTaskList().remove(task);
            TaskList.getInstance().sortTaskList();
            writeTaskListXML(TaskList.getInstance());
            //writeTaskList(new PrintWriter("out.txt"));
        } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        changed = true;
    }

    public void addTask(Task task) throws FileNotFoundException, XMLStreamException {
        try {
            TaskList.getInstance().getTaskList().add(task);
            TaskList.getInstance().sortTaskList();
            //writeTaskList(new PrintWriter("out.txt"));
            writeTaskListXML(TaskList.getInstance());
            } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        changed = true;
    }

    public void updateTask(int index, Task task) throws FileNotFoundException, XMLStreamException {
        try {
            TaskList.getInstance().setTaskByIndex(index, task);
            TaskList.getInstance().sortTaskList();
            //writeTaskList(new PrintWriter("out.txt"));
            writeTaskListXML(TaskList.getInstance());
            
        } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        changed = true;
    }

    public void updateString(int index, int index2, String name) throws FileNotFoundException, XMLStreamException {

        try {
            if (index2 == ClassConst.TASK_NAME_INDEX) {
                TaskList.getInstance().getTaskByIndex(index).setName(name);
            }
            if (index2 == ClassConst.TASK_DESCRIPTION_INDEX) {
                TaskList.getInstance().getTaskByIndex(index).setDescription(name);
            }
            if (index2 == ClassConst.TASK_CONTACTS_INDEX) {
                TaskList.getInstance().getTaskByIndex(index).setContacts(name);
            }
            if (index2 == ClassConst.TASK_TIME_INDEX) {
                TaskList.getInstance().getTaskByIndex(index).setTime(name);
            }
            TaskList.getInstance().sortTaskList();

            //writeTaskList(new PrintWriter("out.txt"));
            writeTaskListXML(TaskList.getInstance());
            
        } catch (IOException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WorkTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        changed = true;
    }

    public boolean isModified() {
        return changed;
    }

    public void setModified(boolean changed) {
        this.changed = changed;
    }

    /**
     *
     * @param task
     * @param delayTime 1 = 1 МИНУТЕ (юзать константы класса ClassConst)
     */
    public void postponeTask(Task task, Long delayTime) {
        Long newDate = task.getTime().getTime();
        newDate = newDate + (60 * 1000 * delayTime);
        task.setTime(new Date(newDate));
        //ClassConst.TIMER=(int) (60 * 1000 * delayTime);
    }

    public static void writeTaskListXML(TaskList taskList) throws FileNotFoundException, XMLStreamException, IOException {

        String fileName = "C:/Users/Александра/Desktop/лаб2/taskList.xml";

        StaxXMLWriter xmlWriter = new StaxXMLWriter();
        xmlWriter.initNewTasksFile(fileName);
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            String rootElement = "task";
            Map<String, String> elementsMap = new HashMap<>();
            elementsMap.put("name", taskList.getTaskByIndex(i).getName());
            elementsMap.put("description", taskList.getTaskByIndex(i).getDescription());
            elementsMap.put("time", taskList.getTaskByIndex(i)
                    .toStringTime(taskList.getTaskByIndex(i).getTime()));
            elementsMap.put("contacts", taskList.getTaskByIndex(i).getContacts());

            xmlWriter.writeXML(rootElement, elementsMap);
        }
        xmlWriter.closeWriter();

    }
    public static void writeTaskListXMLPostoneTime(int posTime) throws FileNotFoundException, XMLStreamException, IOException {

        String fileName = "C:/Users/Александра/Desktop/лаб2/postoneTime.xml";

        StaxXMLWriter xmlWriter = new StaxXMLWriter();
        xmlWriter.initNewTasksFile(fileName);
        
            String rootElement = "time";
            xmlWriter.writeXMLPostoneTime(rootElement, posTime);
        
        xmlWriter.closeWriter();

    }
public static void writeTasXML(Task task) throws FileNotFoundException, XMLStreamException, IOException {

        String fileName = "C:/Users/Александра/Desktop/лаб2/task.xml";

        StaxXMLWriter xmlWriter = new StaxXMLWriter();
        xmlWriter.initNewTasksFile(fileName);
            String rootElement = "task";
            Map<String, String> elementsMap = new HashMap<>();
            elementsMap.put("name", task.getName());
            elementsMap.put("description", task.getDescription());
            elementsMap.put("time", task.toStringTime(task.getTime()));
            elementsMap.put("contacts", task.getContacts());

            xmlWriter.writeXML(rootElement, elementsMap);
        xmlWriter.closeWriter();

    }
    
    public static TaskList readTaskListXML(XMLEventReader eventReader) throws XMLStreamException, ParseException, FileNotFoundException, IOException {
        return StaxXMLReader.readXML(eventReader);
    }
 public static int readTaskListXMLPostoneTime(XMLEventReader eventReader) throws XMLStreamException, ParseException, FileNotFoundException, IOException {
        return StaxXMLReader.readXMLPostoneTime(eventReader);
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, XMLStreamException {
        WorkTaskList.writeTaskListXML(TaskList.getInstance());
    }

}
