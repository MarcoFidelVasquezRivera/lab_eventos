package model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class University{
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//					   ATTRIBUTES
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	private ArrayList<Auditorium> auditorium;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//						METHODS
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public University(){
		auditorium=new ArrayList<Auditorium>();
	}

/**
*<b>Name:</b> addAuditorium.<br>
*This method save an auditorium into the program.<br>
*<b>pre:</b> array auditoriums is created and initialized.<br>
*@param name name of the auditorium. <br>
*@param photo the direction where is the foto of the angel.<br>
*<b>Pos:</b> auditorium has been saved.<br>
*@return  return a message that report if the auditorium was saved successfully or an error happened<br>
*/
	public String addAuditorium(String name, String nameBuilding){
		String message="";
		boolean flag=false;

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(name)){
				message="this auditorium already exist";
				flag=true;
			}

		}

		if(!flag){
			Auditorium newAuditorium = new Auditorium(name, nameBuilding);
			auditorium.add(newAuditorium);
			message="auditorium has been saved successfully";
		}

		return message;
	}

/**
*<b>Name:</b> addEvent.<br>
*This method saves an event into the program.<br>
*<b>pre:</b> at least one auditorium has been created.<br>
*@param name it's the name of the event.<br>
*@param day the day when will be the event.<br>
*@param startHour the hour when the event starts.<br>
*@param endHour the hour when the event ends.<br>
*@param responsibleTeacherName the name of the teacher responsible of the event.<br>
*@param responsibleFaculty the name of the faculty responsible of the event.<br>
*@param auditoriums an array of String with the name of the the auditoriums that the event will use.<br>
*@param year the year when will be the event<br>
*@param month the month when will be the event<br>
*<b>Pos:</b> event has been saved and put int the auditoriums that will use.<br>
*@return return a message that reports if the event was saved successfully or an error happened<br>
*/
	public String addEvent(String name, int day, int startHour, int endHour, String responsibleTeacherName, String responsibleFaculty, String[] auditoriums,int year,int month){
		String message="";
		boolean flag=false;
		ArrayList<Event> events;

		for(int i=0;i<auditorium.size() && !flag;i++){
			events = auditorium.get(i).getEvents();

			for(int j=0;j<events.size() && !flag;j++){
				if(events.get(i).getName().equalsIgnoreCase(name)){
					flag=true;
					message="this event already exist";
				}
				else if((startHour>=events.get(i).getStartHour() && startHour<=events.get(i).getEndHour()) || (endHour>=events.get(i).getStartHour() && endHour<=events.get(i).getEndHour())){
					flag=true;
					message="event cannot be saved because it would overwrite another event";
				}
			}
		}

		for(int i=0;i<auditorium.size() && !flag;i++){
			for(int j=0;j<auditoriums.length;j++){
				if(auditorium.get(i).getName().equalsIgnoreCase(auditoriums[j])){
					auditorium.get(i).addEvent(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,year,month);
					message="event has been saved successfully";					
				}
			}
		}

		return message;
	}

/**
*<b>Name:</b> makeChairsAuditorium.<br>
*This method makes the matrix of chairs that the auditorium will have.<br>
*<b>pre:</b> at least one auditorium has been created.<br>
*@param chairsToMake an array of int that helps to do the mareix of chairs.<br>
*@param auditoriumName the name of the auditorium where will be the matrix of chairs.<br>
*<b>Pos:</b> chairs´ matrix has been created.<br>
*@return  return a message that report if the matrix was created successfully or an error happened.<br>
*/
	public String makeChairsAuditorium(int[] chairsToMake, String auditoriumName){

		boolean flag=false;
		String message="";

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName)){
				message=auditorium.get(i).setChairs(chairsToMake);

				flag=true;
			}
			else{
				message="auditorium not found";
			}

		}
		return message;
	}

/**
*<b>Name:</b> reportChairDefective.<br>
*This method report a chair defective.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param auditoriumName name of the auditorium where is the chair that will be reported.<br>
*@param row row where is the chair that will be reported.<br>
*@param column column where is the chair that will be reported.<br>
*@param description description of the defect.<br>
*<b>Pos:</b> chair's status was changed to defective and not avaible.<br>
*@return  return a message that report if the chair's status was changed successfully or an error happened.<br>
*/
	public String reportChairDefective(String auditoriumName, int row, int column, String description){
		boolean flag=false;
		String message="";

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName) && auditorium.get(i).getChairs().length>row){
				if(auditorium.get(i).getChairs()[row].length>column){

					message=auditorium.get(i).changeChairStatus(row,column,description);
						
				}
				flag=true;		
			}
			else{
				message="matriz row is smaller than the row given";
			}

		}
			return message;
	}


