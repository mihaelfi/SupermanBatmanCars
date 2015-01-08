package spl.ass3;


import java.util.ArrayList;
import java.util.Collections;


public class Asset implements Comparable<Asset> {
	


	private String name;
	// one of the types provided in the xml.
	private String type;
	private Location location;
	// List of Content of the asset
	private ArrayList<AssetContent> assetContents;
	// One of 3 different options, 1 = available, 2= booked , 3 = occupied , 4 = unavailable.
	private String status;
	private double costPerNight;
	private int size;
	private final String STATUS_AVAILABLE 			=   "AVAILABLE";
	private final String STATUS_BOOKED 				=   "BOOKED";
	private final String STATUS_OCCUPIED 			=   "OCCUPIED";
	private final String STATUS_UNAVAILABLE 		=   "UNAVAILABLE";
	private boolean isBroken = false;
	
	
	
	
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

	
	
	public String getStatus() {
		return status;
	}


//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	public void setStatusAvailable(){
		this.status = this.STATUS_AVAILABLE;
	}
	
	public void setStatusBooked(){
		this.status = this.STATUS_BOOKED;
	}
	
	public void setStatusOccupied(){
		this.status = this.STATUS_OCCUPIED;
	}
	
	public void setStatusUnavailable(){
		this.status = this.STATUS_UNAVAILABLE;
	}
	
	


	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}


	public Location getLocation() {
		return location;
	}


	public ArrayList<AssetContent> getAssetContents() {
		return assetContents;
	}


	public double getCostPerNight() {
		return costPerNight;
	}


	public int getSize() {
		return size;
	}


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
	
	public void sortAssetContents(){
		Collections.sort(this.assetContents);
	}


	public void setRepaired() {
		this.isBroken = false;
	}


	public void setBroken() {
		this.isBroken = true;
	}
	
	
	public boolean isBroken(){
		return this.isBroken;
	}
	
	
	
	

}


	
