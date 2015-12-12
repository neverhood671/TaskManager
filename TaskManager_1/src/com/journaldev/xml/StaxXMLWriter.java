/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.xml;

/**
 *
 * @author Настя
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxXMLWriter {

    public static final String ROOT_ELEMENT_NAME = "root";
    private FileOutputStream out = null;
    private XMLEventWriter xmlEventWriter = null;
    private XMLEventFactory eventFactory = null;
    private XMLEvent end = null;

    public void initNewTasksFile(String fileName) {
        try {
            out = new FileOutputStream(fileName);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            xmlEventWriter = xmlOutputFactory
                    .createXMLEventWriter(out, "UTF-8");

            eventFactory = XMLEventFactory.newInstance();
            end = eventFactory.createDTD("\n");
            StartDocument startDocument = eventFactory.createStartDocument();
            xmlEventWriter.add(startDocument);
            xmlEventWriter.add(end);
            xmlEventWriter.add(eventFactory.createStartElement("", "", ROOT_ELEMENT_NAME));
            xmlEventWriter.add(end);
        } catch (XMLStreamException | FileNotFoundException e) {
           
        }
    }

    public void writeXML(String rootElement, Map<String, String> elementsMap) {
        try {
            if (out == null || xmlEventWriter == null) //todo:Настя add XMLException
            {
                throw new RuntimeException("XMLWriter not initialized!");
            }
            eventFactory = XMLEventFactory.newInstance();
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", rootElement);
            xmlEventWriter.add(configStartElement);
            xmlEventWriter.add(end);
            // Write the element nodes
            Set<String> elementNodes = elementsMap.keySet();
            for (String key : elementNodes) {
                createNode(xmlEventWriter, key, elementsMap.get(key));
            }

            xmlEventWriter.add(eventFactory.createEndElement("", "", rootElement));
            xmlEventWriter.add(end);
//            xmlEventWriter.add(eventFactory.createEndDocument());

        } catch (XMLStreamException e) {
        }
    }

    public void closeWriter() {
        try {
            if (out == null || xmlEventWriter == null) //todo:Настя add XMLException
            {
                throw new RuntimeException("XMLWriter not initialized!");
            }
            xmlEventWriter.add(eventFactory.createEndElement("", "", ROOT_ELEMENT_NAME));
            xmlEventWriter.add(eventFactory.createEndDocument());
            xmlEventWriter.close();
            xmlEventWriter = null;
            out.flush();
            out.close();
            out = null;
        } catch (XMLStreamException | IOException ex) {
        }
    }

    private static void createNode(XMLEventWriter eventWriter, String element,
            String value) throws XMLStreamException {
        XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();
        XMLEvent end = xmlEventFactory.createDTD("\n");
        XMLEvent tab = xmlEventFactory.createDTD("\t");
        //Create Start node
        StartElement sElement = xmlEventFactory.createStartElement("", "", element);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        //Create Content
        Characters characters = xmlEventFactory.createCharacters(value);
        eventWriter.add(characters);
        // Create End node
        EndElement eElement = xmlEventFactory.createEndElement("", "", element);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }
    public void writeXMLPostoneTime(String rootElement, int postTime) {
        try {
            if (out == null || xmlEventWriter == null) //todo:Настя add XMLException
            {
                throw new RuntimeException("XMLWriter not initialized!");
            }
            eventFactory = XMLEventFactory.newInstance();
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", rootElement);
            xmlEventWriter.add(configStartElement);
            xmlEventWriter.add(end);
                createNode(xmlEventWriter, rootElement, Integer.toString(postTime));
            xmlEventWriter.add(eventFactory.createEndElement("", "", rootElement));
            xmlEventWriter.add(end);
        } catch (XMLStreamException e) {
        }
    }
    
    public void writeXMLTask(String rootElement, Map<String, String> elementsMap) {
        try {
            if (out == null || xmlEventWriter == null) //todo:Настя add XMLException
            {
                throw new RuntimeException("XMLWriter not initialized!");
            }
            eventFactory = XMLEventFactory.newInstance();
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", rootElement);
            xmlEventWriter.add(configStartElement);
            xmlEventWriter.add(end);
            // Write the element nodes
            Set<String> elementNodes = elementsMap.keySet();
            for (String key : elementNodes) {
                createNode(xmlEventWriter, key, elementsMap.get(key));
            }

            xmlEventWriter.add(eventFactory.createEndElement("", "", rootElement));
            xmlEventWriter.add(end);
//            xmlEventWriter.add(eventFactory.createEndDocument());

        } catch (XMLStreamException e) {
        }
    }

}
