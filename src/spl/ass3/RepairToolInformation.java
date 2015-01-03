package spl.ass3;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RepairToolInformation.
 */
public class RepairToolInformation {
	
	/** The name. */
	protected String nameOfItemToBeRepaired;
	protected ArrayList<RepairTool> toolsNeededForRepair;
	
	
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
	
	
}
