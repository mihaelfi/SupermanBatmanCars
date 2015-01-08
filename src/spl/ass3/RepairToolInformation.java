/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.util.ArrayList;
import java.util.Collections;



// TODO: Auto-generated Javadoc
/**
 * The Class RepairToolInformation.
 */
public class RepairToolInformation {
	
	/** The name. */
	private String nameOfItemToBeRepaired;
	private ArrayList<RepairTool> toolsNeededForRepair;
	
	
	public RepairToolInformation(String assetContentToRepair) {
		this.nameOfItemToBeRepaired = assetContentToRepair;
		this.toolsNeededForRepair = new ArrayList<RepairTool>();
		}
	
	public void addRepairToolInformation(RepairTool repairToolToAdd){
		this.toolsNeededForRepair.add(repairToolToAdd);
	}
	
	public String toString(){
		String ans = "Repair Tool Information for Asset Content: " + this.nameOfItemToBeRepaired + "\n";
		
		for (int i = 0 ; i < this.toolsNeededForRepair.size() ; i++){
			ans = ans + this.toolsNeededForRepair.get(i).toString();
		}
		
		
		return ans;
		
	}

	public String getNameOfItemToBeRepaired() {
		return nameOfItemToBeRepaired;
	}

	public ArrayList<RepairTool> getToolsNeededForRepair() {
		return toolsNeededForRepair;
	}
	
	public void sortRepairTools(){
		Collections.sort(this.toolsNeededForRepair);
	}
	
	
	
	
}
