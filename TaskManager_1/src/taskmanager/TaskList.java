/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Александра
 */
public class TaskList implements Serializable{

    private static volatile TaskList instance;

    private List<Task> taskList;

    private TaskList() throws FileNotFoundException, IOException, ParseException, XMLStreamException {
        //BufferedReader reader = new BufferedReader(new FileReader("out.txt"));
        //instance = WorkTaskList.readTaskList(reader);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory
                .createXMLEventReader(new FileReader("C:/Users/Александра/Desktop/лаб2/taskList.xml"));
        instance = WorkTaskList.readTaskListXML(eventReader);
        taskList = instance.getTaskList();
    }
//C:/Users/Александра/Desktop/лаб2/taskList.xml
    public static TaskList getInstance() throws IOException, FileNotFoundException, ParseException, XMLStreamException {
        TaskList localInstance = instance;
        if (localInstance == null) {
            synchronized (TaskList.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TaskList();
                }
            }
        }
        return localInstance;
    }

    public TaskList(Task[] tasks) {
        taskList = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            taskList.add(tasks[i]);
        }
        Collections.sort(taskList, new TaskDataComparator());
    }

    public Task getTaskByIndex(int index) {
        return taskList.get(index);
    }

    public void setTaskByIndex(int index, Task task) {
        this.taskList.set(index, task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void sortTaskList() {
        Collections.sort(taskList, new TaskDataComparator());
    }

    public int getTaskCount() {
        int value = taskList.size();
        synchronized (TaskList.class) {
            return value;
        }
    }

    public String toStringTime(Date time) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        sb.append(format1.format(time));
        return sb.toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.getTaskCount(); i++) {
            str.append(this.getTaskByIndex(i).toString());
        }
        return str.toString();
    }
}
