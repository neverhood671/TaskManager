/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.util.Comparator;
import taskmanager.Task;

/**
 *
 * @author Александра
 */
public class TaskDataComparator implements Comparator<Task>{

    @Override
    public int compare(Task o1, Task o2) {
         return o1.getTime().compareTo(o2.getTime());
    }
    
}
