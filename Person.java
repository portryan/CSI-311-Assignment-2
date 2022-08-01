/*
 * Person class meant to define person
 * @author Ryan Porter
 * @version 1.0 
 */

public class Person {
	private String name;
	private int month;
	private int day;
	private int year;
	
	public Person() {
		name = "";
		month = 00;
		day = 00;
		year = 0000;
	}
	
	public Person(String n, int m, int d, int y) {
		name = n;
		month = m;
		day = d;
		year = y;
	}
	
	public String getName() { // returns name
		return name;
	}
	
	public int getMonth() { // returns month
		return month;
	}
	
	public int getDay() { // returns day
		return day;
	}
	
	public int getYear() { // returns year
		return year;
	}
	
	public void setName(String n) { // sets name
		name = n;
	}
	
	public void setMonth(int m) { // sets month
		month = m;
	}
	
	public void setDay(int d) { // sets day
		day = d;
	}
	
	public void setYear(int y) { // sets year
		year = y;
	}
	
	@Override
	public String toString() { // returns string format, '|' is used to separate data
		return name + "|" + String.format("%02d", month) + "|" + String.format("%02d", day) + "|" + year;
	}
}
