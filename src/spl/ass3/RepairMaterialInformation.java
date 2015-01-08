/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RepairMaterialInformation.
 */
public class RepairMaterialInformation {

	/** The name of item to be repaired. */
	protected String nameOfItemToBeRepaired;
	
	/** The materials needed for repair. */
	protected ArrayList<RepairMaterial> materialsNeededForRepair;
	
	
	/**
	 * Instantiates a new repair material information.
	 *
	 * @param assetContentToRepair
	 *            the asset content to repair
	 */
	public RepairMaterialInformation(String assetContentToRepair) {
		this.nameOfItemToBeRepaired = assetContentToRepair;
		this.materialsNeededForRepair = new ArrayList<RepairMaterial>();
		}
	
	/**
	 * Adds the repair material information.
	 *
	 * @param repairMaterialToAdd
	 *            the repair material to add
	 */
	public void addRepairMaterialInformation(RepairMaterial repairMaterialToAdd){
		this.materialsNeededForRepair.add(repairMaterialToAdd);
	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String ans = "Repair Material Information for Asset Content: " + this.nameOfItemToBeRepaired + "\n";
		
		for (int i = 0 ; i < this.materialsNeededForRepair.size() ; i++){
			ans = ans + this.materialsNeededForRepair.get(i).toString();
		}
		
		
		return ans;
		
	}

	/**
	 * Gets the name of item to be repaired.
	 *
	 * @return the name of item to be repaired
	 */
	public String getNameOfItemToBeRepaired() {
		return nameOfItemToBeRepaired;
	}

	/**
	 * Gets the materials needed for repair.
	 *
	 * @return the materials needed for repair
	 */
	public ArrayList<RepairMaterial> getMaterialsNeededForRepair() {
		return materialsNeededForRepair;
	}
	
	
	
	
}
