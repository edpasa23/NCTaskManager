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

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract Stream<Task> getStream();

}