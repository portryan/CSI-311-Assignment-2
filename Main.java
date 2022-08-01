/*
 * Main class meant to start program
 * @author Ryan Porter
 * @version 1.0 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		while (true) {
		System.out.print("\n{1} Search by name\n{2} Search by birthday month\n{3} Search by birthday day\n{4} Search by birthday year\n{5} List Everyone\n{6} Enter new person\n{7} Edit Person\n{8} Remove Person\n{9} Quit\n");
		Scanner input = new Scanner(System.in);
		String name = "";
		int month = 0;
		int day = 0;
		int year = 0;
		Person p;
		LinkedList<Person> people = new LinkedList<Person>();
		try {
			File adt = new File("ADT.txt");
			Scanner sc = new Scanner(adt);
			while (sc.hasNextLine()) {
				people.add(lineToPerson(sc.nextLine())); // searches file for data and adds it to people list
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
		}
		
		
		switch(input.nextInt()) {
		case 1: //Searches by name
			input.nextLine();
			System.out.println("Enter name:");
		    name = input.nextLine();
		    new BST(people,name,-1,1); // Starts new tree searching by name  
			break;
		case 2: //Searches by month
			System.out.println("Enter month (MM):");
			month = input.nextInt();
			new BST(people,"",month,2); //Starts new tree searching by month
			break;
		case 3: // Search by day
			System.out.println("Enter day (DD):");
			day = input.nextInt();
			new BST(people,"",day,3); //Starts new tree searching for day
			break;
		case 4: // Search by year
			System.out.println("Enter year (YYYY):");
			year = input.nextInt();
			new BST(people,"",year,4); //Starts new tree searching for year
			break;
		case 5: // List all people ordered alphabetically
			new BST(people,"",-1,5);
			break;
		case 6: // Enter new person
			input.nextLine();
			System.out.println("Enter name:");
			name = input.nextLine();
			System.out.println("Enter month (MM):");
			month = input.nextInt();
			System.out.println("Enter day (DD):");
			day = input.nextInt();
			System.out.println("Enter year (YYYY):");
			year = input.nextInt();
			p = new Person(name,month,day,year);
			try {
				FileWriter myWriter = new FileWriter("ADT.txt",true);
				myWriter.write(p.toString() + "\n"); // Writes new person to file
			    myWriter.close();
			    System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
			break;
		case 7: // Edits person by searching for name
			System.out.println("Enter name of person to edit: ");
			input.nextLine();
			name = input.nextLine();
			new BST(people,name,-1,6);
			break;
		case 8: // Removes person by searching for name
			System.out.println("Enter name of person to remove: ");
			input.nextLine();
			name = input.nextLine();
			new BST(people,name,-1,7);
			break;
		case 9:
			System.exit(0);
			break;
		}
		
		}
	}
	
	public static Person lineToPerson(String s) { //Takes line from file and creates person
		String name;
		int month, day, year;
		int p1 = s.indexOf('|',0); // finds location of first |
		int p2 = s.indexOf('|',p1+1); // finds location of second |
		int p3 = s.indexOf('|',p2+1);// finds location of third |
		
		name = s.substring(0,p1); // sets name
		month = Integer.parseInt(s.substring(p1+1,p2)); // sets month
		day = Integer.parseInt(s.substring(p2+1,p3)); // sets day
		year = Integer.parseInt(s.substring(p3+1)); // sets year
		
		return new Person(name,month,day,year);
	}
}
