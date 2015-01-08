/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;


/**
 * The Class ClerkDetails.
 */
public class ClerkDetails {
	
	/** Clerk name. */
	protected String name;
	
	/** Clerk location. */
	protected Location location;

	
	/**
	 * Instantiates a new clerk details.
	 *
	 * @param name
	 *            the name
	 * @param location
	 *            the location
	 */
	public ClerkDetails(String name , Location location) {
	this.name = name;
	this.location = location;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String ans = "Name: " + this.name + " Location: " + this.location.toString();
		return ans;
	}

	/**
	 * Gets Clerk name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets Clerk name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets Clerk location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets Clerk location.
	 *
	 * @param location
	 *            the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
