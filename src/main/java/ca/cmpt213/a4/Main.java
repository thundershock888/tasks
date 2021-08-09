package ca.cmpt213.a4;

import ca.cmpt213.a4.control.Helper;
import ca.cmpt213.a4.control.Load;
import ca.cmpt213.a4.control.Save;
import ca.cmpt213.a4.model.Task;
import ca.cmpt213.a4.view.HomePage;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        //testTaskCreation();
        Test test = new Test();
        test.setVisible(true);


    }
    static void testTaskCreation(){
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listOfTasks.add(Helper.makeRandTask());
        }
        for (Task t : listOfTasks
        ) {
            t.printSelf();
        }
        Save.saveTask(listOfTasks);
    }
    static ArrayList<Task> testTaskLoad(){
        ArrayList<Task> listOfTasks = Load.loadTasks();
        for (Task t : listOfTasks
        ) {
            t.printSelf();
        }
        return listOfTasks;
    }

}
