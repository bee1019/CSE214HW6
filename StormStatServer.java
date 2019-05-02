//Bansri Shah
//110335850

import java.util.*;
import java.io.*;

/**
 * Driver class that creates a storm stat server and creates a hashmap to store storm objects
 * @author Bansri
 *
 */
public class StormStatServer {
	
	private HashMap<String, Storm> database = new HashMap<String, Storm>();
	
	public static void main (String [] args) {
		StormStatServer server = new StormStatServer();
		
		try {
			FileInputStream fileIn = new FileInputStream("hurricane.ser");
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			server.database = (HashMap<String, Storm>) inStream.readObject();
		}
		catch(FileNotFoundException e) {
			System.out.println("No previous data found.");
		}
		catch(IOException e) {
			System.out.println("File check error.");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Hashmap not found.");
		}
		
		String option = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!");
		
		boolean loop = true;
		while(loop) {
			try {
				System.out.println();
				System.out.println("Menu:");
				System.out.println("\tA) Add A Storm");
				System.out.println("\tL) Look Up A Storm");
				System.out.println("\tD) Delete A Storm");
				System.out.println("\tE) Edit Storm Data");
				System.out.println("\tR) Print Storms Sorted By Rainfall");
				System.out.println("\tW) Print Storms Sorted by Windspeed");
				System.out.println("\tX) Save and quit");
				System.out.println("\tQ) Quit and delete saved data");
				System.out.println();
				System.out.print("Please select an option: ");
				option = scan.next();
				option = option + scan.nextLine();
				option = option.toUpperCase();
				char opChar = option.charAt(0);
				if(option.length() != 1) {
					throw new IllegalArgumentException("Incorrect input.\n");
				}
				
				switch(opChar) {
				
				case 'A':
					System.out.print("Please enter name: ");
					String name = scan.next();
					name = name.toUpperCase();
					System.out.print("Please enter date (YYYY-MM-DD): ");
					String date = scan.next();
					
					if(date.matches("\\d{4}-\\d{2}-\\d{2}") == false) {
						System.out.print("Invalid date format.\n");
						break;
					}
					
					System.out.print("Please enter precipitation (cm): ");
					double precipitation = scan.nextDouble();
					System.out.print("Please enter windspeed (km/h): ");
					double windspeed = scan.nextDouble();
					
					Storm newStorm = new Storm(name, precipitation, windspeed, date);
					server.database.put(name, newStorm);
					
					System.out.print(name + " added.\n");
					break;
					
				case 'L':
					
					System.out.print("Please enter name: ");
					String key1 = scan.next();
					key1 = key1.toUpperCase();
					
					if(server.database.get(key1) == null) {
						System.out.print("Storm does not exist.\n");
						break;
					}
				
					System.out.println("\nStorm " + server.database.get(key1).getName() + 
					": Date " + server.database.get(key1).getDate() + ", " + 
					server.database.get(key1).getWindspeed() + " km/h winds, " + 
					server.database.get(key1).getPrecipitation() + " cm of rain\n");
					break;
					
				case 'D':
					
					System.out.print("Please enter a name: ");
					String key2 = scan.next();
					key2 = key2.toUpperCase();
					
					if(server.database.get(key2) == null) {
						System.out.print("Storm does not exist.\n");
						break;
					}
					
					server.database.remove(key2);
					System.out.print("Storm " + key2 + " has been deleted.\n");
					break;
					
				case 'E':
					
					System.out.print("Please enter name: ");
					String key3 = scan.next();
					key3 = key3.toUpperCase();
					
					if(server.database.get(key3) == null) {
						System.out.print("Storm does not exist.\n");
						break;
					}
					
					System.out.print("In Edit Mode, press enter without any input to leave data unchanged.\n");
					System.out.print("Please enter date (YYYY-MM-DD): ");
					String date2 = " ";
					date2 = scan.nextLine();
					scan.nextLine();
					
					if(date2.equals("")) {
						System.out.print("");
					}
					
					else if(date2.matches("\\d{4}-\\d{2}-\\d{2}") == false) {
						System.out.print("Invalid date format.\n");
						break;
					}
					
					else {
						server.database.get(key3).setDate(date2);
					}
					
					System.out.print("Please enter precipitation (cm): ");
					String precip = scan.nextLine();
					if(precip.equals("")) {
						System.out.print("");
					}
					
					else {
						double precipitation2 = Double.parseDouble(precip);
						server.database.get(key3).setPrecipitation(precipitation2);
					}
					
					System.out.print("Please enter windspeed (km/h): ");
					String wind = scan.nextLine();
					if(wind.equals("")) {
						System.out.print("");
					}
					
					else {
						double windspeed2 = Double.parseDouble(wind);
						server.database.get(key3).setWindspeed(windspeed2);
					}
					
					System.out.print(key3 + " added.\n");
					break;
					
				case 'R':
					
					ArrayList<Storm> rainList = new ArrayList<Storm>();
					for(String key : server.database.keySet()) {
						rainList.add(server.database.get(key));
					}
					
					Collections.sort(rainList, new PrecipitationComparator());
					
					System.out.println("Name\t\t\t\t\tWindspeed\t      Rainfall");
					System.out.println("----------------------------------------------------------------------------");
					
					for(int i = 0; i < rainList.size(); i++) {
						System.out.println(rainList.get(i).toString());
					}
					System.out.println("----------------------------------------------------------------------------");
					break;
					
				case 'W':
					
					ArrayList<Storm> windList = new ArrayList<Storm>();
					for(String key : server.database.keySet()) {
						windList.add(server.database.get(key));
					}
					
					Collections.sort(windList, new WindSpeedComparator());
					
					System.out.println("Name\t\t\t\t\tWindspeed\t      Rainfall");
					System.out.println("----------------------------------------------------------------------------");
					
					for(int i = 0; i < windList.size(); i++) {
						System.out.println(windList.get(i).toString());
					}
					System.out.println("----------------------------------------------------------------------------");
					break;
					
				case 'X':
					try {
						File newFile = new File("hurricane.ser");
						FileOutputStream fileOut = new FileOutputStream(newFile);
						ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
						outStream.writeObject(server.database);
						outStream.close();
						System.out.println("\nFile saved to hurricane.ser; feel free to use "
								+ "the weather channel in the meantime!");
						System.exit(opChar);
					}
					catch(IOException e) {
						System.err.println("The file could not be saved.");
					}
				
				case 'Q':
					File newFile = new File("hurricane.ser");
					newFile.delete();
					System.out.println("\nGoodbye, it's hard to hold an (electric) candle in the"
							+ "cold November (and April!) rain!");
					System.exit(opChar);
					
				default: 
					System.out.println("Invalid option. Please select an option listed above.");
					break;
				}
			}
			catch(NullPointerException e) {
				System.out.println(e.getMessage());
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
