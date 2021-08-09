package ca.cmpt213.a4;

import ca.cmpt213.a4.control.Load;
import ca.cmpt213.a4.control.Save;
import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Test extends JFrame{
    private JPanel Home;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JList taskList;
    private JButton SaveAsNew;
    private JLabel name;
    private JLabel notesField;
    private JButton ViewAll;
    private JButton ViewOverDue;
    private JButton ViewUpcoming;
    private JLabel dateField;
    private JLabel completedField;
    private JButton SaveExisting;
    private JTextField textName;
    private JTextField textNotes;
    private JLabel textDate;
    private JButton setTrue;
    private JButton setFalse;
    private JLabel textCompleted;
    private JButton makeRandom;
    private DateTimePicker setDateField;
    private ArrayList<Task> tasks;
    private DefaultListModel listTaskModel;
    Test(){
        super("Tasks Project");
        this.setContentPane(this.Home);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        tasks = Load.loadTasks();
        listTaskModel = new DefaultListModel();
        taskList.setModel(listTaskModel);
        for (Task t:tasks
             ) {
            System.out.println("loaded");
            t.printSelf();
        }

        SaveExisting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = taskList.getSelectedIndex();
                if(i >= 0){
                    Task t = tasks.get(i);
                    t.setName(textName.getText());
                    t.setNotes(textNotes.getText());
                    t.setDateTime(setDateField.getDateTimeStrict().toString());
                }

            }
        });
        SaveAsNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = taskList.getSelectedIndex();
                if(i >= 0){
                    Task t = tasks.get(i);
                    textName.setText(t.getName());
                    textNotes.setText(t.getNotes());
                    textDate.setText(t.getDateTime());
                    textCompleted.setText(t.getCompletedAsString());

                }
            }
        });
        setFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void refreshTaskList(){
        listTaskModel.removeAllElements();
        for (Task t: tasks
             ) {
            listTaskModel.addElement(t.getName());
        }
        //Save.saveTask(tasks);
    }
    public void addTask(Task t){
        tasks.add(t);
        refreshTaskList();
    }
}
