package mx.edu.j2se.ParadaS.tasks;

/**
 * This class create a list using arrays to stock tasks
 * @author Eduardo Parada
 * @version -
 *          Practice 2. List based on an array with methods to modify
 *          the list
 *          Practice 3. Exceptions added
 */

    public class ArrayTaskList extends AbstractTaskList{

    //Attributes

    //This is an array of Task-type
    private Task arrayTask[] = new Task[0];

    //Methods

    /**
     * This method adds a task to the list
     * @param task it is the new task, it is added to the end of the list
     */
    public void add(Task task) throws NullPointerException {

        Task[] auxArray = new Task[size() + 1];

        //array copy to the new array
        System.arraycopy(arrayTask, 0, auxArray, 0, size());

        //add the new task
        auxArray[size()] = task;

        //save the updated list in arrayTask
        arrayTask = auxArray;
    }

    /**
     * This method remove all tasks that are equals to the received task
     * @param task is the task that will be compare
     * @return true if it the task was in the list
     */
    public boolean remove(Task task) throws NullPointerException {

        Task[] auxArray = new Task[size()];
        int counter = 0;

        for (Task x : arrayTask) {
            if (!x.getTitle().equals(task.getTitle())) {
                auxArray[counter] = x;
                counter++;
            }
        }

        if (counter == size()) {
            return false;
        } else {
            Task[] auxArray2 = new Task[counter];
            System.arraycopy(auxArray, 0, auxArray2, 0, counter);
            arrayTask = auxArray2;
            return true;
        }

    }

    /**
     * This method return a task (if it exist) in the specified index
     *
     * @param index
     * @return task in the specified index - 1
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        return arrayTask[index];
    }

    public int size() {
        return arrayTask.length;
    }

    /**
     * This method return all tasks that are scheduled in a certain interval
     * @param from
     * @param to
     * @return Tasks schedulen in a certain interval or null if there is not task
     */
    public ArrayTaskList incoming(int from, int to) throws IllegalArgumentException{

        if(from < 0 || to < from){
            throw new IllegalArgumentException("Invalid range");
        }

        ArrayTaskList searchArray = new ArrayTaskList();

        for (Task x : arrayTask) {
            if (x.isActive()) {
                if (x.getStartTime() >= from && x.getEndTime() <= to) {
                    searchArray.add(x);
                } else if (x.getTime() > from && x.getTime() < to) {
                    searchArray.add(x);
                }
            }
        }
        return searchArray;
    }
}
