package BD;


public class Person {
	int birthday;

	public Person(int birthday) {
		this.birthday = birthday;
		
	}
	/**
	 * Accessor: returns int value of birthday of Person object. 
	 * the int represents the number of days of the year.
	 * 
	 * @return int birthday of the Person object
	 */
	public int getBirthday() {
		
		return birthday;
	}
	
}
