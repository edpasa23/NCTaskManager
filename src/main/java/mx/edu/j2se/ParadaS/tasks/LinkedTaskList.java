package mx.edu.j2se.ParadaS.tasks;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * This class create a list using a linked list to stock tasks
 * @author Eduardo Parada S.
 * @version -
 *          Practice 3. Linked List and exceptions
 *          Practice 5. Iterators added
 *                      equals and hasCode added
 *                      toString added
 *                      clone option added
 *          Practice 6. Stream added
 */

public class LinkedTaskList extends AbstractTaskList{

    //Attributes

    Node head;

    //Methods

    /**
     * This method adds a task to the list
     *
     * @param task it is the new task, it is added to the end of the list
     */
    @Override
    public void add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("This method not support null objects");
        }

        //New node
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
        } else {
            Node n = head;
            while (n.hasNext()) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    /**
     * This method remove all tasks that are equals to the received task
     *
     * @param task is the task that will be compare
     * @return true if it the task was in the list
     */
    @Override
    public boolean remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("This method not support null objects");
        }
        boolean out = false;

        if (head != null) {

            Node n = head;

            busqueda:
            { //recorrido por los Nodos
                do {
                    if (head.task.equals(task)) {

                        out = true;

                        //if there is only one element in the linked list
                        // the program have to initialize again head

                        if (head.next == null) {
                            head = null;
                            return true;
                        } else {
                            head = head.next;
                            n = head;
                        }
                    } else if (n.next.task.equals(task)) {

                        out = true;

                        Node n2 = n;

                        while (n2.next.task.equals(task)) {

                            n2 = n2.next;

                            if (n2 == null) {
                                n.next = null;
                                break busqueda;
                            }
                        }

                        n.next = n2.next;
                        n = n2.next;
                    } else {
                        n = n.next;
                    }
                } while (n.hasNext());
            }
        }
        return out;
    }

    @Override
    public int size() {
        int count = 0;
        Node n = head;
        if (n != null) {
            do {
                count++;
                n = n.next;
            } while (n != null);
        }
        return count;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("This method not support negative index");
        } else if (head == null) {
            throw new IndexOutOfBoundsException("The list is empty");
        } else if (index > size()) {
            throw new IndexOutOfBoundsException("This index doesnt exist");
        }

        int actual = 0;
        Node n = head;

        while (actual != index) {
            actual++;
            n = n.next;
        }
        return n.task;
    }

    /**
     * This method return all tasks that are scheduled in a certain interval
     *
     * @param
     * @param
     * @return return tasks scheduled in a certain interval or return null if there is not task
     */
 /*   public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {

        if (from < 0 || to < from) {
            throw new IllegalArgumentException("Invalid range");
        }

        LinkedTaskList searchLinkedList = new LinkedTaskList();

        Node n = head;

        do {
            if (n.task.isActive()) {
                if (n.task.getStartTime().isAfter(from) && n.task.getEndTime() <= to) {
                    searchLinkedList.add(n.task);
                } else if (n.task.getTime() >= from && n.task.getTime() <= to) {
                    searchLinkedList.add(n.task);
                }
                n = n.next;
            }
        } while (n != null);
        return searchLinkedList;
    }*/

    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskIterator();
    }

    private class LinkedTaskIterator implements Iterator<Task>{
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

        /* Check if o is an instance of LinkedTaskList or not.
          "null instanceof [type]" also returns false */
        if (!(o instanceof LinkedTaskList)) {
            return false;
        }

        // typecast to LinkedTaskList so that we can compare data members
        LinkedTaskList l = (LinkedTaskList) o;

        // Compare the data members and return accordingly

        if (l.size() == size()) {

            Iterator<Task> lIterator = l.iterator();
            Iterator<Task> oIterator = ((LinkedTaskList) o).iterator();

            while (lIterator.hasNext() && oIterator.hasNext()) {

                Task tmp1 = lIterator.next();
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
        return head.hashCode()*size();
    }

    @Override
    public String toString() {

        String texto = "Task List:\n";

        if(size()==0){
        texto = "Empty List";
        }
        else{
            Node n = head;

            do{
                texto =  texto +  n.task.toString();
                n = n.next;
            }while(n!=null);
        }

        return texto;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        LinkedTaskList clon = (LinkedTaskList) super.clone();

        clon.head = (Node) head.clone();

        return clon;

    }

    @Override
    public Stream<Task> getStream() throws IllegalStateException{

        if(size()==0){
            throw new IllegalArgumentException("The List is empty");
        }

        Stream.Builder<Task> builder = Stream.builder();

        Node aux = head;

        while(aux != null){
            builder.add(aux.task);
            aux = aux.next;
        }

        return builder.build();
    }

}

class Node implements Cloneable{

    //Attributes

    Task task;
    Node next;

    //Node constructor

    public Node(){
        //Empty
    }

    public Node(Task task){
        this.task = task;
        next = null;
    }

    public boolean hasNext(){
        return next != null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return task.hashCode()*7;
    }

}

