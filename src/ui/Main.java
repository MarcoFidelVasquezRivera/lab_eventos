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

				case 6: exit=true;


			}


		}





	}

	public void showMenu(){
		System.out.println("please enter the operation that you want to do:");
		System.out.println("1: add a new auditorium");
		System.out.println("2: show auditoriums");
		System.out.println("3: add a new event");
		System.out.println("4: calculate the percent of defective chairs in an auditorium");
		System.out.println("5: report a defective chair");
		System.out.println("6: exit");
	}

	public String addAuditorium(){
		String name="";
		String nameBuilding="";
		String message="";
		int rows=0;
		int[] chairsToMake;

		System.out.println("please enter the auditorium's name");
		name=reader.next();
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
		int[]auditoriums;
		String message="";

		System.out.println("please enter the event's name");
		name=reader.next();
		System.out.println("plese enter the year when is the event");
		year=readerTwo.nextInt();
		System.out.println("please enter the number of the month when is the event");
		month=readerTwo.nextInt();
		while(month>12 || month<1){
			System.out.println("the number must be between 1 and 12");
			month=readerTwo.nextInt();
		}
		System.out.println("please enter the day when is the event");
		day=readerTwo.nextInt();
		System.out.println("please enter the name of the teacher responsible of the event");
		responsibleTeacherName=readerThree.nextLine();
		System.out.println("please enter the name of the faculty responsible of the event");
		responsibleFaculty=readerThree.nextLine();
		System.out.println("please enter the number of people that will attend");
		numberPeopleAttended=readerTwo.nextInt();
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

		auditoriums=new int[quantityAuditoriums];

		for(int i=0;i<auditoriums.length;i++){
			System.out.println("please enter the number of the auditorium number "+(i+1)+" that you will use");
			auditoriums[i]=readerTwo.nextInt();
		}

		message=university.addEvent(name,day,startHour,endHour,responsibleTeacherName,responsibleFaculty,numberPeopleAttended,auditoriums,year,month);

		return message;
	}

	public String defectiveChairsPercent(){
		String message="";
		String auditoriumName="";

		System.out.println("please enter the auditorium's name");
		auditoriumName=reader.next();

		message=university.defectiveChairPercent(auditoriumName);

		return message;
	}

	public String reportDefectiveChair(){
		String message="";
		String auditoriumName="";
		String description="";
		int row=0;
		int column=0;

		System.out.println("please enter the auditorium's name where is the chair");
		auditoriumName=reader.next();

		System.out.println(university.showChairs(auditoriumName));

		System.out.println("please enter the row where is the chair");
		row=readerTwo.nextInt();
		row--;
		System.out.println("please enter the column where is the chair");
		column=readerTwo.nextInt();
		column--;
		System.out.println("please enter a description of the defect");
		description=readerThree.nextLine();

		message=university.reportChairDefective(auditoriumName,row,column,description);

		return message;
	}

}