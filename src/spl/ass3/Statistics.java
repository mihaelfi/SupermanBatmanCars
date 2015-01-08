package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
	
	private Double moneyGained;
	private ArrayList<RentalRequest> rentalRequestCollection;
	private HashMap  <String,RepairTool> repairToolUsedCollection;
	private HashMap  <String,RepairMaterial> repairMaterialUsedCollection;
	private StringBuilder stringbuilder = new StringBuilder();
	
	
	public Statistics(Double moneyGained) {
		
		
		this.rentalRequestCollection = new ArrayList<RentalRequest>();
		this.repairToolUsedCollection = new HashMap<String,RepairTool>();
		this.repairMaterialUsedCollection = new HashMap<String,RepairMaterial>();
		
		
	}
	
	
	
	
	
	
	
	

}
