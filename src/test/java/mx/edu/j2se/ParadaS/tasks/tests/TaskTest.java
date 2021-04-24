package mx.edu.j2se.ParadaS.tasks.tests;

import mx.edu.j2se.ParadaS.tasks.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;


public class TaskTest {

    LocalDateTime timeNoRep = LocalDateTime.of(2021,Month.APRIL, 29, 10, 0);
    Task taskNoRep = new Task("Task Non-repetitive",timeNoRep);
    LocalDateTime startRep = LocalDateTime.of(2021,Month.APRIL, 29, 10, 0);
    LocalDateTime endRep = LocalDateTime.of(2021,Month.APRIL, 29, 12, 0);
    LocalDateTime intervalRep = LocalDateTime.of(2021,Month.APRIL, 29, 0, 10);
    Task taskRep = new Task("Task repetitive",startRep,endRep,intervalRep);

    @Test
    public void gettersTest() throws Exception{

        System.out.println("Task non repetitive parameters:");

        System.out.println("Titulo: "+taskNoRep.getTitle());
        System.out.println("Start: "+taskNoRep.getStartTime());
        System.out.println("End: "+taskNoRep.getEndTime());
        System.out.println("Interval: "+taskNoRep.getRepeatInterval());
        System.out.println("Time: "+taskNoRep.getTime());
        System.out.println("Active: "+taskNoRep.isActive());
        System.out.println("Repeated: "+taskNoRep.isRepeated());

        System.out.println("\nTask repetitive parameters:");

        System.out.println("Titulo: "+taskRep.getTitle());
        System.out.println("Start: "+taskRep.getStartTime());
        System.out.println("End: "+taskRep.getEndTime());
        System.out.println("Interval: "+taskRep.getRepeatInterval());
        System.out.println("Time: "+taskRep.getTime());
        System.out.println("Active: "+taskRep.isActive());
        System.out.println("Repeated: "+taskRep.isRepeated());

    }

    @Test
    public void nextTimeTest() throws Exception{

        LocalDateTime current = LocalDateTime.of(2021,Month.APRIL, 29, 11, 1);
        taskRep.setActive(true);
        taskNoRep.setActive(true);
        System.out.println(taskNoRep.nextTimeAfter(current));
        System.out.println(taskRep.nextTimeAfter(current));

    }

    @Test
    public void toStringTest() throws Exception{
        System.out.println(taskRep.toString());
        System.out.println(taskNoRep.toString());
    }

}
