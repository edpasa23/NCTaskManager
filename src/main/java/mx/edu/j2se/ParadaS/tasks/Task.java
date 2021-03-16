package mx.edu.j2se.ParadaS.tasks;

/**
 * Practice 1. Class Task
 * @version 15 Mar 2021
 * @author Eduardo Parada
 */


class Task {

    // Attributes

    private String title;
    int time;
    int start;
    int end;
    int interval;
    boolean active;

    // Constructors

    // ...for non-repetitive task
    public Task (String title, int time) throws IllegalArgumentException{
        if(time <= 0 ) {
            throw new IllegalArgumentException("Time cant be negative");
        }
        this.title = title;
        this.time = time;
        active = true; //PREGUNTAR
    }

    // ...for repetitive task
    public Task (String title, int start, int end, int interval) throws IllegalArgumentException{
        if(start < 0 || end <= start || interval < 0){
            throw new IllegalArgumentException("Invalid Parameter(s)");
        }
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            active = true;
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
    public void setTime (int time) throws IllegalArgumentException{
        if(time <= 0 ) {
            throw new IllegalArgumentException("time can´t be negative");
        }
        if(isRepeated()){
            start = 0;
            end = 0;
            interval = 0;
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
    public void setTime(int start, int end, int interval) throws IllegalArgumentException{
        if(start <= 0 || end <= start || interval < 0){
            throw new IllegalArgumentException("Parametro(s) invalido(s)");
        }
        if(!isRepeated()) {
            time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    // Method to check the task for repeatability
    public boolean isRepeated(){
        return interval>0;
    }

    // Method to know the next execution time (if it exist)
    public int nextTimeAfter (int current){
        if(isActive()){
            if(isRepeated()){
                if(current <= start){
                    return start;
                }
                else if(current >= start && current <= end ){
                    int i=current;
                    do{
                        i=i+interval;
                        if(i>end){
                            return -1;
                        }
                    }while(i>=start && current <= end);
                    return i;
                }
                else{
                    return -1;
                }
            }
            else if(current <= time){
                return time;
            }
            else{
                return -1;
            }
        }
        else {
            return -1;
        }
    }
}
