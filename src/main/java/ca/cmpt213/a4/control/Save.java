package ca.cmpt213.a4.control;

import ca.cmpt213.a4.model.Task;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {
    public static void saveTask(ArrayList<Task> tasks){
        Gson gson = new Gson();
        String resp = gson.toJson(tasks);
        try{
            File jsonTask = new File("tasks.json");
            jsonTask.createNewFile();
        }catch (IOException e){
            System.out.println("error occured creating json file");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("tasks.json");
            myWriter.write(resp);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
