package mx.edu.j2se.ParadaS.tasks;

/**
 * @author Eduardo Parada S.
 * @version -
 *          Practice 4. TaskListFactory
 *          Practice 5. Iterators added
 */

public class TaskListFactory {

    static AbstractTaskList createTaskList(ListTypes.types type){

        switch(type) {
            case ARRAY:
                ArrayTaskList array = new ArrayTaskList();
                return array;

            case LINKED:
                LinkedTaskList list = new LinkedTaskList();
                return list;

            default:
                return null;
        }
    }
}
