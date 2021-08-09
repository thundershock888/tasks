package ca.cmpt213.a4.control;

import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DateTimePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Helper {

    private static HttpURLConnection connection;
    static BufferedReader reader;
    static String line;
    static StringBuffer responseContent = new StringBuffer();
    public static String getRandTask(){
        responseContent = new StringBuffer();
        try {
            URL url = new URL("https://www.boredapi.com/api/activity/");
            //System.out.println(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            System.out.println(status);
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent.toString();

    }
    public static Task makeRandTask(){
        String taskString = getRandTask();
        String[] taskStrings = taskString.split(",");
        String name = taskStrings[0].split(":")[1].trim();
        String notes = taskStrings[1]+taskStrings[2]+taskStrings[3];
        LocalDateTime ldt = LocalDateTime.now();
        DateTimePicker dtp = new DateTimePicker();
        dtp.setDateTimePermissive(ldt);
        Task t = new Task(name, notes, dtp.toString(), false);
        return t;
    }
   public static boolean toBool(String s){
        if(!s.equals("true")){
            return false;
        }
        return true;
    }
    public static ArrayList<Task> getOverdue(ArrayList<Task> oldTasks){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localTime = LocalDateTime.now();
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task t: oldTasks
             ) {
            t.printSelf();
            String date = removeT(t.getDateTime());
            LocalDateTime dueDate = LocalDateTime.parse(date, formatter);

            if(dueDate.isBefore(localTime)){
                if(!t.isCompleted()) {
                    newTasks.add(t);
                }
            }
            Collections.sort(newTasks);

        }
        return newTasks;
    }
    public static ArrayList<Task> getUpcoming(ArrayList<Task> oldTasks){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localTime = LocalDateTime.now();

        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task t: oldTasks
        ) {
            t.printSelf();
            String date = removeT(t.getDateTime());
            LocalDateTime dueDate = LocalDateTime.parse(date, formatter);

            if(dueDate.isAfter(localTime)){
                if(!t.isCompleted()) {
                    newTasks.add(t);
                }
            }
            Collections.sort(newTasks);

        }
        return newTasks;

    }
    public static String removeT(String s){
        String[] temp = s.split("T");
        s = temp[0]+ " " + temp[1];
        return s;
    }
}
