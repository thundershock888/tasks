package ca.cmpt213.a4.model;
import com.github.lgooddatepicker.components.DateTimePicker;

import java.util.GregorianCalendar;

public class Task{
	private String name;
	private String notes;
	String dateTime;

	private boolean completed;

	public Task(String name, String notes, String dateTime, boolean completed) {
		this.name = name;
		this.notes = notes;
		this.dateTime = dateTime;
		this.completed = completed;
	}
	public void printSelf(){
		System.out.println(toText());
	}
	public String toText(){
		String text = "";
		text+= name + "\n";
		text+= notes + "\n";
		text+= dateTime + "\n";
		text+= completed;
		return text;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCompletedAsString(){
		if(!completed){
			return "false";
		}
		return "true";
	}



	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
