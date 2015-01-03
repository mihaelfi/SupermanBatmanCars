package spl.ass3;


import java.util.ArrayList;


public class Asset implements Comparable<Asset> {
	
	protected String name;
	// one of the types provided in the xml.
	protected String type;
	protected Location location;
	// List of Content of the asset
	protected ArrayList<AssetContent> assetContents;
	// One of 3 different options, 1 = available, 2= booked , 3 = occupied , 4 = unavailable.
	protected String status;
	protected double costPerNight;
	protected int size;
	
	
	
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
		
		return "The Asset: " + this.name + "\nIs of type: " + this.type+".\nLocated in the location " + this.location+ 
				".\nIt's Status is: " + this.status + "\nIt's cost Per night is: " + this.costPerNight + "\nit's size is : " + this.size + 
				"\nIt's asset contents are :" + this.assetContents.toString();
	}


	@Override
	public int compareTo(Asset o) {
	  int ans = 0;
	  if (this.size < o.size){
		  ans = -1;
	  }else if (this.size > o.size){
		  ans = 1;
	  }
	  
		return 0;
	}
	
	

}


	
