//Bansri Shah
//110335850

import java.io.*;

/**
 * This class creates a storm object which consists of name, precipitation, windspeed, and date
 * @author Bansri
 *
 */
public class Storm implements Serializable{
	private String name;
	private double precipitation;
	private double windspeed;
	String date;
	
	/**
	 * This constructor creates the storm object
	 * @param name
	 * name of storm
	 * @param precipitation
	 * precipitation rate
	 * @param windspeed
	 * windspeed rate
	 * @param date
	 * date of storm
	 */
	public Storm(String name, double precipitation, double windspeed, String date) {
		this.name = name;
		this.precipitation = precipitation;
		this.windspeed = windspeed;
		this.date = date;
	}
	
	/**
	 * getter for name
	 * @return
	 * returns name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setter for name
	 * @param name
	 * sets given name to variable
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for precipitation
	 * @return
	 * returns precipitation
	 */
	public double getPrecipitation() {
		return this.precipitation;
	}
	
	/**
	 * setter for precipitation
	 * @param precipitation
	 * sets given precipitation to variable
	 */
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	
	/**
	 * getter for windspeed
	 * @return
	 * returns windspeed
	 */
	public double getWindspeed() {
		return this.windspeed;
	}
	
	/**
	 * setter for windspeed
	 * @param windspeed
	 * sets given windspeed to variable
	 */
	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}
	
	/**
	 * getter for date
	 * @return
	 * returns date
	 */
	public String getDate() {
		return this.date;
	}
	
	/**
	 * setter for date
	 * @param date
	 * sets given date to variable
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return getName() + String.format("%45s", getWindspeed()) + String.format("%20s", getPrecipitation());
	}
}
