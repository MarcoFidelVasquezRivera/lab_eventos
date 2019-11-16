package model;
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Auditorium{
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//					   ATTRIBUTES
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public final static String OCUPADO = "ocupado";
	public final static String DESOCUPADO = "desocupado";
	private String name;
	private String nameBuilding;
	private String status;
	private Chair[][] chairs;
	private ArrayList<Event> events;
	private Random r;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//						METHODS
//+++++++++++++++++++++++++++++++++++++++++++++++++++++ 
	public Auditorium(String name, String nameBuilding){
		this.name=name;
		this.nameBuilding=nameBuilding;
		this.status=DESOCUPADO;
		chairs= null;
		events=new ArrayList<Event>();
		r=new Random();
	}

	public String getName(){
		return name;
	}

	public String getStatus(){
		return status;
	}

	public String getNameBuilding(){
		return nameBuilding;
	}

	public Chair[][] getChairs(){
		return chairs;
	}

	public ArrayList<Event> getEvents(){
		return events;
	}

/**
*<b>Name:</b> addEvent.<br>
*This method saves a new event.<br>
*<b>pre:</b> at least one auditorium has been created.<br>
*@param name name of the event.<br>
*@param day the day when will be the event.<br>
*@param startHour the hour when the event starts.<br>
*@param endHour the hour when the event ends.<br>
*@param responsibleTeacherName the name of the teacher responsible of the event.<br>
*@param responsibleFaculty the name of the faculty responsible of the event.<br>
*@param numberPeopleAttended the number of perople that will attend to the event<br>
*@param year the year when will be the event<br>
*@param month the month when will be the event<br>
*<b>Pos:</b> a new event has been saved.<br>
*/
	public void addEvent(String name, int day, int startHour, int endHour, String responsibleTeacherName, String responsibleFaculty,int year,int month){
		Event event= new Event(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,year,month);
		events.add(event);
	}

/**
*<b>Name:</b> occupyChair.<br>
*This method occupy tha chairs of an auditorium and report an assistant.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*<b>Pos:</b> chair's status was changed to defective and not avaible and assistant was reported.<br>
*/
	public void occupyChair(){
		int people=0;
		int posX=r.nextInt(chairs.length);
		int posY=r.nextInt(chairs[posX].length);
		boolean flag=false;
		LocalDateTime date = LocalDateTime.now();

		if(chairs[posX][posY].getStatusForEvent().equalsIgnoreCase(Chair.DISPONIBLE)){
			chairs[posX][posY].setStatusForEvent(Chair.NODISPONIBLE);
		}
		else{
			for(int i=0;i<chairs.length;i++){//error por lo de posx

				for(int j=0;j<chairs[i].length;j++){
					if(chairs[posX][posY].getStatusForEvent().equalsIgnoreCase(Chair.DISPONIBLE)){
						chairs[posX][posY].setStatusForEvent(Chair.NODISPONIBLE);
					}
				}			
			}
		}

		for(int j=0;j<events.size() && !flag;j++){
			if(events.get(j).getYear()==date.getYear() && events.get(j).getMonth()==date.getMonthValue() && events.get(j).getDay()==date.getDayOfMonth() && events.get(j).getStartHour()<=date.getHour() && events.get(j).getEndHour()>date.getHour()){
				people=events.get(j).getPeopleAttended()+1;
				events.get(j).setPeopleAttended(people);
				flag=true;
			}
		}

	}

	public void setStatus(String status){
		this.status=status;
	}

/**
*<b>Name:</b> emptyChairs.<br>
*This method to empty the chairs of an auditorium, but if the chair is defective the chair does not change.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*<b>Pos:</b> chair's status was changed to ofective and avaible.<br>
*/
	public void emptyChairs(){
		for(int i=0;i<chairs.length;i++){
			for(int j=0;j<chairs[i].length;j++){
				if(chairs[i][j].getStatus().equalsIgnoreCase(Chair.OPERATIVA)){
					chairs[i][j].setStatusForEvent(Chair.DISPONIBLE);
				}
			}
		}
	}

/**
*<b>Name:</b> setChairs.<br>
*This method make the chairs of an auditorium.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param chairsToMake an array of int that helps to do the mareix of chairs.<br>
*<b>Pos:</b> chairs´ matrix has been created.<br>
*@return  return a message that report if the matrix was created successfully or an error happened.<br>
*/
	public String setChairs(int[] chairsToMake){
		boolean flag=false;
		String message="";
		Chair[][] newChairs;

			if(chairs==null){
				newChairs= new Chair[chairsToMake.length][];

				for(int i=0;i<chairsToMake.length;i++){
					newChairs[i] = new Chair[chairsToMake[i]];

					for(int j=0;j<newChairs[i].length;j++){
						newChairs[i][j]=new Chair(Chair.OPERATIVA,Chair.DISPONIBLE);
					}
				}
				chairs=newChairs;
				flag=true;
			}
			else{
				message="chairs already exist";
			}

		return message;
	}

/**
*<b>Name:</b> changeChairStatus.<br>
*This method report a chair defective.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@param row row where is the chair that will be reported.<br>
*@param column column where is the chair that will be reported.<br>
*@param description description of the defect.<br>
*<b>Pos:</b> chair's status was changed to defective and not avaible.<br>
*@return  return a message that report if the chair's status was changed successfully or the chair is already deported as defective.<br>
*/
	public String changeChairStatus(int row, int column, String description){
		String message="";
		if(chairs[row][column].getStatus().equalsIgnoreCase(Chair.OPERATIVA)){
			chairs[row][column].setStatus(Chair.DEFECTUOSA);
			chairs[row][column].setStatusForEvent(Chair.NODISPONIBLE);
			chairs[row][column].setDescriptionDefective(description);	
			message="status changed successfully";	
		}
		else{
			message="chair is already defective";
		}
		return message;
	}

/**
*<b>Name:</b> showChairs.<br>
*This method show the chairs of an auditorium.<br>
*<b>pre:</b> at least has been created one auditorium.<br>
*@return  return a message with the chairs' information.<br>
*/
	public String showChairs(){
		String message="";
		char[] row={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		for(int i=0;i<chairs.length;i++){
			message=message+String.valueOf(row[i]);

			for(int j=0;j<chairs[i].length;j++){
				message=message+"|"+String.valueOf(j+1)+":"+chairs[i][j].getStatus()+" - "+chairs[i][j].getStatusForEvent();
			}

			message=message+"|"+"\n";
		}
		return message;
	}

	public int getTotalChairs(){
		int totalChairs=0;

		for(int i=0;i<chairs.length;i++){
			for(int j=0;j<chairs[i].length;j++){
				totalChairs++;
			} 
		}
		return totalChairs;
	}

	public int getDefectiveChairs(){
		int defectiveChairs=0;

		for(int i=0;i<chairs.length;i++){
			for(int j=0;j<chairs[i].length;j++){
				if(chairs[i][j].getStatus().equalsIgnoreCase(Chair.DEFECTUOSA)){
					defectiveChairs++;
				}
			} 
		}
		return defectiveChairs;
	}
//recuerda lo de la hora pinche puto 
}