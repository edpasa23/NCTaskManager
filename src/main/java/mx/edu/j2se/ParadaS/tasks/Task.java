package mx.edu.j2se.ParadaS.tasks;

/**
 * This class is a template for repetitive and
 * non-repetitive tasks
 * @author Eduardo Parada S.
 * @version -
 *          Practice 1. Constructor, attributes and methods
 *          Practice 3. Exceptions added
 *          Practice 5. equals and hasCode added
 *                      toString added
 *                      clone option added
 */

public class Task implements Cloneable{

    //Attributes
    private String title;
    private int time;
    private int end;
    private int start;
    private int interval;
    private boolean active;

    //Constructors

    //Constructor for a non-repetitive task
    public Task (String title, int time) throws IllegalArgumentException{
        if (title == null || time < 0){
            throw new IllegalArgumentException(title==null?"Invalid title":"time can't be negative");
        }
        this.title = title;
        this.time = time;
    }

    //Constructor for a repetitive task
    public Task (String title, int start, int end, int interval) throws IllegalArgumentException {
        if (title == null || start < 0 || end <= start || interval <= 0){
            throw new IllegalArgumentException(title==null?"Invalid title":"invalid parameter(s) ");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    //getters and setters for title, active

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException{
        if (title == null){
            throw new IllegalArgumentException("Invalid title");
        }
        this.title = title;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * This method return the time of a non-repetitive task, in case of a
     * repetitive one, it returns the start time of the repetition
     * @return the time of the task or the start time
     * time of a repetitive task
     */

    public int getTime(){
        return (!isRepeated() ? time : start);
    }

    /**
     * This method set the time of a non-repetitive task, in case that
     * the task is a repetitive one, it will become non-repetitive
     * @param time the value of time in a non-repetitive task
     */

    public void setTime(int time) throws IllegalArgumentException{
        if(time < 0){
            throw new IllegalArgumentException("Time can't be negative");
        }
        if(isRepeated()){
            interval = 0;
            end = 0;
            start = 0;
        }
        this.time = time;
    }

    /**
     * This method return the start time of a repetitive task, in case of a
     * non-repetitive it returns "time"
     * @return "start" in case of a repetitive task or "time" for a non-repetitive one
     */

    public int getStartTime(){
        return (isRepeated() ? start : time);
    }

    /**
     * This method return the end time of a repetitive task, in case of a
     * non-repetitive it returns "time"
     * @return "end" in case of a repetitive task or "time" for a non-repetitive one
     */

    public int getEndTime(){
        return (isRepeated() ? end : time);
    }

    /**
     * This method return the interval of a repetitive task, in case of a
     * non-repetitive it returns 0
     * @return "interval" in case of a repetitive task or 0 for a non-repetitive one
     */

    public int getRepeatInterval(){
        return (isRepeated() ? interval : 0);
    }

    /**
     * This method set the time for a task, in case that
     * the task is a non-repetitive one, it will become repetitive
     * @param start start time
     * @param end end time
     * @param interval interval of a repetitive task
     */

    public void setTime(int start, int end, int interval) throws IllegalArgumentException{
        if (start < 0 || end <= start || interval <= 0){
            throw new IllegalArgumentException("invalid parameter(s)");
        }
        if(!isRepeated()){
            time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * This method review if the task is repetitive or not
     * @return true for a repetitive task
     */

    public boolean isRepeated(){
        return interval > 0;
    }

    /**
     * This method return the next execution time if it exist
     * @param current The current time
     * @return The time of the next execution or -1 if it not exist.
     */

    public int nextTimeAfter(int current) throws IllegalArgumentException{
        if(current < 0){
            throw new IllegalArgumentException("Current can't be negative");
        }
        //for repetitive task
        if(isActive() && isRepeated()){
            if(start>=current){
                return start;
            }
            //This else if verify the next execution of a repetitive task
            else if(start < current && end > current) {
                int newStart = start;
                do {
                    newStart = newStart + interval;
                }while(newStart < current);

                return (newStart < end) ? newStart : -1;
            }
            else{
                return -1;
            }
        }
        //for non repetitive task
        else if(isActive() && !isRepeated()) {
            return (time > current ? time : -1);
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Task or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Task)) {
            return false;
        }

            // typecast to Task so that we can compare data members
        Task t = (Task) o;

        // Compare the data members and return accordingly

        return t.getTitle().equals(getTitle()) &&
                t.getTime() == getTime() &&
                t.getStartTime() == getStartTime() &&
                t.getEndTime() == getEndTime() &&
                t.isActive() == isActive();
    }

    @Override
    public int hashCode() {
        if (isRepeated()) {
            return title.hashCode() * start * end * interval;
        } else {
            return title.hashCode() * time;
        }
    }

    @Override
    public String toString() {
        if(isRepeated()){
            return "Task: \"" + title + "\" starts at " +
                    start + " and ends at " +
                    end + " in intervals of " +interval +"\n";
        }
        else{
            return "Task: \"" + title + "\" starts at " +
                    time+"\n";
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }
}