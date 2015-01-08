package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Statistics {
	
	private Double moneyGained = new Double(0);
	private ArrayList<RentalRequest> handeledrentalRequestCollection;
	private HashMap  <String,RepairTool> repairToolUsedCollection;
	private HashMap  <String,RepairMaterial> repairMaterialUsedCollection;
	private StringBuilder stringbuilder = new StringBuilder();
	
	
	
	
	
	
	
	public Statistics(Double moneyGained,
			ArrayList<RentalRequest> handeledrentalRequestCollection,
			HashMap<String, RepairTool> repairToolUsedCollection,
			HashMap<String, RepairMaterial> repairMaterialUsedCollection) {
		
		this.moneyGained = moneyGained;
		this.handeledrentalRequestCollection = handeledrentalRequestCollection;
		this.repairToolUsedCollection = repairToolUsedCollection;
		this.repairMaterialUsedCollection = repairMaterialUsedCollection;
	}


//	public Statistics(Double moneyGained,) {
//		
//		
//		this.handeledrentalRequestCollection = new ArrayList<RentalRequest>();
//		this.repairToolUsedCollection = new HashMap<String,RepairTool>();
//		this.repairMaterialUsedCollection = new HashMap<String,RepairMaterial>();
//		this.moneyGained = moneyGained;
//		
//		
//	}


	public HashMap<String, RepairTool> getRepairToolUsedCollection() {
		return repairToolUsedCollection;
	}


	public void setRepairToolUsedCollection(
			HashMap<String, RepairTool> repairToolUsedCollection) {
		this.repairToolUsedCollection = repairToolUsedCollection;
	}


	public HashMap<String, RepairMaterial> getRepairMaterialUsedCollection() {
		return repairMaterialUsedCollection;
	}


	public void setRepairMaterialUsedCollection(
			HashMap<String, RepairMaterial> repairMaterialUsedCollection) {
		this.repairMaterialUsedCollection = repairMaterialUsedCollection;
	}


	public ArrayList<RentalRequest> getRentalRequestCollection() {
		return handeledrentalRequestCollection;
	}


	public void setRentalRequestCollection(
			ArrayList<RentalRequest> rentalRequestCollection) {
		this.handeledrentalRequestCollection = rentalRequestCollection;
	}
	
	private String repairToolUsedCollectionToString(){
		String ans = "";
		Iterator it =this.repairToolUsedCollection.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n TOOL: " + pairs.getKey() + " Quantity: " + ((RepairTool)pairs.getValue()).getNumberOfTools();
		}
		return ans;
	}
	
	private String repairMaterialUsedCollectionToString(){
		String ans = "";
		Iterator it =this.repairMaterialUsedCollection.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n Material: " + pairs.getKey() + " Quantity: " + ((RepairTool)pairs.getValue()).getNumberOfTools();
		}
		return ans;
	}
	
	public void addMoney(Double moneyToAdd){
		this.moneyGained = this.moneyGained + moneyToAdd;
	}
	
	
	public String toString(){
		
		stringbuilder.append("\nThe Statistics for the simulation Are:\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		
		stringbuilder.append("Money gained during simulation is: " + this.moneyGained + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("Tools used in the Simulation are :\n" + this.repairToolUsedCollectionToString() + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("Materials used in the Simulation are :\n" + this.repairToolUsedCollectionToString() + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("All Handeled Requests Are :\n" + this.handeledrentalRequestCollection.toString() + "\n");
		
		
		return stringbuilder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	

}
