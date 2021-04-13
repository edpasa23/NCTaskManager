package mx.edu.j2se.ParadaS.tasks;

/**
 * This is an abstract class for LinkedTaskList and ArrayTaskList
 * @author Eduardo Parada S.
 * @version -
 *          Practice 4. Inheritance (abstract)
 */
abstract class AbstractTaskList {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract AbstractTaskList incoming(int from, int to);

}