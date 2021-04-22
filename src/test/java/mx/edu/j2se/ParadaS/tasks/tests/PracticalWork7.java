package mx.edu.j2se.ParadaS.tasks.tests;

import mx.edu.j2se.ParadaS.tasks.ArrayTaskList;
import mx.edu.j2se.ParadaS.tasks.Task;
import mx.edu.j2se.ParadaS.tasks.Tasks;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import static mx.edu.j2se.ParadaS.tasks.Tasks.calendar;
import static mx.edu.j2se.ParadaS.tasks.Tasks.incoming;

public class PracticalWork7 {

    @Test
    public void testPracticalWork7() throws Exception{

        LocalDateTime prueba0 = LocalDateTime.of(2021, Month.APRIL, 29, 9, 0, 0);
        LocalDateTime prueba1 = LocalDateTime.of(2021, Month.APRIL, 29, 10, 0, 0);
        Task task1 = new Task("Task1",prueba1);
        LocalDateTime prueba2 = LocalDateTime.of(2021, Month.APRIL, 29, 10, 0, 0);
        Task task2 = new Task("Task2",prueba2);
        LocalDateTime prueba3 = LocalDateTime.of(2021, Month.APRIL, 29, 12, 0, 0);
        Task task3 = new Task("Task3",prueba3);
        LocalDateTime prueba4 = LocalDateTime.of(2021, Month.APRIL, 29, 14, 0, 0);
        Task task4 = new Task("Task4",prueba4);
        LocalDateTime prueba5 = LocalDateTime.of(2021, Month.APRIL, 29, 15, 0, 0);
        Task task5 = new Task("Task5",prueba5);
        LocalDateTime prueba6 = LocalDateTime.of(2021, Month.APRIL, 29, 18, 0, 0);
        Task task6 = new Task("Task6",prueba6);
        LocalDateTime prueba7 = LocalDateTime.of(2021, Month.APRIL, 29, 10, 0, 0);

        LocalDateTime start1 = LocalDateTime.of(2021, Month.APRIL, 29, 10, 0, 0);
        LocalDateTime end1 = LocalDateTime.of(2021, Month.APRIL, 29, 14, 0, 0);
        LocalDateTime interval1 = LocalDateTime.of(2021, Month.APRIL, 29, 1, 0, 0);
        Task task7 = new Task("Task7",start1,end1,interval1);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);
        task5.setActive(true);
        task6.setActive(true);
        task7.setActive(true);

        ArrayTaskList arrayPrueba = new ArrayTaskList();

        arrayPrueba.add(task1);
        arrayPrueba.add(task2);
        arrayPrueba.add(task3);
        arrayPrueba.add(task4);
        arrayPrueba.add(task5);
        arrayPrueba.add(task6);
        arrayPrueba.add(task7);

        Iterable<Task> pruebaIncoming = incoming(arrayPrueba,prueba7,prueba5);

        System.out.println(pruebaIncoming.toString());

        SortedMap<LocalDateTime, Set<Task>> pruebaCalendar = calendar(arrayPrueba,prueba7,prueba5);

        System.out.println(pruebaCalendar.toString());


    }


}
