/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;


import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.stream.XMLStreamException;
import taskmanager.gui.MainFrame;


/**
 *
 * @author Александра
 */
public class TaskManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, InterruptedException, AWTException, XMLStreamException {
       
        //TimerForNotification tim= new TimerForNotification();
        new MainFrame().setVisible(true);
        //tim.OnTimer();
        
    }
}


// TODO code application logic here

       //Task task1 = new Task("Покупки", "масло, хлеб", "29.10.2015 21:00"  ,"-" );
//       Task task2 = new Task("Уборка", "помыть пол", "30.10.2015 12:20"  ,"-" );        
      // Task task3 = new Task("Уроки", "Выучить математику", "30.10.2015 11:45"  ,"-" );
      //  WorkTaskList.writeTaskList( new PrintWriter("out.txt"));
//        System.out.println(TaskList.getInstance().getTaskCount());
//        Task task4 = new Task("Звонок", "Позвонить сестре", "29.10.2015 22:30"  ,"89177448596" );
//        System.out.println(task1.getTime());
//        TaskList tl1= new TaskList(new Task[] {task1,task2,task4});
//        System.out.println(tl1.getTaskByIndex(0).getTime().toString());
//                WorkTaskList.writeTaskList(tl1, new PrintWriter("out.txt"));
//        BufferedReader reader = new BufferedReader(new FileReader("out.txt"));
//      TaskList tl = WorkTaskList.readTaskList(reader); 
//         WorkTaskList.writeTaskList(tl, new PrintWriter("out1.txt"));
//        tl1.setTaskByIndex(1, task3);
//        WorkTaskList tl3= new WorkTaskList(tl1);
//        tl3.addTask(task2);
//        //tl3.deleteTaskByIndex(0);
//        WorkTaskList.writeTaskList(tl1, new PrintWriter("out.txt"));/*
//        Task task1 = new Task(" ", " ", "29.10.2015 11:00", " ");
//        Task task2 = new Task(" ", " ", "20.10.2015 21:00", " ");
//        Task task3 = new Task(" ", " ", "29.10.2015 22:00", " ");
//        Task task4 = new Task(" ", " ", "30.10.2015 22:00", " ");
//        Task task5 = new Task(" ", " ", "19.10.2015 15:00", " ");
//        TaskList tl1= new TaskList(new Task[] {task1,task2,task3, task4,task5});
//         WorkTaskList.writeTaskList(tl1, new PrintWriter("out2.txt"));
//      tl1.sortTaskList();*/
//         WorkTaskList.writeTaskList(tl, new PrintWriter("out1.txt"));
       //TaskAbautFrame f =  new TaskAboutFrame(task1);
       //f.setVisible(true);
  // MainFrame mainFrame = new MainFrame();

