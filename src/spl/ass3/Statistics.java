/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Statistics.
 */
public class Statistics {
	
	/** The money gained. */
	private Double moneyGained ;
	
	/** The handeledrental request collection. */
	private ArrayList<RentalRequest> handeledrentalRequestCollection;
	
	/** The repair tool used collection. */
	private HashMap  <String,RepairTool> repairToolUsedCollection;
	
	/** The repair material used collection. */
	private HashMap  <String,RepairMaterial> repairMaterialUsedCollection;
	
	/** The stringbuilder. */
	private StringBuilder stringbuilder;
	

	
	/**
	 * Instantiates a new statistics.
	 */
	public Statistics() {
		
		this.moneyGained = new Double(0);
		this.handeledrentalRequestCollection = new ArrayList<RentalRequest>();
		this.repairToolUsedCollection = new HashMap  <String,RepairTool>();
		this.repairMaterialUsedCollection =  new HashMap  <String,RepairMaterial>();
		this.stringbuilder = new StringBuilder();;
	}


	/**
	 * Gets the repair tool used collection.
	 *
	 * @return the repair tool used collection
	 */
	public HashMap<String, RepairTool> getRepairToolUsedCollection() {
		return repairToolUsedCollection;
	}


	/**
	 * Sets the repair tool used collection.
	 *
	 * @param repairToolUsedCollection
	 *            the repair tool used collection
	 */
	public void setRepairToolUsedCollection(
			HashMap<String, RepairTool> repairToolUsedCollection) {
		this.repairToolUsedCollection = repairToolUsedCollection;
	}


	/**
	 * Gets the repair material used collection.
	 *
	 * @return the repair material used collection
	 */
	public HashMap<String, RepairMaterial> getRepairMaterialUsedCollection() {
		return repairMaterialUsedCollection;
	}


	/**
	 * Sets the repair material used collection.
	 *
	 * @param repairMaterialUsedCollection
	 *            the repair material used collection
	 */
	public void setRepairMaterialUsedCollection(
			HashMap<String, RepairMaterial> repairMaterialUsedCollection) {
		this.repairMaterialUsedCollection = repairMaterialUsedCollection;
	}


	/**
	 * Gets the rental request collection.
	 *
	 * @return the rental request collection
	 */
	public ArrayList<RentalRequest> getRentalRequestCollection() {
		return handeledrentalRequestCollection;
	}


	/**
	 * Sets the rental request collection.
	 *
	 * @param rentalRequestCollection
	 *            the new rental request collection
	 */
	public void setRentalRequestCollection(
			ArrayList<RentalRequest> rentalRequestCollection) {
		this.handeledrentalRequestCollection = rentalRequestCollection;
	}
	
	/**
	 * Repair tool used collection to string.
	 *
	 * @return the string
	 */
	private String repairToolUsedCollectionToString(){
		String ans = "";
		Iterator it =this.repairToolUsedCollection.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n TOOL: " + pairs.getKey() + " Quantity: " + ((RepairTool)pairs.getValue()).getNumberOfTools();
		}
		return ans;
	}
	
	
	
	/**
	 * Gets the money gained.
	 *
	 * @return the money gained
	 */
	public Double getMoneyGained() {
		return moneyGained;
	}


	/**
	 * Sets the money gained.
	 *
	 * @param moneyGained
	 *            the new money gained
	 */
	public void setMoneyGained(Double moneyGained) {
		this.moneyGained = moneyGained;
	}


	/**
	 * Gets the handeledrental request collection.
	 *
	 * @return the handeledrental request collection
	 */
	public ArrayList<RentalRequest> getHandeledrentalRequestCollection() {
		return handeledrentalRequestCollection;
	}


	/**
	 * Sets the handeledrental request collection.
	 *
	 * @param handeledrentalRequestCollection
	 *            the new handeledrental request collection
	 */
	public void setHandeledrentalRequestCollection(
			ArrayList<RentalRequest> handeledrentalRequestCollection) {
		this.handeledrentalRequestCollection = handeledrentalRequestCollection;
	}


	/**
	 * Repair material used collection to string.
	 *
	 * @return the string
	 */
	private String repairMaterialUsedCollectionToString(){
		String ans = "";
		Iterator it =this.repairMaterialUsedCollection.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n Material: " + pairs.getKey() + " Quantity: " + ((RepairMaterial)pairs.getValue()).getNumberOfMaterials();
		}
		return ans;
	}
	
	/**
	 * Adds the money.
	 *
	 * @param moneyToAdd
	 *            the money to add
	 */
	public void addMoney(Double moneyToAdd){
		this.moneyGained = this.moneyGained + moneyToAdd;
	}
	
	/**
	 * Adds the rental request.
	 *
	 * @param rentalRequestToAdd
	 *            the rental request to add
	 */
	public void addRentalRequest(RentalRequest rentalRequestToAdd){
		this.handeledrentalRequestCollection.add(rentalRequestToAdd);
	}
	
	
	/**
	 * Adds the tool torepair tools used collection.
	 *
	 * @param toolToAdd
	 *            the tool to add
	 */
	public void addToolTorepairToolsUsedCollection (RepairTool toolToAdd){
		if(this.repairToolUsedCollection.get(toolToAdd.getToolName()) == null){
			this.repairToolUsedCollection.put(toolToAdd.getToolName(), toolToAdd);
		}else{
			int oldNumberOfToolsUsed = this.repairToolUsedCollection.get(toolToAdd.getToolName()).getNumberOfTools();
			int newNumberOfToolsUsed = oldNumberOfToolsUsed + toolToAdd.getNumberOfTools();
			this.repairToolUsedCollection.get(toolToAdd.getToolName()).setNumberOfTools(newNumberOfToolsUsed);
			Driver.LOGGER.info("New Number Of Tools is: " + newNumberOfToolsUsed);
		}
	}
	
	
	/**
	 * Adds the material torepair material used collection.
	 *
	 * @param materialToAdd
	 *            the material to add
	 */
	public void addMaterialTorepairMaterialUsedCollection (RepairMaterial materialToAdd){
		if(this.repairMaterialUsedCollection.get(materialToAdd.getMaterialName()) == null){
			this.repairMaterialUsedCollection.put(materialToAdd.getMaterialName(), materialToAdd);
		}else{
			int oldNumberOfMaterialsUsed = this.repairMaterialUsedCollection.get(materialToAdd.getMaterialName()).getNumberOfMaterials();
			int newNumberOfMaterialsUsed = oldNumberOfMaterialsUsed + materialToAdd.getNumberOfMaterials();
			this.repairMaterialUsedCollection.get(materialToAdd.getMaterialName()).setNumberOfMaterials(newNumberOfMaterialsUsed);
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		stringbuilder.append("\nThe Statistics for the simulation Are:\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		
		stringbuilder.append("Money gained during simulation is: " + this.moneyGained + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("Tools used in the Simulation are :\n" + this.repairToolUsedCollectionToString() + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("Materials used in the Simulation are :\n" + this.repairMaterialUsedCollectionToString() + "\n");
		stringbuilder.append("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+\n");
		stringbuilder.append("All Handeled Requests Are :\n" + this.handeledrentalRequestCollection.toString() + "\n");
		
		
		return stringbuilder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	

}
