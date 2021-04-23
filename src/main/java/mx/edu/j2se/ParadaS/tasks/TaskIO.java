package mx.edu.j2se.ParadaS.tasks;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {

    DataOutputStream stream = new DataOutputStream(out);

    stream.write(tasks.size());

    tasks.forEach(task -> {

        try {

            stream.write(task.getTitle().length());
            stream.writeChars(task.getTitle());
            stream.writeBoolean(task.isActive());
            stream.writeBoolean(task.isRepeated());

            if(task.isRepeated()){
                stream.writeInt(task.getStartTime().getYear());
                stream.writeInt(task.getStartTime().getMonthValue());
                stream.writeInt(task.getStartTime().getDayOfMonth());
                stream.writeInt(task.getStartTime().getHour());
                stream.writeInt(task.getStartTime().getMinute());

                stream.writeInt(task.getEndTime().getYear());
                stream.writeInt(task.getEndTime().getMonthValue());
                stream.writeInt(task.getEndTime().getDayOfMonth());
                stream.writeInt(task.getEndTime().getHour());
                stream.writeInt(task.getEndTime().getMinute());

                stream.writeInt(task.getRepeatInterval().getYear());
                stream.writeInt(task.getRepeatInterval().getMonthValue());
                stream.writeInt(task.getRepeatInterval().getDayOfMonth());
                stream.writeInt(task.getRepeatInterval().getHour());
                stream.writeInt(task.getRepeatInterval().getMinute());

            }

            else{

                stream.writeInt(task.getTime().getYear());
                stream.writeInt(task.getTime().getMonthValue());
                stream.writeInt(task.getTime().getDayOfMonth());
                stream.writeInt(task.getTime().getHour());
                stream.writeInt(task.getTime().getMinute());

            }

        } catch (IOException e) {
            //
        }
    });

    stream.close();

    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {

        DataInputStream stream = new DataInputStream(in);

        int size = stream.read();

        for(int i = 0; i < size; i++){

            StringBuilder builder = new StringBuilder();
            String title;
            Task taskAux;
            int year,month,day,hour,minute;

            int lengthTitle = stream.read();

            for(int j = 0; j < lengthTitle; j++){
                builder.append(stream.readChar());
            }

            title = builder.toString();

            boolean active = stream.readBoolean();
            boolean isRepeated = stream.readBoolean();

            if(isRepeated){
                year = stream.readInt();month = stream.readInt();day = stream.readInt();
                hour = stream.readInt();minute = stream.readInt();
                LocalDateTime interval = LocalDateTime.of(year,month,day,hour,minute);
                year = stream.readInt();month = stream.readInt();day = stream.readInt();
                hour = stream.readInt();minute = stream.readInt();
                LocalDateTime start = LocalDateTime.of(year,month,day,hour,minute);
                year = stream.readInt();month = stream.readInt();day = stream.readInt();
                hour = stream.readInt();minute = stream.readInt();
                LocalDateTime end = LocalDateTime.of(year,month,day,hour,minute);
                taskAux = new Task(title,start,end,interval);
            }
            else{
                year = stream.readInt();month = stream.readInt();day = stream.readInt();
                hour = stream.readInt();minute = stream.readInt();

                LocalDateTime time = LocalDateTime.of(year,month,day,hour,minute);
                taskAux = new Task(title,time);
            }

            taskAux.setActive(active);
            tasks.add(taskAux);
        }
        stream.close();
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {

        FileOutputStream fileOut = new FileOutputStream(file);

        write(tasks,fileOut);

        fileOut.close();
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException, ClassNotFoundException {

        FileInputStream fileIn = new FileInputStream(file);

        read(tasks,fileIn);

        fileIn.close();
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException{

        JsonWriter writer = new JsonWriter(out);

        writer.beginArray();

        tasks.forEach(task -> {
            try {

                writer.beginObject();
                writer.name("Task Name").value(task.getTitle());
                writer.name("Active Status").value(task.isActive());
                writer.name("Repetitive").value(task.isRepeated());
                if(task.isRepeated()){
                    writer.name("Start time").value(task.getStartTime().toString());
                    writer.name("End time").value(task.getEndTime().toString());
                    writer.name("Interval").value(task.getRepeatInterval().toString());
                }
                else{

                    writer.name("Time year").value(task.getTime().toString());
                }

                writer.endObject();
            }catch (IOException e) {
            //
            }
        });

        writer.endArray();
        writer.flush();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {

        JsonReader reader = new JsonReader(in);
        String title = null;
        Task taskAux;
        String startString, endString, intervalString, timeString;
        LocalDateTime start = null, end = null, interval = null, time = null;
        boolean active = false, repetitive = false;

        if (reader.hasNext()) {

            reader.beginArray();

            while (reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {

                    String name = reader.nextName();

                    switch (name) {
                        case "Task Name":
                            title = reader.nextString();
                            break;
                        case "Active Status":
                            active = reader.nextBoolean();
                            break;
                        case "Repetitive":
                            repetitive = reader.nextBoolean();
                            break;
                        case "Start time": {
                            startString = reader.nextString();
                            DateTimeFormatter stringToTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                            start = LocalDateTime.parse(startString, stringToTime);
                            break;
                        }
                        case "End time": {
                            endString = reader.nextString();
                            DateTimeFormatter stringToTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                            end = LocalDateTime.parse(endString, stringToTime);
                            break;
                        }
                        case "Interval": {
                            intervalString = reader.nextString();
                            DateTimeFormatter stringToTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                            interval = LocalDateTime.parse(intervalString, stringToTime);
                            break;
                        }
                        case "Time year": {
                            timeString = reader.nextString();
                            DateTimeFormatter stringToTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                            time = LocalDateTime.parse(timeString, stringToTime);
                            break;
                        }
                    }
                }

                if (repetitive) {
                    taskAux = new Task(title, start, end, interval);
                    taskAux.setActive(active);
                    tasks.add(taskAux);
                } else {
                    taskAux = new Task(title, time);
                    taskAux.setActive(active);
                    tasks.add(taskAux);
                }
                reader.endObject();
            }

            reader.endArray();

        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException{

        FileWriter writer = new FileWriter(file);
        write(tasks,writer);

    }

    public static void readText(AbstractTaskList tasks, File file) throws  IOException{
        FileReader reader = new FileReader(file);
        read(tasks,reader);
    }

}
