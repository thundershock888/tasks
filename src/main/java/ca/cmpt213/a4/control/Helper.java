package ca.cmpt213.a4.control;

import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DateTimePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

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
        String notes = taskStrings[1];
        LocalDateTime ldt = LocalDateTime.now();
        DateTimePicker dtp = new DateTimePicker();
        dtp.setDateTimePermissive(ldt);
        Task t = new Task(name, notes, dtp.toString(), false);
        return t;
    }
}
