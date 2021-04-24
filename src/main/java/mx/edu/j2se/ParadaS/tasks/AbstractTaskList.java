package mx.edu.j2se.ParadaS.tasks;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * This is an abstract class for LinkedTaskList and ArrayTaskList
 * @author Eduardo Parada S.
 * @version -
 *          Practice 4. Inheritance (abstract)
 *          Practice 5. Iterable and Cloneable added (implementation)
 */
abstract class AbstractTaskList implements Iterable<Task>,Cloneable, Serializable {

    public abstract Iterator iterator();

    /**
     * This method adds a task to the list
     * @param task this task is added to the end of the list
     */
    abstract void add(Task task);

    /**
     * This method remove all tasks that are equals to the received task
     * @param task this task will be compare in the list
     * @return true if the task was in the list (and removed)
     */
    abstract boolean remove(Task task);

    /** Return the size of the list
     * @return Return an integer that represent the size of the list
     */
    abstract int size();

    /**
     * This method return a task (if it exist) in the specified index
     * @param index It is the index to search the list
     * @return task in the specified index
     */
    abstract Task getTask(int index);

    abstract Stream<Task> getStream();

}