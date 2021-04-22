package mx.edu.j2se.ParadaS.tasks.tests;

import mx.edu.j2se.ParadaS.tasks.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAmount;

public class TaskTest {

    @Test
    public void testRepetitiveTask() throws Exception{
  /*      //Repetitive Task
        Task taskRep = new Task("Task",10,30,5);

        Assert.assertEquals(10,taskRep.getStartTime());
        Assert.assertEquals(30,taskRep.getEndTime());
        Assert.assertEquals(5,taskRep.getRepeatInterval());
        Assert.assertEquals(true,taskRep.isRepeated());
        Assert.assertEquals(false,taskRep.isActive());

        taskRep.setTime(12,25,3);

        Assert.assertEquals(12,taskRep.getStartTime());
        Assert.assertEquals(25,taskRep.getEndTime());
        Assert.assertEquals(3,taskRep.getRepeatInterval());

        taskRep.setActive(true);

        Assert.assertEquals(true,taskRep.isRepeated());
        Assert.assertEquals(15,taskRep.nextTimeAfter(13));
    }

    @Test
    public void testRepetitiveToNonRepetitive() throws Exception{
        //Repetitive Task that will ve changed to non Repetitive
        Task taskRep = new Task("Task",10,30,5);

        String prueba = taskRep.toString();

        taskRep.setTitle("Task nuevo nombre");
        taskRep.setTime(12);
        Assert.assertEquals("Task nuevo nombre",taskRep.getTitle());
        Assert.assertEquals(false,taskRep.isRepeated());
        Assert.assertEquals(12,taskRep.getTime());


        taskRep.setActive(true);

        Assert.assertEquals(false,taskRep.isRepeated());
        Assert.assertEquals(-1,taskRep.nextTimeAfter(13));
        Assert.assertEquals(12,taskRep.nextTimeAfter(8));

        String prueba2 = taskRep.toString();
        System.out.println(prueba);
        System.out.println(prueba2);

    }

    @Test
    public void cloneTest() throws Exception{
        Task taskOriginal = new Task("Original",10,30,5);


        Task taskClone = (Task) taskOriginal.clone();
        System.out.println(taskOriginal.hashCode());
        System.out.println(taskClone.hashCode());

        taskClone.setTitle("nuevoTitle");

        String prueba = taskOriginal.toString();
        String prueba2 = taskClone.toString();

        System.out.println(prueba);
        System.out.println(prueba2);
        System.out.println(taskOriginal.hashCode());
        System.out.println(taskClone.hashCode());*/
    }

    @Test
    public void testLocalDate() throws Exception{

        LocalDateTime prueba = LocalDateTime.of(2021,Month.APRIL, 29, 10, 10, 00);
        Task taskNoRep = new Task("Task",prueba);
        System.out.println("Titulo: "+taskNoRep.getTitle());
        System.out.println("Time: "+taskNoRep.getTime());
        System.out.println("Interval: "+taskNoRep.getRepeatInterval());
        System.out.println("String: "+taskNoRep.toString());

        Task taskClone = (Task)taskNoRep.clone();

        System.out.println("Titulo clon: "+taskClone.getTitle());
        System.out.println("Time clon: "+taskClone.getTime());
        System.out.println("Interval clon: "+taskClone.getRepeatInterval());
        System.out.println("String clon: "+taskClone.toString());

        System.out.println("equals clone y original: "+taskNoRep.equals(taskClone));

        LocalDateTime inicio = LocalDateTime.of(2021,Month.APRIL, 1, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2021,Month.APRIL, 30, 1, 0, 0);
        LocalDateTime intervalo = LocalDateTime.of(1,1, 1, 1, 0, 0);
        System.out.println("Repetitiva "+taskClone.isRepeated());

        taskClone.setTime(inicio,fin,intervalo);

        System.out.println("Repetitiva "+taskClone.isRepeated());
        System.out.println("Titulo rep: "+taskClone.getTitle());
        System.out.println("Time rep: "+taskClone.getTime());
        System.out.println("Inicio rep: "+taskClone.getStartTime());
        System.out.println("Fin rep: "+taskClone.getEndTime());
        System.out.println("Interval rep: "+taskClone.getRepeatInterval());
        System.out.println("String rep: "+taskClone.toString());

        System.out.println("equals clone y original: "+taskNoRep.equals(taskClone));
        taskClone.setActive(true);

        System.out.println("next time 1: "+taskClone.nextTimeAfter(LocalDateTime.of(2021,Month.APRIL, 1, 3, 0, 0)));
        System.out.println("next time 2: "+taskClone.nextTimeAfter(LocalDateTime.of(2021,Month.APRIL, 29, 23, 45, 1)));
        System.out.println("next time 3: "+taskClone.nextTimeAfter(LocalDateTime.of(2021,Month.APRIL, 30, 1, 0, 0)));
    }

}
