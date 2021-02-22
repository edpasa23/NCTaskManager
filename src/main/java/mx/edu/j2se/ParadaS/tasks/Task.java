package mx.edu.j2se.ParadaS.tasks;

/**
 * class Task
 * @version 1.0 22 Feb 2021
 * @author Eduardo Parada
 */


public class Task {

    // Attributes
    String title;
    int time;
    int start;
    int end;
    int interval;
    boolean active;
    boolean repeated;

    // Constructor

    // ...for non-repetitive task
    public Task (String title, int time){
        this.title = title;
        this.time = time;
        repeated = false;
    }

    // ...for repetitive task
    public Task (String title, int start, int end, int interval){
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = true;
    }

    // Methods

    // Method for reading task name
    public String getTitle(){
        return title;
    }

    // Method for setting task name
    public void setTitle(String title){
        this.title = title;
    }

    // Method for reading the task status
    public boolean isActive(){
        active = start < end;
        return active;
    }

    // Method for setting the task status
    public void setActive(boolean active){
        this.active = active;
    }

    // Method for reading execution time for a non-repetitive task
    public int getTime(){
        return (isRepeated() ? start : time);
    }

    // Method for getting execution time for a non-repetitive task
    public void setTime(int time){
        if(isRepeated()) {
            repeated = false;
        }
        this.time = time;
    }

    // Method for reading the start time of a repetitive task
    public int getStartTime(){
        return (isRepeated() ? start : time);
    }

    // Method for reading the end time of a repetitive task
    public int getEndTime(){
        return (isRepeated() ? end : time);
    }

    // Method for reading the interval time of a repetitive task
    public int getRepeatInterval(){
        return (isRepeated() ? interval : 0);
    }

    // Method for setting the start, the end and the interval of a repetitive task
    public void setTime(int start, int end, int interval){
        if(!isRepeated()) {
            repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    // Method to check the task for repeatability
    public boolean isRepeated(){
        repeated = interval > 0;
        return repeated;
    }

    // Method to know the next execution time (if it exist)
    public int nextTimeAfter (int current){
        if(isActive()){
            if(isRepeated()){
                if (current >= start) {
                    start = start + interval;
                }
                return start;
            }
            else {
                return time;
            }
        }
        else {
            return -1;
        }
    }
}
