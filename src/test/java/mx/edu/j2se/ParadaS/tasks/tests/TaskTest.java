package mx.edu.j2se.ParadaS.tasks.tests;

import mx.edu.j2se.ParadaS.tasks.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testRepetitiveTask() throws Exception{
        //Repetitive Task
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
        //Repetitive Task changed to non Repetitive
        Task taskRep = new Task("Task",10,30,5);

        taskRep.setTitle("Task ahora repetitiva");
        taskRep.setTime(12);
        Assert.assertEquals("Task ahora repetitiva",taskRep.getTitle());
        Assert.assertEquals(false,taskRep.isRepeated());
        Assert.assertEquals(12,taskRep.getTime());

        taskRep.setActive(true);

        Assert.assertEquals(false,taskRep.isRepeated());
        Assert.assertEquals(-1,taskRep.nextTimeAfter(13));
        Assert.assertEquals(12,taskRep.nextTimeAfter(8));

    }
}