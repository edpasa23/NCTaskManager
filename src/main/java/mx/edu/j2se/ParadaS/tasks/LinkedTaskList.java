package mx.edu.j2se.ParadaS.tasks;
import java.util.LinkedList;

public class LinkedTaskList extends AbstractTaskList{

/**
 * Practice 3. class LinkedTasklist
 * @version 16 Mar 2021
 * @author Eduardo Parada
 */

    // Atributtes
    private LinkedList<Task> taskList = new LinkedList<Task>();

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
        for(int i=0; i< taskList.size() ; i++){
            if(taskList.get(i).getTitle()==task.getTitle()){
                taskList.remove(i);
                valueReturn = true;
               i--; //al remover un valor se recorre la lista, por ello hay que verificar el dato recorrido
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
        LinkedTaskList searchList = new LinkedTaskList();

        for(int i = 0; i < size(); i++){
            if(taskList.get(i).active) {
                if (taskList.get(i).start > from && taskList.get(i).end < to) {
                    searchList.add(taskList.get(i));
                }
                else if (taskList.get(i).time > from && taskList.get(i).time < to){
                    searchList.add(taskList.get(i));
                }
            }
        }
        return searchList;
    }
}