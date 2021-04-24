package mx.edu.j2se.ParadaS.tasks;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * This class create a list using arrays to stock tasks
 * @author Eduardo Parada
 * @version -
 *          Practice 2. List based on an array with methods to modify
 *          the list
 *          Practice 3. Exceptions added
 *          Practice 5. Iterators added
 *                      equals and hasCode added
 *                      toString added
 *                      clone option added
 *          Practice 6. Stream added
 *                      (toString modified because used arrays library)
 */

    public class ArrayTaskList extends AbstractTaskList {

    //Array of Task-type
    private Task arrayTask[] = new Task[0];

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("This method not support null objects");
        }

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
     * @param task this task will be compare in the list
     * @return true if the task was in the list (and removed)
     */
    @Override
    public boolean remove(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("This method not support null objects");
        }

        Task[] auxArray = new Task[size()];
        int counter = 0;

        for (Task x : arrayTask) {
            if (!x.equals(task)) {
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

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("This method not support negative index");
        } else if (size() == 0) {
            throw new IndexOutOfBoundsException("The list is empty");
        } else if (index > size()) {
            throw new IndexOutOfBoundsException("This index doesnt exist");
        }

        return arrayTask[index];
    }

    @Override
    public int size() {
        return arrayTask.length;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskIterator();
    }

    protected class ArrayTaskIterator implements Iterator<Task>{
        private int index = 0;

        public boolean hasNext(){
            return (index < size() );
        }

        public Task next(){
            index++;
            return getTask(index-1);
        }
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of ArrayTaskList or not.
          "null instanceof [type]" also returns false */
        if (!(o instanceof ArrayTaskList)) {
            return false;
        }

        // typecast to ArrayTaskList so that we can compare data members
        ArrayTaskList a = (ArrayTaskList) o;

        // Compare the data members and return accordingly

        if (a.size() == size()) {

            Iterator<Task> aIterator = a.iterator();
            Iterator<Task> oIterator = ((ArrayTaskList) o).iterator();

            while (aIterator.hasNext() && oIterator.hasNext()) {

                Task tmp1 = aIterator.next();
                Task tmp2 = oIterator.next();

                if(!tmp1.equals(tmp2)) {
                    return false;
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return arrayTask.hashCode()*size();
    }

    @Override
    public String toString() throws IllegalStateException{ //Revisar

        if(size()==0){
            throw new IllegalArgumentException("The List is empty");
        }

        String text = arrayTask[0].toString() + "\n";

        for(int i = 1 ; i < size() ; i++){

            text = text + arrayTask[i].toString() +  "\n";

        }

        return "Task List:\n" + text;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (ArrayTaskList)super.clone();
    }

    @Override
    public Stream<Task> getStream()throws IllegalStateException{

        if(size()==0){
            throw new IllegalArgumentException("The List is empty");
        }

        Stream.Builder<Task> builder = Stream.builder();

        for(Task x : arrayTask){
            builder.add(x);
        }

        return builder.build();
    }

}


