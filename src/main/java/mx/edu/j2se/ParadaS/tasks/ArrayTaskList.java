package mx.edu.j2se.ParadaS.tasks;

/**
 * class ArrayTaskList
 * @version 1.1 02 Mar 2021
 * @author Eduardo Parada
 */

public class ArrayTaskList {

    // Atributtes

    Task[] taskList;
    int last = 0;
    int current = 0;

    // Methods

    // Method to add an specified task to the list
    public void add (Task task) throws IllegalArgumentException {
        if(task == null ){
            throw new IllegalArgumentException("Parametro invalido!");
        }
        else {
            taskList[last] = task;
            last++;
        }
    }

    // Method to remove a task (if it exist)
    public boolean remove (Task task){
        for(current = 0 ; current < last ; current++ ){
            if (task==taskList[current]){
                for(int j = current + 1; j < last ; j++){
                    taskList[current]=taskList[j];
                }
                last = last - 1;
                return true;
            }
        }
        return false;
    }

    // Method that returns the number of tasks
    public int size(){
        return last;
    }

    // Method that return an specified task in the list
    public Task getTask(int index) {
        try {
            return taskList[index];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe la tarea " + index);
        }
        return null;
    }

    // Method that shows tasks that are in a specific period of time
    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList searchArray = new ArrayTaskList();

        for(current = 0; current < last; current++){
            if(taskList[current].active) {
                if (taskList[current].start > from && taskList[current].end < to) {
                    searchArray.add(taskList[current]);
                }
                else if (taskList[current].time > from && taskList[current].time < to){
                    searchArray.add(taskList[current]);
                }
            }
        }
        return searchArray;
    }
}

