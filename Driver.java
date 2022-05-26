/*
 * Driver program for People Database
 * @author Peter DiPalma
 * @version 1.0
 */

//import for filereader
import java.io.FileReader;
//import used for handling dates
import java.time.LocalDate;
//import for dynamic array
import java.util.ArrayList;

public class Driver {
	/*
	 * main method creates filereader and passes to database using filereader
	 * constructor, file parsing etc is handled within constructor and proceeds to
	 * perform tests on database (list all, check for existence, search by
	 * name|DOB|day of month, add, remove)
	 */
	public static void main(String[] args) throws Exception {

		FileReader reader = new FileReader("test.txt");
		System.out.println("Creating Database from File...");
		// using filereader constructor
		PeopleDatabase db = new PeopleDatabase(reader);
		System.out.println("Database Created.");

		System.out.println("Listing all...");
		// formatprint makes arraylist print more nicely
		formatPrint(db.listAll());

		System.out.println();

		System.out.println("Does name \"Leonardo DiCaprio\" exist?");
		System.out.println(db.exists("Leonardo DiCaprio"));
		System.out.println("Performing a search for name: \"Leonardo DiCaprio\"");
		System.out.println(db.searchByName("Leonardo DiCaprio"));

		System.out.println("Does name \"Michael Jordan\" exist?");
		System.out.println(db.exists("Michael Jordan"));
		System.out.println("Performing a search for name: \"Michael Jordan\"");
		System.out.println(db.searchByName("Michael Jordan"));

		System.out.println();

		System.out.println("Attempting to change name of \"Johnny Test\" to \"Sponge Bob\"");
		db.modifyName("Johnny Test", "Sponge Bob");
		System.out.println("Name changed... Relisting all");
		formatPrint(db.listAll());
		System.out.println();
		System.out.println("Does name \"Johnny Test\" exist?");
		System.out.println(db.exists("Johnny Test"));
		System.out.println("Performing a search for name: \"Sponge Bob\"");
		System.out.println(db.searchByName("Sponge Bob"));

		System.out.println();

		System.out.println("Attempting to change DOB of Sponge Bob from " + db.searchByName("Sponge Bob").getDOB()
				+ " to 1986-07-14...");
		db.modifyDOB("Sponge Bob", LocalDate.parse("1986-07-14"));
		System.out.println("DOB changed... Re-searching for \"Sponge Bob\"...");
		System.out.println(db.searchByName("Sponge Bob"));
		
		
		System.out.println();

		System.out.println("Searching for all people born in year: 2000...");
		formatPrint(db.searchByYear(2000));

		System.out.println();

		System.out.println("Searching for all people born in month: 12 (December)...");
		formatPrint(db.searchByMonth(12));

		System.out.println();

		System.out.println("Searching for all people born on the 23rd of the month...");
		formatPrint(db.searchByDay(23));

		System.out.println();

		System.out.println("Attempting to remove name: \"LeBron James\"...");
		// because db.remove method takes person class as param, searchbyname must be
		// used first to get person, then remove can be executed
		db.remove(db.searchByName("LeBron James"));
		System.out.println("\"LeBron James\" removed... Listing all\n");
		formatPrint(db.listAll());

		System.out.println();

		System.out.println("Attempting to add a new person with name \"Gordon Ramsey\" and DOB \"1966-11-08\"...");
		db.add(new Person("Gordon Ramsey", LocalDate.parse("1966-11-08")));
		System.out.println("\"Gordon Ramsey\" added... Listing all");
		formatPrint(db.listAll());
	}
	
	/*
	 * method for printing arraylist in a neater fashon
	 * 
	 * @param al, arraylist to print
	 */
	static void formatPrint(ArrayList<Person> al) {
		for (int i = 0; i < al.size(); ++i) {
			System.out.print((i + 1) + ". " + al.get(i));
		}
	}
}
