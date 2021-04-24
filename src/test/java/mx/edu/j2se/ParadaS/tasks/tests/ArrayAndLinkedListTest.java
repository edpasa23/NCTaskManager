package mx.edu.j2se.ParadaS.tasks.tests;
import mx.edu.j2se.ParadaS.tasks.ArrayTaskList;
import mx.edu.j2se.ParadaS.tasks.LinkedTaskList;
import mx.edu.j2se.ParadaS.tasks.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;

public class ArrayAndLinkedListTest {

        LocalDateTime timeNoRep = LocalDateTime.of(2021, Month.APRIL, 29, 10, 0);
        Task taskNoRep = new Task("Task Non-repetitive",timeNoRep);
        LocalDateTime startRep = LocalDateTime.of(2021,Month.APRIL, 29, 10, 0);
        LocalDateTime endRep = LocalDateTime.of(2021,Month.APRIL, 29, 12, 0);
        LocalDateTime intervalRep = LocalDateTime.of(2021,Month.APRIL, 29, 0, 10);
        Task taskRep = new Task("Task repetitive",startRep,endRep,intervalRep);

        @Test
        public void addTest() throws Exception{
            LinkedTaskList taskList = new LinkedTaskList();
            ArrayTaskList taskArray = new ArrayTaskList();

            taskList.add(taskRep);
            taskList.add(taskNoRep);
            taskArray.add(taskRep);
            taskArray.add(taskNoRep);
            taskList.add(taskRep);
            taskList.add(taskNoRep);
            taskArray.add(taskRep);
            taskArray.add(taskNoRep);

            System.out.println("Size list: "+taskList.size());
            System.out.println("Array list: "+taskArray.size());

            taskArray.remove(taskRep);

            System.out.println("Size list: "+taskList.size());
            System.out.println("Array list: "+taskArray.size());

            taskList.remove(taskNoRep);

            System.out.println("Size list: "+taskList.size());
            System.out.println("Array list: "+taskArray.size());

            taskArray.remove(taskNoRep);

            System.out.println("Size list: "+taskList.size());
            System.out.println("Array list: "+taskArray.size());

            taskList.remove(taskRep);

            System.out.println("Size list: "+taskList.size());
            System.out.println("Array list: "+taskArray.size());

        }
}
