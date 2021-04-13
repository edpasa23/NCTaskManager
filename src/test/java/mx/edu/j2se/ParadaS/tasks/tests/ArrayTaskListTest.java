package mx.edu.j2se.ParadaS.tasks.tests;

import mx.edu.j2se.ParadaS.tasks.ArrayTaskList;
import mx.edu.j2se.ParadaS.tasks.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ArrayTaskListTest {

    @Test
    public void ArrayTaskListTest() throws Exception{
        //Tasks
        Task task1 = new Task("Task1",1,10,1);
        Task task2 = new Task("Task2",2,12,2);
        Task task3 = new Task("Task3",7);
        Task task4 = new Task("Task4",9);

        //Array
        ArrayTaskList arrayT = new ArrayTaskList();

        arrayT.add(task1);
        arrayT.add(task2);
        arrayT.add(task3);
        arrayT.add(task4);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);

        Assert.assertEquals(4,arrayT.size());

        arrayT.remove(task2);

        Assert.assertEquals("Task3",arrayT.getTask(1).getTitle());

        ArrayTaskList test = arrayT.incoming(0,15);

        Assert.assertEquals(3,test.size());

    }

}
