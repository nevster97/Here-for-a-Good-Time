package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 5
 *  returns their name and a
 *  modified string
 *
 *  @author Yasmin Martins
 *  @version 1.1
 */
public class Person5 {
  /** Holds the persons real name */
	private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	public Person5(String pname) {
  		name = pname;
	}
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		char[] arr = input.toCharArray();
		char first = arr[0];
		char second = arr[1];
		for (int i = 0; i < arr.length - 2; i++) {
			arr[i] = arr[i + 2];
		}
		arr[arr.length - 2] = first;
		arr[arr.length - 1] = second;
		String updatedString = new String(arr);
		return null;
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

	public static void main(String[] args) {
		Person5 yasmin = new Person5("yasmin");
		yasmin.toString();
	}

}
