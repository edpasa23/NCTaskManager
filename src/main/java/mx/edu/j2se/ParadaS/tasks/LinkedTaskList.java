package mx.edu.j2se.ParadaS.tasks;

/**
 * This class create a list using a linked list to stock tasks
 * @author Eduardo Parada S.
 * @version -
 *          Practice 3. Linked List and exceptions
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
                    if (head.task.getTitle().equals(task.getTitle())) {

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
                    } else if (n.next.task.getTitle().equals(task.getTitle())) {

                        out = true;

                        Node n2 = n;

                        while (n2.next.task.getTitle().equals(task.getTitle())) {

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
     * @param from
     * @param to
     * @return return tasks scheduled in a certain interval or return null if there is not task
     */
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {

        if (from < 0 || to < from) {
            throw new IllegalArgumentException("Invalid range");
        }

        LinkedTaskList searchLinkedList = new LinkedTaskList();

        Node n = head;

        do {
            if (n.task.isActive()) {
                if (n.task.getStartTime() >= from && n.task.getEndTime() <= to) {
                    searchLinkedList.add(n.task);
                } else if (n.task.getTime() >= from && n.task.getTime() <= to) {
                    searchLinkedList.add(n.task);
                }
                n = n.next;
            }
        } while (n != null);
        return searchLinkedList;
    }
}

class Node{

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
}

