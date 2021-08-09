package ca.cmpt213.a4;

import ca.cmpt213.a4.control.Helper;
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
import java.util.Collections;

public class Test extends JFrame{
    private JPanel basePanel;
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
    private DateTimePicker setDateField;
    private JButton removeTask;
    private JButton addRandoTask;
    private JLabel notice;
    private ArrayList<Task> tasks;
    private ArrayList<Task> tempTasks;
    private DefaultListModel listTaskModel;
    private String modeSelected;

    Test() {

        super("Task Project");
        this.setContentPane(this.basePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        tasks = Load.loadTasks();
        tempTasks = tasks;
        System.out.println("tasks size: "+ tasks.size());
        listTaskModel = new DefaultListModel();
        modeSelected = "all";
        refreshTasks();
        //listTaskModel.addElement("wtf");
        taskList.setModel(listTaskModel);


        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                notice.setText("");
                int i = taskList.getSelectedIndex();
                if(i >= 0){
                    Task t = tempTasks.get(i);
                    textName.setText(t.getName());
                    textNotes.setText(t.getNotes());
                    textDate.setText(t.getDateTime());
                    textCompleted.setText(t.getCompletedAsString());

                }

            }
        });
        SaveAsNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               int i = 1;
                String name = textName.getText();
                if(name.length() < 1){
                    notice.setText("Notice: Please Enter a Name");
                    i = -1;
                }
                if(setDateField.getDateTimeStrict()==null){
                    notice.setText("Notice: Please Select a Date");
                    i = -1;
                }
                if(i>0){
                Task t = new Task(textName.getText(), textNotes.getText(),setDateField.getDateTimeStrict().toString(), Helper.toBool(completedField.getText()));
                tasks.add(t);
                refreshTasks();}
            }

        });

        SaveExisting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = 1;
                String name = textName.getText();
                if(name.length() < 1){
                    notice.setText("Notice: Please Enter a Name");
                    i = -1;
                }
                if(setDateField.getDateTimeStrict()==null){
                    notice.setText("Notice: Please Select a Date");
                    i = -1;
                }
                i = taskList.getSelectedIndex();
                if(i >= 0){
                    Task t = tempTasks.get(i);
                    t.setName(textName.getText());
                    t.setNotes(textNotes.getText());
                    t.setDateTime(setDateField.getDateTimeStrict().toString());
                    tempTasks.set(i,t);
                }
                refreshTasks();
            }
        });
        removeTask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                int i = taskList.getSelectedIndex();
                if(i >= 0) {
                    tasks.remove(i);
                    refreshTasks();
                }
            }
        });
        setFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                int i = taskList.getSelectedIndex();
                if(i >= 0) {
                    textCompleted.setText("false");
                    Task t = tempTasks.get(i);
                    t.setCompleted(false);
                    tempTasks.set(i,t);
                }
                refreshTasks();
            }
        });
        setTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                int i = taskList.getSelectedIndex();
                if(i >= 0) {
                    textCompleted.setText("true");
                    Task t = tempTasks.get(i);
                    t.setCompleted(true);
                    tempTasks.set(i,t);
                }
                refreshTasks();
            }
        });
        ViewUpcoming.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                modeSelected = "overdue";
                refreshTasks();
            }
        });
        ViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                modeSelected = "all";
                refreshTasks();
            }
        });
        ViewOverDue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                modeSelected = "upcoming";
                refreshTasks();
            }
        });
        addRandoTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice.setText("");
                tasks.add(Helper.makeRandTask());
                refreshTasks();
            }
        });
    }
    public void refreshTasks(){
        Collections.sort(tasks);

        listTaskModel.removeAllElements();
        if(modeSelected.equals("all")) {
            tempTasks = tasks;
            for (Task t : tasks
            ) {
                listTaskModel.addElement(t.getName());
            }
        }
        if(modeSelected.equals("overdue")){
            ArrayList<Task> overDue = Helper.getOverdue(tasks);
            tempTasks = overDue;
            for (Task t : overDue
            ) {
                if(!t.isCompleted()) {
                    listTaskModel.addElement(t.getName());
                }
            }

        }
        if(modeSelected.equals("upcoming")){
            ArrayList<Task> upcoming = Helper.getUpcoming(tasks);
            tempTasks = upcoming;
            for (Task t : upcoming
            ) {
                if(!t.isCompleted()) {
                    listTaskModel.addElement(t.getName());
                }

            }


        }

        Save.saveTask(tasks);
    }

}
