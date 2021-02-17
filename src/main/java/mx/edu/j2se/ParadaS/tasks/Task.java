package mx.edu.j2se.ParadaS.tasks;

/*PRIMERA TAREA*/

//Clase Task
public class Task {

    //*****Atributos*****

    String title;
    int time;
    int start;
    int end;
    int interval;
    boolean active;
    boolean repeated;

    //*****Constructores*****

    //Contructor para tarea no repetitiva
    public Task (String title, int time){

    }

    //Contructor para tarea repetitiva
    public Task (String title, int start, int end, int interval){

    }

    //Metodos

    //Metodo de lectura para nombre
    public String getTitle(){
        return title;
    }

    //Metodo para modificar nombre
    public void setTitle(String title){
        this.title = title;
    }

    //Metodo para lectura de si sigue activa la tarea
    public boolean isActive(){
        return active;
    }

    //Metodo para modifcar el estado de la tarea
    public void setActive(boolean active){
        this.active = active;
    }

    //Metodo de lectura de tiempo
    public int getTime(){
        if (isRepeated())
            return start;
        else
            return time;
    }

    //Metodo para modificar tiempo
    public void setTime(int time){
        if(isRepeated())
            repeated = false;
        this.time = time;
    }

    //Metodo para lectura de inicio de ejecución de una tarea repetitiva
    public int getStartTime(){
        if (isRepeated())
            return start;
        else
            return time;
    }

    //Metodo para lectura de finalizacion de ejecucion de una tarea repetitiva
    public int getEndTime(){
        if(isRepeated())
            return end;
        else
            return time;
    }

    //Metodo de lectura para el intervalo de una tarea repetitiva
    public int getRepeatInterval(){
        if(isRepeated())
            return interval;
        else
            return 0;
    }

    //Metodo para modificar parametros de incio, final e intervalo
    public void setTime(int start, int end, int interval){
        if(!isRepeated())
            repeated = true;

        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    //Metodo para checar si es repetitivo
    public boolean isRepeated(){
        repeated = interval > 0;
        return repeated;
    }

    //Metodo para conocer la prócuma ejecicion (si es que la hay)
    public int nextTimeAfter (int current){
        if(isActive()){
            if(isRepeated()){
                start=start + interval;
                return start;
            }
            else {
                active = false;
                return time-current;
            }
        }
        else
            return -1;
    }
}
