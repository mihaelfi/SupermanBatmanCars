package spl.ass3;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RepairMaterialInformation.
 */
public class RepairMaterialInformation {

	protected String nameOfItemToBeRepaired;
	protected ArrayList<RepairMaterial> materialsNeededForRepair;
	
	
	public RepairMaterialInformation(String assetContentToRepair) {
		this.nameOfItemToBeRepaired = assetContentToRepair;
		this.materialsNeededForRepair = new ArrayList<RepairMaterial>();
		}
	
	public void addRepairMaterialInformation(RepairMaterial repairMaterialToAdd){
		this.materialsNeededForRepair.add(repairMaterialToAdd);
	
	}
	
	public String toString(){
		String ans = "Repair Material Information for Asset Content: " + this.nameOfItemToBeRepaired + "\n";
		
		for (int i = 0 ; i < this.materialsNeededForRepair.size() ; i++){
			ans = ans + this.materialsNeededForRepair.get(i).toString();
		}
		
		
		return ans;
		
	}
	
	
}
