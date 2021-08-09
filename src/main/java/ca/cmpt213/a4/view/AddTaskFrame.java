package ca.cmpt213.a4.view;

import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AddTaskFrame{
    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public DateTimePicker getDtp() {
        return dtp;
    }

    String name;
    String notes;
    Boolean completed;
    DateTimePicker dtp;

    public AddTaskFrame(ArrayList tasks){
        completed = false;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        DateTimePicker dateTimePicker = new DateTimePicker();
        JTextField textFieldName = new JTextField("Enter Name", 20);
        JTextField textFieldNotes= new JTextField("Enter Notes", 20);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textFieldName.getText());
                name = textFieldName.getText();
                System.out.println(textFieldNotes.getText());
                notes = textFieldNotes.getText();
                System.out.println(dateTimePicker.getDatePicker().getDateStringOrEmptyString());
                System.out.println(dateTimePicker.getTimePicker().getTimeStringOrEmptyString());
                Task t = new Task(name, notes, dateTimePicker.toString(), completed);
                //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

            }
        });
        JLabel label = new JLabel("Label");





        frame.setSize(420,420);
        frame.setVisible(true);
        frame.setTitle("Tasks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel,BorderLayout.CENTER);
        panel.add(textFieldName);
        panel.add(textFieldNotes);
        panel.add(dateTimePicker);
        panel.add(button);

        //panel.add(label);
        
        frame.pack();

    }


}
