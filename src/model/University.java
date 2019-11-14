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

	public String addEvent(String name, int day, int startHour, int endHour, String responsibleTeacherName, String responsibleFaculty, int numberPeopleAttended, int[] auditoriums,int year,int month){
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
				else if((startHour>events.get(i).getStartHour() && startHour<events.get(i).getEndHour()) || (endHour>events.get(i).getStartHour() && endHour<events.get(i).getEndHour())){
					flag=true;
					message="evend can't be saved because it would overwrite other event";
				}
			}
		}
		//hacer la parte de que no se crucen las horas

		for(int i=0;i<auditoriums.length && !flag;i++){
			auditorium.get(auditoriums[i]-1).addEvent(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,numberPeopleAttended,year,month);
			message="event has been saved successfully";
		}

		return message;
	}

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

	public String showChairs(String auditoriumName){
		String message="";
		boolean flag=false;

		for(int i=0;i<auditorium.size() && !flag;i++){
			if(auditorium.get(i).getName().equalsIgnoreCase(auditoriumName)){
				System.out.println("dududu");
				message=auditorium.get(i).showChairs();
				System.out.println("kokoko");
				flag=true;
			}
		}
		return message;
	}

	public String showAuditorium(){
		String message="";
		ArrayList<Event> events;
		LocalDateTime date= LocalDateTime.now();

		for(int i=0;i<auditorium.size();i++){
			events=auditorium.get(i).getEvents();
			boolean flag=false;

			for(int j=0;j<events.size() && !flag;j++){
				if(events.get(j).getYear()==date.getYear() && events.get(j).getMonth()==date.getMonthValue() && events.get(j).getDay()==date.getDayOfMonth() && events.get(j).getStartHour()<=date.getHour() && events.get(j).getEndHour()>date.getHour()){
					auditorium.get(j).setStatus(Auditorium.OCUPADO);
					flag=true;
				}
			}
			message=message+String.valueOf(i+1)+": "+auditorium.get(i).getName()+"-"+auditorium.get(i).getStatus()+"\n";
		}
		return message;
	}

	public String defectiveChairPercent(String auditoriumName){
		String message="";
		double percent=0.0;
		double totalChairs=0;
		double defectiveChairs=0;
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

	public int getAuditoriumsQuantity(){
		int quantity=auditorium.size();
		return quantity;
	}

}