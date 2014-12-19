package spl.ass3;

import java.awt.geom.Point2D;

public class Location {
	
	private Point2D.Double currentLocation;
	
	
	public Location(Point2D.Double location){
		this.currentLocation = location;
	}
	
	/**
	 * Returns the distance between 'this' and another point.
	 * @param other point in 2d Space
	 * @return Distance from 'other' to 'this'
	 */
	public double calculateDistance(Point2D.Double other){
		
		return Math.sqrt((Math.pow((other.x - this.currentLocation.x), 2) + Math.pow((other.y - this.currentLocation.y), 2))); 
	}

}
