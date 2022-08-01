/*
 * Generic Node class
 * @author Ryan Porter
 * @version 1.0 
 */

public class Node{
	private Node right;
	private Node left;
	private Person person;
	
	public Node() {
		right = null;
		left = null;
		person = null;
	}
	
	public Node(Person p) {
		person = p;
	}
	
	public Node getRight() {
		return right;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Person getValue() {
		return person;
	}
	
	public void setRight(Node n) {
		right = n;
	}
	
	public void setLeft(Node n) {
		left = n;
	}
	
	public void setValue(Person p) {
		person = p;
	}
	
	public String toString() {
		return person.toString();
	}
}
