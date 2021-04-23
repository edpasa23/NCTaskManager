package mx.edu.j2se.ParadaS.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 *          Practice 7. LocalDateTime added
 */

public class Task implements Cloneable, Serializable {

    //Attributes
    private String title;
    private LocalDateTime time;
    private LocalDateTime end;
    private LocalDateTime start;
    private  LocalDateTime interval;
    private boolean active;

    //Constructors

    //Constructor for a non-repetitive task
    public Task (String title, LocalDateTime time) throws IllegalArgumentException{
        if(time == null){
            throw new IllegalArgumentException("time no valid");
        }
        this.title = title;
        this.time = time;
    }

    //Constructor for a repetitive task
    public Task (String title, LocalDateTime start, LocalDateTime end, LocalDateTime interval) throws IllegalArgumentException{
        if(start == null || end == null || interval == null){
            throw new IllegalArgumentException();
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

    public LocalDateTime getTime(){
        return (!isRepeated() ? time : start);
    }

    /**
     * This method set the time of a non-repetitive task, in case that
     * the task is a repetitive one, it will become non-repetitive
     * @param time the value of time in a non-repetitive task
     */

    public void setTime(LocalDateTime time) throws IllegalArgumentException{
        if(time == null){
            throw new IllegalArgumentException();
        }
        if(isRepeated()){
            interval = null;
            end = null;
            start = null;
        }
        this.time = time;
    }

    /**
     * This method return the start time of a repetitive task, in case of a
     * non-repetitive it returns "time"
     * @return "start" in case of a repetitive task or "time" for a non-repetitive one
     */

    public LocalDateTime getStartTime(){
        return (isRepeated() ? start : time);
    }

    /**
     * This method return the end time of a repetitive task, in case of a
     * non-repetitive it returns "time"
     * @return "end" in case of a repetitive task or "time" for a non-repetitive one
     */

    public LocalDateTime getEndTime(){
        return (isRepeated() ? end : time);
    }

    /**
     * This method return the interval of a repetitive task, in case of a
     * non-repetitive it returns 0
     * @return "interval" in case of a repetitive task or 0 for a non-repetitive one
     */

    public LocalDateTime getRepeatInterval(){
        return (isRepeated() ? interval : null);
    }

    /**
     * This method set the time for a task, in case that
     * the task is a non-repetitive one, it will become repetitive
     * @param start start time
     * @param end end time
     * @param interval interval of a repetitive task
     */

    public void setTime(LocalDateTime start, LocalDateTime end, LocalDateTime interval) throws IllegalArgumentException{
        if ( start==null || end.isBefore(start) || interval==null) {
            throw new IllegalArgumentException("invalid parameter(s)");
        }
        if(!isRepeated()){
            time = null;
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
        return interval != null;
    }

    /**
     * This method return the next execution time if it exist
     * @param current The current time
     * @return The time of the next execution or -1 if it not exist.
     */

    public LocalDateTime nextTimeAfter(LocalDateTime current){

        //for repetitive task
        if(isActive() && isRepeated()){
            if(start.isAfter(current) || start.isEqual(current)){
                return start;
            }
            else if(end.isEqual(current)){
                return end;
            }
            //This "else if" verify the next execution of a repetitive task
            else if((start.isBefore(current) || start.isEqual(current))   && (end.isAfter(current))) {
                LocalDateTime newStart = start;


                do {
                    newStart =  newStart.
                                plusHours(interval.getHour()).
                                plusMinutes(interval.getMinute());
                                //plusSeconds(interval.getSecond());
                }while(newStart.isBefore(current));

                return ((newStart.isEqual(getEndTime()) || newStart.isBefore(getEndTime())) ? newStart : null);
            }
            else{
                return null;
            }
        }
        //for non repetitive task
        else if(isActive() && !isRepeated()) {
            return ((time.isAfter(current) || time.isEqual(current) ) ? time : null);
        }
        else{
            return null;
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
                t.getTime().equals(getTime()) &&
                t.getStartTime().equals(getStartTime()) &&
                t.getEndTime().equals(getEndTime()) &&
                t.isActive() == isActive();
    }

    @Override
    public int hashCode() {
        if (isRepeated()) {
            return title.hashCode() * start.hashCode() * end.hashCode() * interval.hashCode();
        } else {
            return title.hashCode() * time.hashCode();
        }
    }

    @Override
    public String toString() {
        if(isRepeated()){
            return "Task: \"" + title + "\" starts at " +
                    start.toString() + " and ends at " +
                    end.toString() + " in intervals of " +interval.toString() +"\n";
        }
        else{
            return "Task: \"" + title + "\" starts at " +
                    time.toString()+"\n";
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }
}