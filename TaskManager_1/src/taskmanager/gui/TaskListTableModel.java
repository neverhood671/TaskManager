/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.gui;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.XMLStreamException;
import taskmanager.*;

/**
 *
 * @author Настя
 */
public class TaskListTableModel extends DefaultTableModel {

  
    private Component parent;
    ClientWorkTaskList wl;
    public Task[] box;

    public TaskListTableModel(Component parent) throws ParseException, IOException, FileNotFoundException, XMLStreamException {
        this.wl = new ClientWorkTaskList();
        this.parent = parent;
        box = new Task[wl.getTaskList().getTaskCount()];
    }
    
    public int getRowCount() {
        try {
            try {
                if (TaskList.getInstance()!= null) {
                    return TaskList.getInstance().getTaskCount();
                } else {
                    return 0;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }return 0;
    }
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case ClassConst.TASK_TIME_INDEX:
                return "Date&Time";
            case ClassConst.TASK_NAME_INDEX:
                return "Task Name";
            case ClassConst.TASK_CONTACTS_INDEX:
                return "Contacts";
            case ClassConst.TASK_DESCRIPTION_INDEX:
                return "Description";
            default:
                return "Undefined";
        }
    }

    /**
     *
     * @param columnIndex
     * @return
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ClassConst.TASK_TIME_INDEX:
                return String.class;
            case ClassConst.TASK_NAME_INDEX:
                return String.class;
            case ClassConst.TASK_CONTACTS_INDEX:
                return String.class;
            case ClassConst.TASK_DESCRIPTION_INDEX:
                return String.class;
            default:
                return null;
        }
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return row <= this.getRowCount() && column <= this.getColumnCount();
    }

    @Override
    public Object getValueAt(int row, int column){
        switch (column) {
            case ClassConst.TASK_TIME_INDEX:
        {
            try {
                try {
                    return wl.getTaskList().getTaskByIndex(row).toStringTime(wl.getTaskList().getTaskByIndex(row).getTime());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (XMLStreamException ex) {
                    Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            case ClassConst.TASK_NAME_INDEX:
        {
            try {
                try {
                    return wl.getTaskList().getTaskByIndex(row).getName();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (XMLStreamException ex) {
                    Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            case ClassConst.TASK_CONTACTS_INDEX:
        {
            try {
                return wl.getTaskList().getTaskByIndex(row).getContacts();
            } catch (IOException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            case ClassConst.TASK_DESCRIPTION_INDEX:
        {
            try {
                return wl.getTaskList().getTaskByIndex(row).getDescription();
            } catch (IOException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        try {
            Date data=new Date();
            if (column ==0){
                String stringDateFormat = "dd.MM.yyyy HH:mm";
                SimpleDateFormat format = new SimpleDateFormat(stringDateFormat);
                Date date = format.parse((String) aValue);
                if (check((String)aValue)==false|| (data.compareTo(date)>0)){
                    JOptionPane.showMessageDialog(parent, "Incorrect data! Please, try again!");
                }else{  
                    wl.updateString(row,column,(String) aValue);
                    JOptionPane.showMessageDialog(parent, "The changes are saved!");
                }
            }else{
                    wl.updateString(row,column,(String) aValue);
                    JOptionPane.showMessageDialog(parent, "The changes are saved!");
                }
            //wl.updateString(row,column,(String) aValue);      
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(parent, "Incorrect data! Please, try again!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }            
    } 
    
    public void removeRow(int index, int boxIndex) throws FileNotFoundException, XMLStreamException{
        try {
            box[boxIndex] = wl.getTaskList().getTaskByIndex(index);
        } catch (IOException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TaskListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        wl.deleteTask(index);   
    }
    
    public boolean check(String date){
        char[] e = date.toCharArray();
        int dd= Character.getNumericValue(e[0])*10+Character.getNumericValue(e[1]),
                mm=Character.getNumericValue(e[3])*10+Character.getNumericValue(e[4]), 
                yy=Character.getNumericValue(e[6])*1000+Character.getNumericValue(e[7])*100+Character.getNumericValue(e[8])*
                10+Character.getNumericValue(e[9]),
                hh=Character.getNumericValue(e[11])*10+Character.getNumericValue(e[12]), 
                min=Character.getNumericValue(e[14])*10+Character.getNumericValue(e[15]);
        
        if (dd>31|| dd<0|| mm>12||mm<0||yy>2017||hh>24||hh<0||min>59||min<0|| e.length!=16){
            return false;
        } else {  
            return true;
        }
    }
    

}
