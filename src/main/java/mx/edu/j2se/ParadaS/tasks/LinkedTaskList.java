package mx.edu.j2se.ParadaS.tasks;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList {

/**
 * LinkedTasklist
 * @version 06 Abril 2021
 * @author Eduardo Parada
 */

    // Atributtes
     LinkedList<Task> taskList = new LinkedList<Task>();

    // Methods

    // Method to add an specified task to the list
    public void add (Task task) throws IllegalArgumentException{
        if(task == null ){
            throw new IllegalArgumentException("Parametro invalido!");
        }
        taskList.add(task);
    }

    // Method to remove a task (if it exist)
    public boolean remove (Task task) throws IllegalArgumentException{
        if(task == null ){
            throw new IllegalArgumentException("Parametro invalido!");
        }
        boolean valueReturn = false;

        // Get the iterator
        Iterator<Task> iterador = taskList.iterator();

        while(iterador.hasNext()){
            Task actualTask = iterador.next();
            if(actualTask.equals(task)){
                valueReturn = true;
                iterador.remove();
            }
        }
        return valueReturn;
    }

    // Method that returns the number of tasks
    public int size(){
        return taskList.size();
    }

    // Method that return an specified task in the list
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index-1 < 0 && index > size()){
            throw new IndexOutOfBoundsException("No existe la tarea " + index);
        }
        return taskList.get(index-1);
    }

    // Method that shows tasks that are in a specific period of time
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(to<from){
            throw new IllegalArgumentException("rango invalido");
        }

        // Get the iterator
        Iterator<Task> iterador = taskList.iterator();

        LinkedTaskList searchList = new LinkedTaskList();

        while(iterador.hasNext()){
            Task actualTask = iterador.next();
            if(actualTask.active){
                if (actualTask.start > from && actualTask.end < to) {
                    searchList.add(actualTask);
                }
                else if (actualTask.time > from && actualTask.time < to){
                    searchList.add(actualTask);
                }
            }
        }
        return searchList;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        LinkedTaskList that = (LinkedTaskList) obj;
        return taskList.equals(that.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" + "taskList=" + taskList + '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}