package spl.ass3;

import java.awt.geom.Point2D;

public class Location {
	
	private double xLocation;
	private double yLocation;
	
	
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
	
	public String toString(){
		String ans = this.xLocation + "," + this.yLocation;
		return ans;
	}

}
