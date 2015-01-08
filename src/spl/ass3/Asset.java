/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;


import java.util.ArrayList;
import java.util.Collections;



/**
 * The Class Asset.
 */
public class Asset implements Comparable<Asset> {
	


	/** The name. */
	private String name;
	// one of the types provided in the xml.
	/** The type. */
	private String type;
	
	/** The location. */
	private Location location;
	// List of Content of the asset
	/** The asset contents. */
	private ArrayList<AssetContent> assetContents;
	// One of 3 different options, 1 = available, 2= booked , 3 = occupied , 4 = unavailable.
	/** The status. */
	private String status;
	
	/** The cost per night. */
	private double costPerNight;
	
	/** The size. */
	private int size;
	
	/** The status available. */
	private final String STATUS_AVAILABLE 			=   "AVAILABLE";
	
	/** The status booked. */
	private final String STATUS_BOOKED 				=   "BOOKED";
	
	/** The status occupied. */
	private final String STATUS_OCCUPIED 			=   "OCCUPIED";
	
	/** The status unavailable. */
	private final String STATUS_UNAVAILABLE 		=   "UNAVAILABLE";
	
	/** The is broken. */
	private boolean isBroken = false;
	
	
	
	
	/**
	 * Instantiates a new asset.
	 *
	 * @param name Asset name
	 * @param type Asset type
	 * @param location Asset location
	 * @param assetContents  asset contents
	 * @param status Asset status ( available,  booked, occupied ,  unavailable.)
	 * @param costPerNight the cost per night
	 * @param size Asset size
	 */
	public Asset(String name, String type, Location location,ArrayList<AssetContent> assetContents, String status,double costPerNight, int size) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.assetContents = assetContents;
		this.status = status;
		this.costPerNight = costPerNight;
		this.size = size;
	}
	
	

	public String toString(){
		
		return "\nThe Asset: " + this.name + "\nIs of type: " + this.type+".\nLocated in the location " + this.location+ 
				".\nIt's Status is: " + this.status + "\nIt's cost Per night is: " + this.costPerNight + "\nit's size is : " + this.size + 
				"\nIt's asset contents are :" + this.assetContents.toString();
	}

	
	
	/**
	 * Gets the status. 
	 *
	 * @return the status (Can be available,  booked, occupied ,  unavailable.)
	 */
	public String getStatus() {
		return status;
	}

	
	/**
 * Sets the status available.
 */
public void setStatusAvailable(){
		this.status = this.STATUS_AVAILABLE;
	}
	
	/**
	 * Sets the status booked.
	 */
	public void setStatusBooked(){
		this.status = this.STATUS_BOOKED;
	}
	
	/**
	 * Sets the status occupied.
	 */
	public void setStatusOccupied(){
		this.status = this.STATUS_OCCUPIED;
	}
	
	/**
	 * Sets the status unavailable.
	 */
	public void setStatusUnavailable(){
		this.status = this.STATUS_UNAVAILABLE;
	}
	
	


	/**
	 * Gets Asset name.
	 *
	 * @return Asset Name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets Asset Type.
	 *
	 * @return Asset Type
	 */
	public String getType() {
		return type;
	}


	/**
	 * Gets the location.
	 *
	 * @return Asset location
	 */
	public Location getLocation() {
		return location;
	}


	/**
	 * Gets the asset contents.
	 *
	 * @return the asset contents
	 */
	public ArrayList<AssetContent> getAssetContents() {
		return assetContents;
	}


	/**
	 * Gets the cost per night.
	 *
	 * @return the cost per night
	 */
	public double getCostPerNight() {
		return costPerNight;
	}


	/**
	 * Gets the size.
	 *
	 * @return Asset size
	 */
	public int getSize() {
		return size;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Asset o) {
	  int ans = 0;
	  if (this.size < o.size){
		  ans = -1;
	  }else if (this.size > o.size){
		  ans = 1;
	  }
	  
		return ans;
	}
	
	/**
	 * Sort asset contents.
	 */
	public void sortAssetContents(){
		Collections.sort(this.assetContents);
	}


	/**
	 * Sets the repaired flag to false
	 */
	public void setRepaired() {
		this.isBroken = false;
	}


	/**
	 * Sets the broken flag to true
	 */
	public void setBroken() {
		this.isBroken = true;
	}
	
	
	/**
	 * Checks if is broken.
	 *
	 * @return true, if is broken
	 */
	public boolean isBroken(){
		return this.isBroken;
	}
	
	
	
	

}


	
