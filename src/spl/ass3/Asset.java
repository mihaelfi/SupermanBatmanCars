package spl.ass3;


import java.util.ArrayList;


public class Asset {
	
	protected String name;
	// one of the types provided in the xml.
	protected String type;
	protected Location location;
	// List of Content of the asset
	protected ArrayList<AssetContent> assetContent;
	// One of 3 different options, 1 = available, 2= booked , 3 = occupied , 4 = unavailable.
	protected int status;
	protected double costPerNight;
	protected int size;
	
	
	
	

}
