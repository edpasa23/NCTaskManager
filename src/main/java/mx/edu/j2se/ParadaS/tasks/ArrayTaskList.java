package mx.edu.j2se.ParadaS.tasks;

import java.util.Arrays;

/**
 * ArrayTaskList
 * @version 06 Abril 2021
 * @author Eduardo Parada
 */

    class ArrayTaskList extends AbstractTaskList{

    // Atributtes
    private Task[] taskList = new Task[0];

    // Methods


    // Method to add an specified task to the list
    public void add (Task task) throws IllegalArgumentException{
        if(task == null ){
            throw new IllegalArgumentException("Parametro invalido!");
        }
        Task[] tempArray = new Task[size()+1];
        tempArray[tempArray.length-1]=task;
        System.arraycopy(taskList,0,tempArray,0,size());
        taskList = tempArray;
    }

    // Method to remove a task (if it exist)
    public boolean remove (Task task) throws IllegalArgumentException{
        if(task == null ){
            throw new IllegalArgumentException("Parametro invalido!");
        }

        int counter=0;    //coincidencias
        Task[] tempArray = new Task[size()];

        for(int i = 0 ; i < size(); i++ ) {
            if (taskList[i].getTitle() != task.getTitle()) {
                tempArray[counter] = taskList[i];
                counter++;
            }
        }

        if(counter==size()) {
            return false;
        }
        else{
            Task[] tempArray2 = new Task[counter];
            System.arraycopy(tempArray,0,tempArray2,0,counter);
            taskList = tempArray2;
            return true;
        }
    }

    // Method that returns the number of tasks
    public int size(){
        return taskList.length;
    }

    // Method that return an specified task in the list
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index-1 < 0 && index > size()){
            throw new IndexOutOfBoundsException("No existe la tarea " + index);
        }
            return taskList[index-1];
    }

    // Method that shows tasks that are in a specific period of time
    public ArrayTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(to<from){
            throw new IllegalArgumentException("rango invalido");
        }
        ArrayTaskList searchArray = new ArrayTaskList();

        for(int i = 0; i < size(); i++){
            if(taskList[i].active) {
                if (taskList[i].start > from && taskList[i].end < to) {
                    searchArray.add(taskList[i]);
                }
                else if (taskList[i].time > from && taskList[i].time < to){
                    searchArray.add(taskList[i]);
                }
            }
        }
        return searchArray;
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
        ArrayTaskList that = (ArrayTaskList) obj;
        return Arrays.equals(taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskList);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" + "taskList=" + Arrays.toString(taskList) + '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


