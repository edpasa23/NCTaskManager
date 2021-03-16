package mx.edu.j2se.ParadaS.tasks;

abstract class AbstractTaskList {

    /**
     * Practice 4. class AbstractTaskList
     * @version 16 Mar 2021
     * @author Eduardo Parada
     */

    abstract void add(Task task);
    abstract boolean remove (Task task);
    abstract int size();
    abstract Task getTask(int index);
    abstract AbstractTaskList incoming (int from,int to);
}
