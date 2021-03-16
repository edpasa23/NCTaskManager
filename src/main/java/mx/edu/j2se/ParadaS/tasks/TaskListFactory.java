package mx.edu.j2se.ParadaS.tasks;

public class TaskListFactory {

    private static final LinkedTaskList list = new LinkedTaskList();
    private static final ArrayTaskList array = new ArrayTaskList();

    static AbstractTaskList createTaskList(ListTypes.types type){

        switch(type) {
            case ARRAY:
                return array;

            case LINKED:
                return list;

            default:
                return null;
        }
    }
}
