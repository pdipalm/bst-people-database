import java.io.*;
import java.util.ArrayList;
import java.time.*;

public class PeopleDatabase {
	private Bstree<Person> bst;
	/*
	 * constructs an empty DB
	 */
	PeopleDatabase(){
		bst = new Bstree<Person>();
	}
	/*
	 * constructs db with people from FileReader linked to file, file must be of the style name|DOB in each line (see test.txt)
	 * 
	 * @param filereader linked to file with people in it
	 */
	PeopleDatabase(FileReader inputFile) throws IOException{
		bst = new Bstree<Person>();
		BufferedReader reader = new BufferedReader(inputFile);
		String line = reader.readLine();
		while(line != null) {
			String[] personData = new String[2];
			personData = line.split("\\|");
			this.add(new Person(personData[0], LocalDate.parse(personData[1])));
			line = reader.readLine();
		}
		reader.close();
	}
	/*
	 * adds a person to the db
	 * 
	 * @param person to be inserted
	 */
	public void add(Person p){
		bst.insert(p);
	}
	/*
	 * removes a person from the db
	 * 
	 * @param person to be removed
	 */
	public void remove(Person p) {
		bst.delete(p);
	}
	/*
	 * changes a person's name
	 * 
	 * @param String name to change, String new name
	 */
	public void modifyName(String name, String newName) {
		Person p = searchByName(name);
		bst.delete(p);
		bst.insert(new Person(newName, p.getDOB()));
	}
	
	/*
	 * modifies a person's DOB
	 * 
	 * @param String name to change, LocalDate new DOB
	 */
	public void modifyDOB(String name, LocalDate newDOB) {
		Person p = searchByName(name);
		if(p == null) {
			System.out.println(name + " not found");
			return;
		}
		bst.search(p).getData().setDOB(newDOB);
	}
	/*
	 * lists all people in the database
	 * 
	 * @return an arraylist of all the people
	 */
	public ArrayList<Person> listAll() {
		return bst.getList();
	}
	/*
	 * checks if a name exists in the DB
	 * 
	 * @param name to search for
	 * @return a boolean indicating if the person exists in the DB
	 */
	public boolean exists(String name) {
		if(this.searchByName(name) != null)
			return true;
		
		return false;
	}
	/*
	 * returns a person object by name
	 * 
	 * @param name to find
	 * @return the Person object corresponding to the name
	 */
	public Person searchByName(String name) {
		ArrayList<Person> db = bst.getList();
		
		int leftIndex = 0;
		int rightIndex = db.size();
		
		while (leftIndex <= rightIndex) {
			int midIndex = (leftIndex+rightIndex)/2;
			
			if(name.equals(db.get(midIndex).getName())) {
				return db.get(midIndex);
			}else if(name.compareTo(db.get(midIndex).getName()) < 0) {
				rightIndex = midIndex-1;
			}else {
				leftIndex = midIndex+1;
			}
		}
		return null;
	}
	/*
	 * returns an arraylist of all people born on a given day
	 * 
	 * @param day to search between 1-31
	 * @return an arraylist of the people born on that day
	 */
	public ArrayList<Person> searchByDay(int day){
		ArrayList<Person> all = bst.getList();
		ArrayList<Person> output = new ArrayList<Person>();
		for(int i = 0; i < all.size(); ++i) {
			if(all.get(i).getDOB().getDayOfMonth() == day) 
				output.add(all.get(i));	
		}
		return output;
		
	}
	/*
	 * returns an arraylist of all people born in a given month
	 * 
	 * @param month to search between 1-12
	 * @return an arraylist of the people born in the month
	 */
	public ArrayList<Person> searchByMonth(int month){
		ArrayList<Person> all = bst.getList();
		ArrayList<Person> output = new ArrayList<Person>();
		for(int i = 0; i < all.size(); ++i) {
			if(all.get(i).getDOB().getMonthValue() == month) 
				output.add(all.get(i));	
		}
		return output;
	}
	/*
	 * returns an arraylist of all people born in a given year
	 * 
	 * @param year to search between 0001-2021
	 * @return an arraylist of the people born in the year
	 */
	public ArrayList<Person> searchByYear(int year){
		ArrayList<Person> all = bst.getList();
		ArrayList<Person> output = new ArrayList<Person>();
		for(int i = 0; i < all.size(); ++i) {
			if(all.get(i).getDOB().getYear() == year) 
				output.add(all.get(i));	
		}
		return output;
	}
	
}