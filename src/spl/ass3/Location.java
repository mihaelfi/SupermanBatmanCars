/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.awt.geom.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
public class Location {
	
	/** The x location. */
	private double xLocation;
	
	/** The y location. */
	private double yLocation;
	
	
	/**
	 * Instantiates a new location.
	 *
	 * @param xLocation
	 *            the x location
	 * @param yLocation
	 *            the y location
	 */
	public Location(double xLocation, double yLocation){
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
	/**
	 * Returns the distance between 'this' and another point.
	 * @param other point in 2d Space
	 * @return Distance from 'other' to 'this'
	 */
	public double calculateDistance(Location other){
		
		return Math.sqrt((Math.pow((other.xLocation - this.xLocation), 2) + Math.pow((other.yLocation - this.yLocation), 2))); 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String ans = this.xLocation + "," + this.yLocation;
		return ans;
	}

}