/**
*<b>Name:</b> showChairs.<br>
*This method show the chairs of an auditorium.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param auditoriumName name of the auditorium where are the chairs.<br>
*@return  return a message with the chairs' information.<br>
*/
	public String showChairs(String auditoriumName){
		String message="";
		boolean flag=false;

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName)){
	
				message=auditorium.get(i).showChairs();
				flag=true;
			}
		}
		return message;
	}

/**
*<b>Name:</b> showAuditorium.<br>
*This method Shows every auditorium saved.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@return  return a message with the auditorium's name and its status.<br>
*/
	public String showAuditorium(){
		String message="";
		ArrayList<Event> events;
		LocalDateTime date = LocalDateTime.now();

		for(int i=0;i<auditorium.size();i++){
			events=auditorium.get(i).getEvents();

			for(int j=0;j<events.size();j++){
				if(events.get(j).getYear()==date.getYear() && events.get(j).getMonth()==date.getMonthValue() && events.get(j).getDay()==date.getDayOfMonth() && events.get(j).getStartHour()<=date.getHour() && events.get(j).getEndHour()>date.getHour()){
					auditorium.get(i).setStatus(Auditorium.OCUPADO);
				}
				else if(auditorium.get(i).getStatus().equalsIgnoreCase(Auditorium.OCUPADO)){
					auditorium.get(i).emptyChairs(); 
				}

			}

			message=message+String.valueOf(i+1)+": "+auditorium.get(i).getName()+"-"+auditorium.get(i).getStatus()+"\n";
		}
		return message;
	}


/**
*<b>Name:</b> defectiveChairPercent.<br>
*This method calculate the percent of defective chairs.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param auditoriumName name of the auditorium where are the chairs.<br>
*@return  return a message with the percent or reporting if an error happened.<br>
*/
	public String defectiveChairPercent(String auditoriumName){
		String message="";
		double percent=0.0;
		double totalChairs=0.0;
		double defectiveChairs=0.0;
		boolean flag=false;

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName)){
				totalChairs=(double) auditorium.get(i).getTotalChairs();
				defectiveChairs=(double)auditorium.get(i).getDefectiveChairs();
				percent=(defectiveChairs*100)/totalChairs;
				message=String.valueOf(percent);
				flag=true;
			}
			else{
				message="auditorium not found";
			}
		}
		return message;
	}

/**
*<b>Name:</b> getAuditoriumsQuantity.<br>
*This method get and delivery the quantity of auditoriums saved.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@return  return a message with the quantity of auditoriums.<br>
*/
	public int getAuditoriumsQuantity(){
		int quantity=auditorium.size();
		return quantity;
	}

/**
*<b>Name:</b> reportAssistantEvent.<br>
*This method report the assistant of an event.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param auditoriumName name of the auditorium where is the assistant will assist.<br>
*@return  return a message that informs if the assistant reported successfully or an error has happened.<br>
*/
	public  String reportAssitantEvent(String auditoriumName){
		String message="";
		boolean flag=false;

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName) && auditorium.get(i).getStatus().equalsIgnoreCase(Auditorium.OCUPADO)){
				auditorium.get(i).occupyChair();
				message="assistant reported sucessfully";
			}
			else{
				message="auditorium not found or the auditorium is not in a event";
			}
		}

		return message;
	}

/**
*<b>Name:</b> showPastEvents.<br>
*This method how the name and the number of people who attended to the events that already happend.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@return  return a message whit the name and the number of people who attended.<br>
*/
	public String showPastEvents(){
		ArrayList<Event> events;
		String message="";
		LocalDateTime date = LocalDateTime.now();

		for(int i=0;i<auditorium.size();i++){
			events=auditorium.get(i).getEvents();

			for(int j=0;j<events.size();j++){

				if(events.get(j).getYear()<=date.getYear() && events.get(j).getMonth()<=date.getMonthValue() && events.get(j).getDay()<date.getDayOfMonth()){
					message=events.get(i).getName()+"-"+events.get(i).getPeopleAttended()+" people attended \n";

				}
				else if(events.get(j).getYear()==date.getYear() && events.get(j).getMonth()==date.getMonthValue() && events.get(j).getDay()==date.getDayOfMonth() && events.get(j).getEndHour()<=date.getHour()){
					message=events.get(i).getName()+"-"+events.get(i).getPeopleAttended()+" people attended \n";
				}
			}
		}
		return message;
	}

}