package ca.cmpt213.a4.control;

import ca.cmpt213.a4.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {
    public static ArrayList<Task> loadTasks(){
        try{
            File jsonTask = new File("tasks.json");
            jsonTask.createNewFile();
        }catch (IOException e){
            System.out.println("error occured creating json file");
            e.printStackTrace();
        }
        String str = "";
        try {
            File myObj = new File("tasks.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                str += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<Task>>(){}.getType();
        ArrayList<Task> tasks = gson.fromJson(str,userListType);
        return tasks;
    }

}
