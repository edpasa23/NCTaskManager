package mx.edu.j2se.ParadaS.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    /**
     * This method return all tasks that are scheduled in a certain interval
     * @param tasks
     * @param start
     * @param end
     * @return a iterable with Tasks scheduled in a certain interval or null if there is not task
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Invalid range");
        }

        AbstractTaskList auxList =  TaskListFactory.createTaskList(tasks instanceof LinkedTaskList ?
                                    ListTypes.types.LINKED : ListTypes.types.ARRAY);

        tasks.forEach(task -> {

            if(task.nextTimeAfter(start) != null) {
                if (end.isAfter(task.nextTimeAfter(start)) || end.isEqual(task.nextTimeAfter(start))) {
                    auxList.add(task);
                }
            }
        });

        return auxList;
    }


    /**
     * This method return a calendar of tasks for a specified period
     * @param tasks -
     * @param start -
     * @param end -
     * @return
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar (Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){

        //Find all task in the specified period and return a new iterator
        Iterable<Task> listaTasks = incoming(tasks,start,end); //ask if is it possible to use tasks or is better to create a new iterator

        SortedMap<LocalDateTime, Set<Task>> calendarTasks = new TreeMap<LocalDateTime,Set<Task>>();

        listaTasks.forEach(task -> {

            LocalDateTime aux=start;

            if(task.isRepeated()){
                aux = task.nextTimeAfter(aux);
                if(!calendarTasks.containsKey(aux)) {
                    Set<Task> setTask = new HashSet<Task>();
                    setTask.add(task);
                    calendarTasks.put(aux,setTask);
                    }
                else{
                    Set<Task> setTask = calendarTasks.get(aux);
                    setTask.add(task);
                    calendarTasks.put(aux,setTask);
                }

                do{

                    aux =  aux.
                            plusHours(task.getRepeatInterval().getHour()).
                            plusMinutes(task.getRepeatInterval().getMinute()).
                            plusSeconds(task.getRepeatInterval().getSecond());

                    if(!calendarTasks.containsKey(aux)) {

                        Set<Task> setTask = new HashSet<Task>();

                        if(end.isAfter(aux) && aux != null){//revisar, probable null redundante
                            setTask.add(task);
                            calendarTasks.put(aux,setTask);
                        }
                    }
                    else{

                        Set<Task> setTask = calendarTasks.get(aux);

                        if(end.isAfter(aux) && aux != null){
                            setTask.add(task);
                            calendarTasks.put(aux,setTask);
                        }
                    }

                }while((end.isAfter(aux) && aux != null) || end.isEqual(aux));
            }
            else{
                aux = task.nextTimeAfter(aux);
                if(!calendarTasks.containsKey(aux)) {

                    Set<Task> setTask = new HashSet<Task>();

                    if(end.isAfter(aux) && aux != null){
                        setTask.add(task);
                        calendarTasks.put(aux,setTask);
                    }
                }
                else{
                    Set<Task> setTask = calendarTasks.get(aux);
                    if(end.isAfter(aux) && aux != null){
                        setTask.add(task);
                        calendarTasks.put(aux,setTask);
                    }
                }
            }
        });
        return calendarTasks;

    }

}
