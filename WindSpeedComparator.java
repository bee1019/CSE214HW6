//Bansri Shah
//110335850

import java.util.*;

/**
 * Windspeed comparator, compares windspeed variables for 2 consecutive storm objects
 * @author Bansri
 *
 */
public class WindSpeedComparator implements Comparator<Storm>{
	
	/**
	 * Compare method which returns -1, 0, 1
	 */
	public int compare(Storm left, Storm right) 
	throws NullPointerException {
		if(left == null && right == null) {
			throw new NullPointerException("Cannot compare null objects.");
		}
		
		else {
		if(left.getWindspeed() < right.getWindspeed()) {
			return -1;
		}
		
		else if(left.getWindspeed() == right.getWindspeed()){
			return 0;
		}
		
		else {
			return 1;
		}
	}
	}
}
