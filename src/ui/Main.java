package ui;

import model.*;
import java.util.Scanner;

public class Main{
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//					   ATTRIBUTES
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	private University university;
	private static Scanner reader = new Scanner(System.in);
	private static Scanner readerTwo = new Scanner(System.in);
	private static Scanner readerThree = new Scanner(System.in);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//						METHODS
//+++++++++++++++++++++++++++++++++++++++++++++++++++++

	public Main(){
		university= new University();
	}

	public static void main(String[]args){
		Main main=new Main();
		int operation=0;
		boolean exit=false;
		System.out.println("wellcome");



		while(!exit){
		main.showMenu();
		operation=readerTwo.nextInt();

			while(operation<1 && operation>4){
				System.out.println("the number given must be between 1 and 4");
				operation=readerTwo.nextInt();
			}

			switch(operation){

				case 1:	System.out.println(main.addAuditorium());
						break;

				case 2:	System.out.println(main.university.showAuditorium());
						break;

				case 3:	System.out.println(main.addEvent());
						break;

				case 4:	System.out.println(main.defectiveChairsPercent()); 
						break;

				case 5:	System.out.println(main.reportDefectiveChair());
						break;

				case 6:	System.out.println(main.showChairs());
						break;

				case 7: System.out.println(main.reportAssistant());
						break;

				case 8:	System.out.println(main.university.showPastEvents());
						break;

				case 9: exit=true;
						break;

			}

		}

		System.out.println("thank you for your use");
	}

/**
*<b>Name:</b> showMenu.<br>
*This method shows the principal menu.<br>
*/
	public void showMenu(){
		System.out.println("please enter the operation that you want to do:");
		System.out.println("1: add a new auditorium");
		System.out.println("2: show auditoriums");
		System.out.println("3: add a new event");
		System.out.println("4: calculate the percent of defective chairs in an auditorium");
		System.out.println("5: report a defective chair");
		System.out.println("6: show chairs of an auditorium");
		System.out.println("7: report assistent to an event");
		System.out.println("8: show past events");
		System.out.println("9: exit");
	}

/**
*<b>Name:</b> addAuditorium.<br>
*This method shows the menu to add an auditorium, get its attributes and call the method to save the auditorium<br>
*<b>Pos:</b> an auditorium has been created.<br>
*@return return a message that say if the auditorium has been saved or a problem has happened<br>
*/
	public String addAuditorium(){
		String name="";
		String nameBuilding="";
		String message="";
		int rows=0;
		int[] chairsToMake;

		System.out.println("please enter the auditorium's name");
		name=readerThree.nextLine();
		System.out.println("please enter the building's name where is the auditorium");
		nameBuilding=reader.next();
		message=university.addAuditorium(name,nameBuilding);

		System.out.println("please enter the number of rows of chairs, it must be lower or equals than 26");
		rows=readerTwo.nextInt();

		while(rows>26 || rows<1){
			System.out.println("please enter  number between 1 and 26");
			rows=readerTwo.nextInt();
		}
		chairsToMake= new int[rows];

		for(int i=0;i<chairsToMake.length;i++){
			System.out.println("please enter the number of chairs of the row "+(i+1));
			chairsToMake[i]=readerTwo.nextInt();
		}

		university.makeChairsAuditorium(chairsToMake,name);
		return message;
	}

/**
*<b>Name:</b> addEvent.<br>
*This method shows the menu to add an event, get its attributes and call the method to save the event<br>
*<b>Pos:</b> an event has been created.<br>
*@return return a message that say if the event has been saved or a problem has happened<br>
*/
	public String addEvent(){
		String name="";
		int day=0;
		int startHour=0; 
		int endHour=0;
		String responsibleTeacherName=""; 
		String responsibleFaculty="";
		int numberPeopleAttended=0;
		int year=0;
		int month=0;
		int quantityAuditoriums=0;
		String[]auditoriums;
		String message="";

		System.out.println("please enter the event's name");
		name=readerThree.nextLine();
		System.out.println("plese enter the year when is the event");
		year=readerTwo.nextInt();
		System.out.println("please enter the number of the month when is the event");
		month=readerTwo.nextInt();
		while(month>12 || month<1){
			System.out.println("the number must be between 1 and 12");
			month=readerTwo.nextInt();
		}
		System.out.println("please enter the day of the event");
		day=readerTwo.nextInt();
		System.out.println("please enter the name of the teacher responsible of the event");
		responsibleTeacherName=readerThree.nextLine();
		System.out.println("please enter the name of the faculty responsible of the event");
		responsibleFaculty=readerThree.nextLine();
		System.out.println("please enter the start hour of the event, remember that the auditories are oppened since 7 hours until 20 hours");
		startHour=readerTwo.nextInt();

		while(startHour<7 || startHour>20){
			System.out.println("remember that the auditories are oppened since 7 hours until 20 hours");
			startHour=readerTwo.nextInt();
		}

		System.out.println("please enter the end hour of the event, remember that the auditories are oppened since 7 hours until 20 hours");
		endHour=readerTwo.nextInt();
		while(endHour<7 && endHour>20 || (endHour-startHour)>12){
			System.out.println("remember that the auditories are oppened since 7 hours until 20 hours and an event can only last 12 hours");
			endHour=readerTwo.nextInt();
		}

		System.out.println(university.showAuditorium());

		System.out.println("please enter how many auditoriums will be needed for the event");
		quantityAuditoriums=readerTwo.nextInt();
		while(quantityAuditoriums>university.getAuditoriumsQuantity() || quantityAuditoriums<1){
			System.out.println("the number must be between 1 and the number maximun of auditoriums");
			quantityAuditoriums=readerTwo.nextInt();
		}

		auditoriums=new String[quantityAuditoriums];

		for(int i=0;i<auditoriums.length;i++){
			System.out.println("please enter the name of the auditorium number "+(i+1)+" that you will use");
			auditoriums[i]=readerTwo.next();
		}

		message=university.addEvent(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,auditoriums,year,month);

		return message;
	}

/**
*<b>Name:</b> defectiveChairPercent.<br>
*This method shows the menu to get the percent of defective chairs<br>
*@return return a message that containc the percent of chairs defectives<br>
*/
	public String defectiveChairsPercent(){
		String message="";
		String auditoriumName="";

		System.out.println("please enter the auditorium's name");
		auditoriumName=reader.next();

		message=university.defectiveChairPercent(auditoriumName);

		return message;
	}

/**
*<b>Name:</b> reportDefectiveChair.<br>
*This method shows the menu report a defective chair <br>
*<b>Pos:</b> the status of the chair has been changed to "defectusoda" an its status for events has been changed to "no disponible".<br>
*@return return a message that informs if the chair has been reported successfully or an error has happened<br>
*/
	public String reportDefectiveChair(){
		String message="";
		String auditoriumName="";
		String description="";
		int row=0;
		int column=0;

		System.out.println("please enter the auditorium's name where is the chair");
		auditoriumName=reader.next();

		System.out.println(university.showChairs(auditoriumName));

		System.out.println("please enter the number of row where is the chair");
		row=readerTwo.nextInt();
		row--;
		System.out.println("please enter the number of column where is the chair");
		column=readerTwo.nextInt();
		column--;
		System.out.println("please enter a description of the defect");
		description=readerThree.nextLine();

		message=university.reportChairDefective(auditoriumName,row,column,description);

		return message;
	}

/**
*<b>Name:</b> showChairs.<br>
*This method shows the chairs of an auditorium<br>
*@return return a message with the chairs' information<br>
*/
	public String showChairs(){
		String message="";
		String auditoriumName="";
		System.out.println("please enter the auditorium's name where is the chair");
		auditoriumName=reader.next();

		System.out.println(university.showChairs(auditoriumName));

		return message;
	}

/**
*<b>Name:</b> reportAssistant.<br>
*This method shows the menu report an assistant and call the method to report it<br>
*<b>Pos:</b> assistant has been repoted.<br>
*@return return a message that informs if the assistant reported successfully or an error has happened<br>
*/
	public String reportAssistant(){
		String message="";
		String auditoriumName="";

		System.out.println(university.showAuditorium());
		System.out.println("please enter the auditorium's name");
		auditoriumName=reader.next();

		message=university.reportAssitantEvent(auditoriumName);

		return message;
	}

/**
*<b>Name:</b> init.<br>
*This method makes two auditoriums<br>
*<b>Pos:</b> auditoriums has been created.<br>
*/
	public void init(){
		int[] chairsOne=new int [3];
		chairsOne[0]=2;
		chairsOne[1]=2;
		chairsOne[2]=2;

		int[] chairsTwo=new int [2];
		chairsTwo[0]=3;
		chairsTwo[1]=3;

		university.addAuditorium("manuelita","L");
		university.addAuditorium("varela","G");

		university.makeChairsAuditorium(chairsOne,"manuelita");
		university.makeChairsAuditorium(chairsTwo,"varela");
	}

}