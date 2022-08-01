/*
 * Binary Search Tree class meant to sort and search People depending on given criteria
 * @author Ryan Porter
 * @version 1.0 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class BST {
	LinkedList<Person> people = new LinkedList<Person>(); // List of persons
	LinkedList<Person> resultList = new LinkedList<Person>(); // List of results from searching methods
	public String personListString = ""; // String used in writeFile() and listInOrderString()
	public BST(LinkedList<Person> p,String s, int num,int sel) {
	people = p;
	String name = s; // Name given if there is one
	int dateNum = num; // Number could be month, day, or year if given
	Node n = new Node(p.get(0));
	if (sel == 1) { // Sort by names
		for (int i = 1; i < people.size();i++) {
			nameSort(n,p.get(i)); // Searches Nodes for matching name
		}
			searchName(n,name);
			if (!resultList.isEmpty()) {
				for (int i = 0; i < resultList.size(); i++) { // Print list of all matches
				System.out.println(resultList.get(i)); 
				}
				}else {
				System.out.println("No Results");
				}
	}
	
	else if (sel == 2) { // Sort by month
		for (int i = 1; i < people.size();i++) {
			monthSort(n,p.get(i));
		}
		searchMonth(n,dateNum); // Searches Nodes for matching month
		if (!resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) { // Print list of all matches
			System.out.println(resultList.get(i));
			}
			}else {
			System.out.println("No Results");
			}
	}
	
	else if (sel == 3) { // Sort by day
		for (int i = 1; i < people.size();i++) {
			daySort(n,p.get(i));
		}
		searchDay(n,dateNum); // Searches Nodes for matching day
		if (!resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) { // Print list of all matches
			System.out.println(resultList.get(i)); 
			}
			}else {
			System.out.println("No Results");
			}
	}
	
	else if (sel == 4) { // Sort by year
		for (int i = 1; i < people.size();i++) {
			yearSort(n,p.get(i));
		}
		searchYear(n,dateNum); // Searches Nodes for matching year
		if (!resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) { // Print list of all matches
			System.out.println(resultList.get(i));
			}
			}else {
			System.out.println("No Results");
			}
	}
	
	else if (sel == 5) { // Show all people
		System.out.println("{1} List Alphabetically\n{2} List By Month\n{3} List By Day\n{4} List By Year\n");
		Scanner input = new Scanner(System.in);
		
		switch(input.nextInt()) {
		case 1:
			for (int i = 1; i < people.size();i++) { // sorts alphabetically
			nameSort(n,people.get(i));
			}
			break;
		case 2:
			for (int i = 1; i < people.size();i++) { // sorts by month
				monthSort(n,people.get(i));
			}
			break;
		case 3:
			for (int i = 1; i < people.size();i++) { // sorts by day 
				daySort(n,people.get(i));
			}
			break;
		case 4:
			for (int i = 1; i < people.size();i++) { // sorts by year
				yearSort(n,people.get(i));
			}
			break;
		}	
		listInOrder(n); // prints out list depending how it was sorted
	}
	else if (sel == 6) { // Edit person by name
		for (int i = 1; i < people.size();i++) {
			nameSort(n,p.get(i));
		}
		System.out.println("{1} Edit Name\n{2} Edit Month\n{3} Edit Day\n{4} Edit Year\n");
		Scanner input = new Scanner(System.in);
		Node editedNode;
		switch (input.nextInt()){
		
		case 1:
			System.out.println("New Name:");
			input.nextLine();
			String newName = input.nextLine();
			editedNode = editPersonByName(n, name, newName, 0, 1); // new node with updated info
			writeToFile(editedNode);
			break;
		case 2:
			System.out.println("New Month:");
			int newMonth = input.nextInt();
			editedNode = editPersonByName(n, name, "", newMonth, 2); // new node with updated info
			writeToFile(editedNode);
			break;
		case 3:
			System.out.println("New Day:");
			int newDay = input.nextInt();
			editedNode = editPersonByName(n, name, "", newDay, 3); // new node with updated info
			writeToFile(editedNode);
			break;
		case 4:
			System.out.println("New Year:");
			int newYear = input.nextInt();
			editedNode = editPersonByName(n, name, "", newYear, 4); // new node with updated info
			writeToFile(editedNode);
			break;
		}
	}
	else if (sel == 7) { // Remove person by name
		for (int i = 1; i < people.size();i++) {
			nameSort(n,p.get(i));
		}
		Node removedNode = removePersonByName(n,name); // new node with removed person
		writeToFile(removedNode);
	}
	
}
	
	public void nameSort (Node n, Person p) { // Sorts tree by name in alphabetical order
		String name = p.getName();
		if (name.compareTo(n.getValue().getName()) < 0) { // If name is less than node, recurse left until there is an open spot
			if (n.getLeft() != null) {
				nameSort(n.getLeft(), p);
			}else {
				n.setLeft(new Node(p));
			}
		}else if (name.compareTo(n.getValue().getName()) >= 0) { // If name is greater or equal than node, recurse right until there is an open spot
			if (n.getRight() != null) {
				nameSort(n.getRight(),p);
			}else {
				n.setRight(new Node(p));
			}
		}	
	}
	
	public void monthSort (Node n, Person p) { // Sorts tree in order by month
		int month = p.getMonth();
		if (month <= n.getValue().getMonth()) {// If month is less than node, recurse left until there is an open spot
			if (n.getLeft() != null) {
				monthSort(n.getLeft(), p);
			}else {
				n.setLeft(new Node(p));
			}
		}else if (month > n.getValue().getMonth()) { // If month is greater than node, recurse right until there is an open spot
			if (n.getRight() != null) {
				monthSort(n.getRight(),p);
			}else {
				n.setRight(new Node(p));
			}
		}
	}
	
	public void daySort (Node n, Person p) { // Sorts tree by day
		int day = p.getDay();
		if (day < n.getValue().getDay()) { // If day is less than node, recurse left until there is an open spot
			if (n.getLeft() != null) {
				daySort(n.getLeft(), p);
			}else {
				n.setLeft(new Node(p));
			}
		}else if (day >= n.getValue().getDay()) { // If day is greater than node, recurse right until there is an open spot
			if (n.getRight() != null) {
				daySort(n.getRight(),p);
			}else {
				n.setRight(new Node(p));
			}
		}
	}
	
	
	public void yearSort (Node n, Person p) { // Sorts tree by year
		int year = p.getYear();
		if (year < n.getValue().getYear()) { // If year is less than node, recurse left until there is an open spot
			if (n.getLeft() != null) {
				yearSort(n.getLeft(), p);
			}else {
				n.setLeft(new Node(p));
			}
		}else if (year >= n.getValue().getYear()) { // If year is greater than node, recurse right until there is an open spot
			if (n.getRight() != null) {
				yearSort(n.getRight(),p);
			}else {
				n.setRight(new Node(p));
			}
		}
	}

	
	public void listInOrder(Node n) { // Prints list in current order
		if (n != null) {
			listInOrder(n.getLeft());
			System.out.println(n.getValue());
            listInOrder(n.getRight());
        }
	}
	
	public Node searchName(Node n, String name) { // Searches for name in node tree
		Node temp = new Node();
		if (name.equals(n.getValue().getName())) { // Add node to list and continue searching under it
			resultList.add(n.getValue());
			if (n.getLeft() != null) { 
				searchName(n.getLeft(),name); // recurse left in case there may be multiple results
				}
			if (n.getRight() != null) {
				searchName(n.getRight(),name); // recurse right in case there may be multiple results
				}
			return null;
		}
		if (n.getLeft() == null && n.getRight() == null) { // If there is no children return null
			return null;
		}
		if (name.compareTo(n.getValue().getName()) < 0){ // Go left if node is higher than name
			if (n.getLeft() != null) { 
				temp = n.getLeft();
				}else return null;
		}
		if (name.compareTo(n.getValue().getName()) > 0) { // Go right if node is lower than name
			if (n.getRight() != null) {
			temp = n.getRight();
			}else return null;
		}
		return searchName(temp,name); // Recurse in direction determined from above 2 if statements
	}
	
	public Node searchMonth(Node n, int month) { // Searches for month in node tree
		
		Node temp = new Node();
		if (month == n.getValue().getMonth()) { // Add node to list and continue searching under it
			resultList.add(n.getValue());
			if (n.getLeft() != null) { 
				searchMonth(n.getLeft(),month); // recurse left in case there may be multiple results
				}
			if (n.getRight() != null) {
				searchMonth(n.getRight(),month); // recurse right in case there may be multiple results
				}
			return null;
		}
		if (n.getLeft() == null && n.getRight() == null) { // If there is no children return null
			return null;
		}
		
		if (month < n.getValue().getMonth()){ // Go left if node is higher than month
			if (n.getLeft() != null) { 
			temp = n.getLeft();
			}else return null;
		}
		if (month > n.getValue().getMonth()) { // Go right if node is lower than month
			if (n.getRight() != null) {
			temp = n.getRight();
			}else return null;
		}
		return searchMonth(temp,month); // Recurse in direction determined from above 2 if statements
	}
	
	public Node searchDay(Node n, int day) { // Searches for day in node tree
		if (day == n.getValue().getDay()) { // Add node to list and continue searching under it
			resultList.add(n.getValue());
			if (n.getLeft() != null) { 
				searchDay(n.getLeft(),day); // recurse left in case there may be multiple results
				}
			if (n.getRight() != null) {
				searchDay(n.getRight(),day); // recurse right in case there may be multiple results
				}
			return null;
		}
		if (n.getLeft() == null && n.getRight() == null) { // If there is no children return null
			return null;
		}
		Node temp = new Node();
		if (day < n.getValue().getDay()){ // Go left if node is higher than day
			if (n.getLeft() != null) { 
			temp = n.getLeft();
			}else return null;
		}
		if (day > n.getValue().getDay()) { // Go right if node is lower than day
			if (n.getRight() != null) {
			temp = n.getRight();
			}else return null;
		}
		return searchDay(temp,day); // Recurse in direction determined from above 2 if statements
	}
	
	public Node searchYear(Node n, int year) { // Searches for year in node tree
		if (year == n.getValue().getYear()) {// Add node to list and continue searching under it
			resultList.add(n.getValue());
			if (n.getLeft() != null) { 
				searchYear(n.getLeft(),year);
				}
			if (n.getRight() != null) {
				searchYear(n.getRight(),year);
				}
			return null;
		}
		if (n.getLeft() == null && n.getRight() == null) { // If there is no children return null
			return null;
		}
		Node temp = new Node();
		if (year < n.getValue().getYear()){ // Go left if node is higher than year
			if (n.getLeft() != null) { 
			temp = n.getLeft();
			}else return null;
		}
		if (year > n.getValue().getYear()) { // Go right if node is lower than year
			if (n.getRight() != null) {
			temp = n.getRight();
			}else return null;
		}
		return searchYear(temp,year); // Recurse in direction determined from above 2 if statements
	}
	
	public Node removePersonByName(Node n,String name) { // Removes person by given name
		if (n == null) { // returns null if name not found
			System.out.println("Name not found");
			return null;
		}
		
		if (name.compareTo(n.getValue().getName()) > 0) { // if name is higher than node, name move right
			n.setRight(removePersonByName(n.getRight(),name));
		}
		else if(name.compareTo(n.getValue().getName()) < 0) { // if name is lower than node name, move left
			n.setLeft(removePersonByName(n.getLeft(),name));
		}
		else {
			if (n.getLeft() == null && n.getRight() == null) { // if there is no children, set node to null
				n = null;
			}
			else if(n.getRight() != null) {
				n.setValue(rightMost(n)); // sets node to as far right as it can go
				n.setRight(removePersonByName(n.getRight(),n.getValue().getName())); // recurses 
			}
			else {
				n.setValue(leftMost(n)); // sets node to as far left as it can go
				n.setLeft(removePersonByName(n.getLeft(),n.getValue().getName())); // recurses
			}
		}
		return n;
	}
	
	public Node editPersonByName(Node n, String name, String newName, int num, int sel) { // Edits person by name, month, day, or year
		if (n == null) {
			System.out.println("Name not found");
			return null;
		}
		
		if (name.compareTo(n.getValue().getName()) > 0) { // if name is higher than node, name move right
			n.setRight(editPersonByName(n.getRight(),name, newName,num,sel));
		}
		else if(name.compareTo(n.getValue().getName()) < 0) { // if name is lower than node name, move left
			n.setLeft(editPersonByName(n.getLeft(),name,newName,num,sel));
		}
		else {
			
			switch (sel) {
			case 1:
				n.getValue().setName(newName); // sets new name
				break;
			case 2:
				n.getValue().setMonth(num); // sets new month
				break;
			case 3:
				n.getValue().setDay(num); // sets new day
				break;
			case 4:
				n.getValue().setYear(num); // sets new year
			}
			
		}
		return n; // returns node with updated person
		
		
	}
	
	private Person rightMost(Node n){ // moves as far right as possible from current node
        n = n.getRight();
        while(n.getLeft() != null){
            n = n.getLeft();
        }
        return n.getValue(); // returns right most node
    }
	
	
	private Person leftMost(Node n){ // moves as far left as possible from current node
        n = n.getLeft();
        while(n.getRight() != null){
           n = n.getRight();
        }
        return n.getValue(); // returns left most node
    }

	
	public void writeToFile(Node n) { // Writes to file from personListString
		listInOrderString(n);
		try {
			FileWriter myWriter = new FileWriter("ADT.txt");		
			myWriter.write(personListString); // writes string of people to file
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
	        e.printStackTrace();
		}
				
			
	}
	public void listInOrderString(Node n) { // Takes node and puts it in order into personListString for writing
		if (n != null) {
			listInOrderString(n.getLeft());
			personListString += n.getValue().toString() + "\n"; // adds person to string
            listInOrderString(n.getRight());
        }
	}
	
}
