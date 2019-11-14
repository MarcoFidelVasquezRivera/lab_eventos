package model;

public class Event{
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//					   ATTRIBUTES
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String name;
	private int year;
	private int month;
	private int day;
	private int startHour;
	private int endHour;
	private String responsibleTeacherName;
	private String responsibleFaculty;
	private int numberPeopleAttended;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//						METHODS
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Event(String name, int day, int startHour, int endHour, String responsibleTeacherName, String responsibleFaculty, int numberPeopleAttended,int year,int month){
		this.name=name;
		this.day=day;
		this.startHour=startHour;
		this.endHour=endHour;
		this. responsibleTeacherName=responsibleTeacherName;
		this.responsibleFaculty=responsibleFaculty;
		this.numberPeopleAttended=numberPeopleAttended;
		this.year=year;
		this.month=month;
	}

	public int getYear(){
		return year;
	}

	public int getMonth(){
		return month;
	}

	public String getName(){
		return name;
	}

	public int getDay(){
		return day;
	}

	public int getStartHour(){
		return startHour;
	}

	public int getEndHour(){
		return endHour;
	}

	public String getTeacherName(){
		return responsibleTeacherName;
	}

	public String getResponsibleFaculty(){
		return responsibleFaculty;
	}

	public int getPeopleAttended(){
		return numberPeopleAttended;
	}


}