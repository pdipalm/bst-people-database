
/*
 * Class for holding data of a person (name & DOB)
 * @author Peter DiPalma
 * @version 1.0
 */
import java.time.LocalDate;

public class Person implements Comparable<Person> {
	private String name;
	private LocalDate birthday;

	/*
	 * constructs a person with required fields
	 * 
	 * @param String name, LocalDate DOB
	 */
	Person(String name, LocalDate dob) {
		this.name = name;
		this.birthday = dob;
	}

	/*
	 * returns the name of the person as String
	 * 
	 * @return a String of the person's name
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * returns a localdate of the person's date of birth
	 * 
	 * @return a LocalDate of the person's DOB
	 */
	public LocalDate getDOB() {
		return this.birthday;
	}

	/*
	 * modifies the persons DOB
	 * 
	 * @param LocalDate of the new DOB
	 */
	public void setDOB(LocalDate dob) {
		this.birthday = dob;
	}

	/*
	 * inherited/overwritten from comparable interface, uses string's compareTo
	 * method for lexicographically comparing strings
	 * 
	 * @param Person to be compared with this.person
	 * 
	 * @return integer representation of comparison
	 * example: x.compareTo(y) > 0 then y > x
	 * 			x.compareTo(y) < 0 then y < x
	 */
	@Override
	public int compareTo(Person p) {
		return (this.getName().compareTo(p.getName()));
	}
	
	/* 
	 * overwritten from object class, determines if these people are equal based on
	 * the data they contain.
	 * 
	 * @param an object to compare this TreeNode to
	 * 
	 * @return a boolean indicating if these nodes contain the same data
	 */

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Person)) {
			return false;
		}

		Person cast = (Person) o;

		return (this.getName().equals(cast.getName()) && this.getDOB().equals(cast.getDOB()));

	}
	/*
	 * Overridden toString method from Object class, in the format of: "Name: Peter DiPalma | DOB: 1999-03-10"
	 * 
	 * @return the string representation of the Person
	 */
	@Override
	public String toString() {
		return ("Name: " + this.getName() + " | DOB:" + this.getDOB() + "\n");
	}
}
