package edu.gatech.oad.antlab.person;
import java.util.ArrayList;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string
 *
 * @author Kory Brantley
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		ArrayList<Character> al = new ArrayList<Character>();
		for (int i = 0; i < input.length(); i++) {
			al.add(input.charAt(i));
		}
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < input.length(); i++) {
			 int gen = rand.nextInt(input.length() - 1);


             int gen1 = rand.nextInt(input.length() - 1);
			 // char a = input.charAt(i);
             char a = al.get(gen1);



			 char b = al.get(gen);
			 al.set(gen1, b);
			 al.set(gen, a);
		}
		String newName = "";
		for (int i = 0; i < input.length(); i++) {
			newName = newName + al.get(i);
		}
		return newName;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
