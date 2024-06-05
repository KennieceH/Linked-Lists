package project3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/** 
 * COP 3530: Project 3 – Linked Lists  
 * <p> 
 * This program prints out country objects using stacks and priority queues (both created using Linked Lists) 
 * with options to either delete country objects within a certain interval based on death rates or print the 
 * country objects from the priority queue.
 * <p> 
 * 
 * <p>
 * This program first takes in a csv file of countries and then asks the user to input a number
 * 1-3: (1) delete country objects within a certain interval, (2) print the priority queue, and (3) quit the program.
 * <p>
 * @author <Kenniece Harris> 
 * @version <10/28/2022> 
 */ 
public class Project3_N01503765 {

	public static Country[] countryArray = new Country[145];
	public static Country[] deathRate = new Country[94];
	public static Stack countryStack = new Stack();
	public static PriorityQ countryPQ = new PriorityQ();
	
	public static void main(String[] args) {
		
		System.out.println("Project 3: Linked Lists");
		getUserInput();
		groupDeathRate();
		printUserInput();
		
	}
	
/** 
* The purpose of this method is to get the user input containing the name of the csv file
* which is then taken in as a parameter for the parseCsvFile method.
* 
 */ 
		
	 public static void getUserInput() {
		 
			System.out.print("Please enter the name of the csv file:");
		    Scanner scnr = new Scanner(System.in);
		    String csvFileName = scnr.nextLine();
		    System.out.println("");
		    parseCsvFile(csvFileName);
		    
	 }
		
/** 
* The purpose of this method is to read in the countries from the csv file into country
* objects which are then pushed/inserted into a stack/priority queue.
* 
* @param fileName, the name of the csv file.
 */
	public static void parseCsvFile(String fileName) {
		
		FileReader newFile;
	      Scanner scnr2;
			try {
				newFile = new FileReader(fileName);
				 scnr2 = new Scanner(newFile);
				
				int count = 0;
				while(scnr2.hasNext())
				{
					  String word = scnr2.nextLine();
					  if(count > 0)
					  {
						  
						  String[] newLine = word.split(",");
						  countryArray[count - 1] = new Country(newLine[0],newLine[1],newLine[2],newLine[3], newLine[4], newLine[5], newLine[6]);
						  
						  
						  
					  }
	
					count++;
				}
				
				
				System.out.println("There were 145 records read.");
		        System.out.println();
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("Try Again. Please enter a valid csv file name.");
			}
	}

/** 
* The purpose of this method is to get the user input for options 1-6 and print the result or
* quit the program
 */
	public static void printUserInput() {
			
			
			
		System.out.println("Please make a selection:");
		System.out.println();
	    System.out.println("1. Enter a DR interval for deletions on priority queue  ");
	    System.out.println("2. Print the priority queue  ");
	    System.out.println("3. Exit program ");
	    System.out.println("");
	    System.out.print("Enter your choice: ");
		    
		    Scanner input = new Scanner(System.in);
			String userInput = input.next();
			boolean exit = false;
			System.out.println("");
			 
			while(!exit)
		      {
			    	
			    	if(userInput.equals("quit") || userInput.equals("3"))
			    	{
			    		 exit = true;
			    		 input.close();
			    		 break;
			    	}
			    	
			    	
			    	
			    	
			    	else if(userInput.matches("[a-zA-Z_]+") || !userInput.matches("[1-3]")) {
			    		

				    	while(userInput.matches("[a-zA-Z_]+") || !userInput.matches("[1-3]")) {
				    		System.out.print("Invalid choice! Enter 1-3:");
				    		
				    		userInput = input.next();
				    		
				    	}
				    	
				    	System.out.println("");
			    		
			    	}
			    	
			    	
			    	
			    	
			    	else {
					    
					    int i = Integer.parseInt(userInput); 
					    
					    
					    
					   
					    switch(i) {
					    
					      
					      case 1:
					    	  
					    	  int n1 = 0;
					    	  int n2 = 0;
					    	  System.out.print("Enter DR interval like [x,y]:");
					    	  
					    	  Scanner scnr = new Scanner(System.in);
					    	   scnr.useDelimiter("\\W");
					    	   String num1 = scnr.next();
					    	   String num2 = scnr.next();
					    	   
					    	  if(num1.matches("[a-zA-Z_]+") || num2.matches("[a-zA-Z_]+")) {
					    		  while(num1.matches("[a-zA-Z_]+") || num2.matches("[a-zA-Z_]+")) {
					    			  System.out.print("Invalid choice! enter numbers [x,y]:");
					    			   
					    			    num1 = scnr.next();
							    	    num2 = scnr.next();
					    		  }
					    	   }
					    	  
					    	  n1 = Integer.parseInt(num1);
					    	  n2 = Integer.parseInt(num2);
							
					    	  System.out.println();
					    	   printOptionOne(n1, n2); 
					    	
					    	break;
					      case 2: printOptionTwo();  break;
					      case 3: exit = true; break; 
					      
					    }
					    
					    System.out.println("Please make a selection:");
					    System.out.println();
					    System.out.println("1. Enter a DR interval for deletions on priority queue  ");
					    System.out.println("2. Print the priority queue  ");
					    System.out.println("3. Exit program ");
					    System.out.println("");
					    System.out.print("Enter your choice: ");
					    
					 
					    userInput = input.next();
					    System.out.println("");
					    }
			    	
		      }
		}
	
/** 
* The purpose of this method is to print the first option of the program, deleting country objects
* based on death rate using an interval from user input.
* 
* @param num1, the beginning value of the interval
* @param num2, the ending value of the interval
 */
	public static void printOptionOne(int num1, int num2) {
		
		countryPQ.intervalDelete(num1, num2);
		System.out.printf("Countries of priority queue with DRs in [%d,%d] are deleted\n", num1,num2);
		System.out.println();
	}
	
/** 
* The purpose of this method is to print option two of the program, the countries contained
* in the priority queue.
 */
	public static void printOptionTwo() {
		System.out.printf("%-10s %15s %10s %10s %15s %15s %15s", "Name",   "Capitol",   "GDPPC",   "CFR",   "CaseRate",   "DeathRate",   "PopDensity\n");
		System.out.println("------------------------------------------------------------------------------------------------");
		countryPQ.printPriorityQ(countryPQ.last);
		System.out.println();
		System.out.println();
	}
	
/** 
* The purpose of this method is group the countries based on death rate (VGOOD,GOOD, and FAIR)
* and insert them into the stack. 
 */
	public static void groupDeathRate() {
		
		
         int num = countryArray.length;
		
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
				for(int inner = outer + 1; inner < num; inner++) {
					
					if(countryArray[inner].getDeathRate() < countryArray[lowest].getDeathRate()){
					lowest = inner;
					}
				}
				
				if(lowest != outer) {
				Country current = countryArray[lowest];
				countryArray[lowest] = countryArray[outer];
				countryArray[outer] = current;
				}
				
				
			}
		int count = 0;
		for(int i = countryArray.length - 1; i >= 0; i--) {
			if(countryArray[i].getDeathRate() >= 20 && countryArray[i].getDeathRate() < 350 ) {
			 deathRate[count] = countryArray[i];
			 
			 countryStack.push(deathRate[count]);
			count++;
			}
		}
		
		popToPriorityQ();
	}
	
/** 
* The purpose of this method is to pop each country object from the stack and insert them
* into the priority queue.
 */
	public static void popToPriorityQ() {
		
		for(int i = 0; i < deathRate.length; i++) {
			
			countryPQ.insert(countryStack.pop());
		}
		
	}
}
