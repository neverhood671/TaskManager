/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import taskmanager.Task;
import taskmanager.TaskList;

/**
 *
 * @author Настя
 */
public class StaxXMLReader {

    public static final String ELEMENT_ROOT = "root";
    public static final String ELEMENT_TASK = "task";
    public static final String ELEMENT_DESCRIPTION = "description";
    public static final String ELEMENT_NAME = "name";
    public static final String ELEMENT_CONTACTS = "contacts";
    public static final String ELEMENT_TIME = "time";

    public static TaskList readXML(XMLEventReader eventReader) throws XMLStreamException, ParseException, FileNotFoundException, IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileReader("C:/Users/Александра/Desktop/лаб2/taskList.xml"));
//        String[] atr = new String[4];
//        int i = 0;
        //jhvv
        try {
            int event = r.getEventType();

            XmlTask cuttentTask = null;
            while (r.hasNext()) {
                event = r.next();

                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        for (int index = 0, n = r.getAttributeCount(); index < n; ++index) {
                            System.out.println("Attribute: " + r.getAttributeName(index)
                                    + "=" + r.getAttributeValue(index));
                        }
                        switch (r.getName().toString()) {
                            case ELEMENT_TASK:
                                cuttentTask = new XmlTask();
                                break;
                            case ELEMENT_NAME:
                                r.next();
                                cuttentTask.name = r.getText();
                                r.next();
                                break;
                            case ELEMENT_DESCRIPTION:
                                r.next();
                                cuttentTask.description = r.getText();
                                r.next();
                                break;
                            case ELEMENT_TIME:
                                r.next();
                                cuttentTask.time = Task.toDateTime(r.getText());
                                r.next();
                                break;
                            case ELEMENT_CONTACTS:
                                r.next();
                                cuttentTask.contacts = r.getText();
                                r.next();
                                break;
                        }
                        break;
//                    case XMLStreamConstants.CHARACTERS:
//                        if (r.isWhiteSpace()) {
//                            break;
//                        }
//                        atr[i] = r.getText();
//                        i++;
//                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (ELEMENT_TASK.equals(r.getName().toString())) {
                            taskList.add(new Task(cuttentTask.name,
                                    cuttentTask.description,
                                    cuttentTask.time,
                                    cuttentTask.contacts));
                            cuttentTask = null;
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }

//                if (i > 3) {
//                    Task task = new Task(atr[2], atr[1], Task.toDateTime(atr[0]), atr[3]);
//                    i = 0;
//                    taskList.add(task);
//                    //System.out.println(task);
//                }

            }
        } finally {
            r.close();
        }
        Task[] task = new Task[taskList.size()];

        for (int j = 0; j < taskList.size(); j++) {
            task[j] = taskList.get(j);
        }
        //System.out.println(new TaskList(task));
        return new TaskList(task);
    }
    
    public static int readXMLPostoneTime(XMLEventReader eventReader) throws XMLStreamException, ParseException, FileNotFoundException, IOException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileReader("C:/Users/Александра/Desktop/лаб2/postoneTime.xml"));

        int cuttentTime;
        try {
            int event = r.getEventType();

             cuttentTime = 0;
             while (r.hasNext()) {
                event = r.next();

                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        for (int index = 0, n = r.getAttributeCount(); index < n; ++index) {
                            System.out.println("Attribute: " + r.getAttributeName(index)
                                    + "=" + r.getAttributeValue(index));
                        }
                        if (r.getName().toString()==ELEMENT_TIME){
                                r.next();
                                cuttentTime = Integer.parseInt(r.getText());
                        }
                            
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }
             }
            
        } finally {
            r.close();
        }
        return cuttentTime;
    }
    
    private static class XmlTask {
        public String name;
        public String description;
        public Date time;
        public String contacts;
    }

    public static Task readXMLTask(XMLEventReader eventReader) throws XMLStreamException, ParseException, FileNotFoundException{
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileReader("C:/Users/Александра/Desktop/лаб2/task.xml"));
        XmlTask cuttentTask = null;
        try {
            int event = r.getEventType();

            
            while (r.hasNext()) {
                event = r.next();

                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        for (int index = 0, n = r.getAttributeCount(); index < n; ++index) {
                            System.out.println("Attribute: " + r.getAttributeName(index)
                                    + "=" + r.getAttributeValue(index));
                        }
                        switch (r.getName().toString()) {
                            case ELEMENT_TASK:
                                cuttentTask = new XmlTask();
                                break;
                            case ELEMENT_NAME:
                                r.next();
                                cuttentTask.name = r.getText();
                                r.next();
                                break;
                            case ELEMENT_DESCRIPTION:
                                r.next();
                                cuttentTask.description = r.getText();
                                r.next();
                                break;
                            case ELEMENT_TIME:
                                r.next();
                                cuttentTask.time = Task.toDateTime(r.getText());
                                r.next();
                                break;
                            case ELEMENT_CONTACTS:
                                r.next();
                                cuttentTask.contacts = r.getText();
                                r.next();
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
//                        if (ELEMENT_TASK.equals(r.getName().toString())) {
//                            task new Task(cuttentTask.name,
//                                    cuttentTask.description,
//                                    cuttentTask.time,
//                                    cuttentTask.contacts));
//                            cuttentTask = null;
//                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }
            }
        } finally {
            r.close();
        }
//        Task[] task = new Task[taskList.size()];
//
//        for (int j = 0; j < taskList.size(); j++) {
//            task[j] = taskList.get(j);
//        }
        //System.out.println(new TaskList(task));
        return new Task(cuttentTask.name,cuttentTask.description,cuttentTask.time, cuttentTask.contacts);
    }
}
