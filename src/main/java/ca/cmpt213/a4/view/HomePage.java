package ca.cmpt213.a4.view;

import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage {
    ArrayList<Task> allTasks = new ArrayList<>();
    public HomePage(ArrayList<Task> tasks){
        allTasks = tasks;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Button addTaskButton = new Button("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskFrame tempFrame = new AddTaskFrame(allTasks);
            }
        });
        for (Task t: allTasks
             ) {
            JTextArea textArea = new JTextArea(t.toText());
            frame.add(textArea);
            frame.pack();

        }

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));
        frame.setVisible(true);
        frame.setTitle("Tasks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(addTaskButton);
        frame.pack();
    }
}
