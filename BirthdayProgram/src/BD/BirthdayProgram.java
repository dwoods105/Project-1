package BD;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Daniel Woods
 * 
 * This is the main driver method for the birthday program. The single constructor
 * requires an int of the number of people to generate and it generates an array list of 
 * numPeople people and random birthdays for them. The constructor will calculate probability
 * of the chance that 2 people in a room of numPeople have the same birthday.
 * The constructor prints the information to console. The constructor is the only thing that
 * needs to be called to use this program.
 *
 */

public class BirthdayProgram {
//global variables
	private int numPeople;
	private ArrayList<Person> people;
	private ArrayList<Integer> matchingPeople;
	private double probability;
	
	
	/**
	 * Constructor takes an int of the number of people you'd like to generate.
	 * This method will call all of the methods it needs to to generate the 
	 * ArrayList of people with random birthdays, calculate the probability
	 * of the chance that 2 people in the number specified has the same birthday. 
	 * The program prints the toString which prints birthdays that appear twice and 
	 * the probability that 2 people in the group have the same birthday. 
	 * 
	 * @param int parNumPeople		The number of people you would like to Generate 
	 */
	public BirthdayProgram(int parNumPeople) {
		numPeople = parNumPeople;
		people = generatePeople(numPeople);
		probability = calculateProbability(people.size());
		matchingPeople = compareBirthdays(people);
		System.out.println(toString());
		
		
	}
	/**
	 * This method takes a int number of people and returns an arraylist of that number
	 * of people with randomly generated birthdays
	 * 
	 * @param numPeople 	int value of number of people to generate and put on ArrayList
	 * @return ArrayList of People objects with randomly generated birthdays
	 */
	private ArrayList<Person> generatePeople(int numPeople){
		ArrayList<Person> methodPeople= new ArrayList<Person>();
		Random rand = new Random();
		
		for(int i = 0; i < numPeople; i++) {
			
			int birthday = rand.nextInt(365) + 1;
			methodPeople.add(new Person(birthday));
			
		}
		return methodPeople;
		
	}
	/**
	 * calculates the probability that 2 people have the same birthday in a group of a specified
	 * number of people. 
	 * @param numPeople		Number of people in the group for the calculation
	 * @return	double that is the probability that 2 people have the same birthday in a group of numPeople
	 */
	
	private double calculateProbability(int numPeople){
		//separated the math out for readability 
		double base =.99726027397;
		double power = (((numPeople-1)*numPeople)/2);
		double prob = 1-Math.pow(base, power);
		return prob*100;
		
	}
	
	/**
	 * Iterates over the ArrayList of birthdays and records the first index of matching birthdays
	 * in an new ArrayList of integers, which it returns .
	 * 
	 * @param people ArrayList of person objects
	 * @return ArrayList of integers. The integers are an index of a birthday with a match
	 */
	
	private ArrayList<Integer> compareBirthdays(ArrayList<Person> people) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i=0; i<people.size(); i++) {
			for (int j = i+1; j<people.size();j++) {
				if (people.get(i).getBirthday() == people.get(j).getBirthday()) {
					indexes.add(i);
				}
			}
		}
		return indexes;
	}
	
	/**
	 * toString iterates over the data in birthday ArrayList and prints
	 * the month and day of a birthday if there is at least 1 match. 
	 * toString also prints the probability result of the calculateProbability
	 * method.
	 * 
	 * Format code referenced from : https://www.geeksforgeeks.org/localdate-format-method-in-java/
	 * 
	 * @override
	 * @return String for printing data about BirthdayProgram Object
	 */
	
	public String toString() {
		String bldStr = "Matching Birthdays\nMonth\tDay\n";
		
		for(int i = 0; i<matchingPeople.size();i++) {
			
			int birthday = people.get(matchingPeople.get(i)).getBirthday();
			
			Year year = Year.of(2022);
			LocalDate ld = year.atDay(birthday);
			
			DateTimeFormatter formatMonth = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd");
			
			String birthdayMonth = getMonth(Integer.parseInt(formatMonth.format(ld)));
	        bldStr += birthdayMonth + "\t " + formatDay.format(ld) + "\n";
		}
		bldStr += "\nProbability of 2 people in a room of  "+ numPeople+" having the same birthday: "+ probability+"%";
		return bldStr;
	}
	/**
	 * This method takes a month number and returns a String of the
	 * name of that month. 
	 * January is 1 and Decemeber
	 * Code referenced from : https://stackoverflow.com/questions/5799140/java-get-month-string-from-integer
	 * 
	 * @param Month 	number of month to convert to string
	 * @return String of name of month specified
	 */
	public String getMonth(int Month) {
		String birthdayMonth;
		switch (Month) {
        case 1:  birthdayMonth = "January";       break;
        case 2:  birthdayMonth = "February";      break;
        case 3:  birthdayMonth = "March";         break;
        case 4:  birthdayMonth = "April";         break;
        case 5:  birthdayMonth = "May";           break;
        case 6:  birthdayMonth = "June";          break;
        case 7:  birthdayMonth = "July";          break;
        case 8:  birthdayMonth = "August";        break;
        case 9:  birthdayMonth = "September";     break;
        case 10: birthdayMonth = "October";       break;
        case 11: birthdayMonth = "November";      break;
        case 12: birthdayMonth = "December";      break;
        default: birthdayMonth = "Invalid month"; break;
        
    }
		return birthdayMonth;	
	}
	
}
