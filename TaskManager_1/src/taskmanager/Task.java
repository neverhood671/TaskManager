/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
/**
 *
 * @author Настя
 */
public class Task implements Comparable<Task>, Serializable{
        private String name;
        private String description;
        private Date time;
        private String contacts;        

        public Task(String name, String description, Date time, String contacts) {
            this.name = name;
            this.description = description;
            this.time = time;
            this.contacts = contacts;
        }
        public Task(String name, String description, String time, String contacts) throws ParseException {
            this.name = name;
            this.description = description;
            String stringDateFormat = "dd.MM.yyyy HH:mm";
            SimpleDateFormat format = new SimpleDateFormat(stringDateFormat);
            Date date = format.parse(time);
            this.time = date;
            this.contacts = contacts;
        }
        
        public String getDescription(){
            return this.description;
        }     
        
        public Date getTime(){
            return this.time;
        } 
        
        public String getName(){
            return this.name;
        } 
        
        public String getContacts(){
            return this.contacts;
        } 
        
        public void setName(String name){
            this.name = name; 
        }
        
        public void setDescription(String description){
            this.description = description; 
        }
        
        public void setTime(Date time){
            this.time = time; 
        }
        public void setTime(String time){
            try {
                String stringDateFormat = "dd.MM.yyyy HH:mm";
                SimpleDateFormat format = new SimpleDateFormat(stringDateFormat);
                Date date = format.parse(time); 
                this.time = date;
            } catch (ParseException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void setContacts(String contacts){
            this.contacts = contacts; 
        }
        
        public int compareTo(Task o){
            return this.time.compareTo(o.time);
        }
        public String toStringTime(Date time){
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            sb.append(format1.format(time));
            return sb.toString();
        }
        
       public static Date toDateTime(String time) throws ParseException{
                String stringDateFormat = "dd.MM.yyyy HH:mm";
                SimpleDateFormat format = new SimpleDateFormat(stringDateFormat);
                return format.parse(time);
        }
        
        public String toString(){
            StringBuilder str = new StringBuilder();
        
            str.append("Name: ");
            str.append(this.getName());
             str.append("Descriptin: ");
            str.append(this.getDescription());
             str.append("Time: ");
            str.append(this.getTime());
             str.append("Contacts: ");
            str.append(this.getContacts());
        
            return str.toString();
        }
    
}