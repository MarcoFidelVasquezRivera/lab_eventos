package model;

import java.util.Random;
import java.util.ArrayList;

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

	public void addEvent(String name, int day, int startHour, int endHour, String responsibleTeacherName, String responsibleFaculty, int numberPeopleAttended,int year,int month){
		Event event= new Event(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,numberPeopleAttended,year,month);
		events.add(event);
	}

	public void occupyChair(){
		boolean flag=false;
		

		while(!flag){
			int count=0;
			for(int i=0;i<chairs.length;i++){

				for(int j=0;j<chairs[i].length;j++){
					if(chairs[i][j].getStatusForEvent().equalsIgnoreCase(Chair.DISPONIBLE)){
						count++;
					}
				}
			}

			if(count>0){
				for(int k=0;k<count;k++){

					int posX=r.nextInt(chairs.length);
					int posY=r.nextInt(chairs[posX].length);

					if(chairs[posX][posY].getStatusForEvent().equalsIgnoreCase(Chair.DISPONIBLE)){
						chairs[posX][posY].setStatusForEvent(Chair.NODISPONIBLE);
					}
					else{
						for(int i=0;i<chairs[posX].length;i++){

							for(int j=0;j<chairs[i].length;j++){
								if(chairs[posX][posY].getStatusForEvent().equalsIgnoreCase(Chair.DISPONIBLE)){
									chairs[posX][posY].setStatusForEvent(Chair.NODISPONIBLE);
								}
							}			
						}
					}
				}
			}
			else{
				flag=true;
			}
		}
	}

	public void setStatus(String status){
		this.status=status;
	}

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

	public String changeChairStatus(int row, int column, String description){
		String message="";
		if(chairs[row][column].getStatus().equalsIgnoreCase(Chair.DEFECTUOSA)){
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

	public String showChairs(){
		String message="";
		char[] row={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		System.out.println("argaergethathat54");
		for(int i=0;i<chairs.length;i++){
			System.out.println("me la pela hp vida de mierda");
			message=message+String.valueOf(row[i]);

			for(int j=0;j<chairs[i].length;j++){
				System.out.println("zorosuja");
				message=message+"|"+String.valueOf(j+1)+chairs[i][j].getStatus()+" - "+chairs[i][j].getStatusForEvent();
			}

			message=message+"|"+"\n";
		}
		System.out.println("pepepepepe");
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