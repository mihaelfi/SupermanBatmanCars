package spl.ass3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Warehouse.
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class Warehouse  {
	
	/** The repair tools. */
	HashMap<String, RepairTool> 		repairTools;
	
	/** The repair materials. */
	HashMap<String, RepairMaterial> 	repairMaterials;
	
	
	Warehouse(){
		this.repairTools = new HashMap<String, RepairTool>();
		this.repairMaterials = new HashMap<String, RepairMaterial>();
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#insertToolFromParsing(spl.ass3.RepairTool)
	 */

	public void insertToolFromParsing(RepairTool toolToInsert) {
		this.repairTools.put(toolToInsert.getToolName(), toolToInsert);
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#insertMaterialFromParsing(spl.ass3.RepairMaterial)
	 */

	public void insertMaterialFromParsing(RepairMaterial materialToInsert) {
		this.repairMaterials.put(materialToInsert.getMaterialName(), materialToInsert);
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairTool(java.lang.String)
	 */

	public int takeRepairTool(RepairTool toolToGet) {
		int ans = 0;
		String toolName = toolToGet.getToolName();
		RepairTool toolInWarehouse = this.repairTools.get(toolName); 
		synchronized (toolInWarehouse) {
				
			if (toolInWarehouse.getNumberOfTools() - toolToGet.getNumberOfTools() >= 0){
				toolInWarehouse.takeTools(toolToGet);
				ans = 1;
			}else{
				ans = -1;
			}
			
			Driver.LOGGER.info("Current Status of: " + toolInWarehouse.toString());
			Driver.LOGGER.info("Returned " + toolToGet.toString() +" Materials from warehouse.");
			Driver.LOGGER.info("Current Status of: " + toolInWarehouse.toString());
			
			
		}
		return ans;
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#returnRepairTool(java.lang.String)
	 */

	public void returnRepairTool(RepairTool repairToolToReturn) {
		String toolName = repairToolToReturn.getToolName();
		RepairTool toolInWarehouse = this.repairTools.get(toolName);
		synchronized (toolInWarehouse) {
			toolInWarehouse.returnTools(repairToolToReturn);
			
			Driver.LOGGER.info("Current Status of: " + toolInWarehouse.toString());
			Driver.LOGGER.info("Returned " + repairToolToReturn.toString() +" Materials from warehouse.");
			Driver.LOGGER.info("Current Status of: " + toolInWarehouse.toString());
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairMaterial(java.lang.String)
	 */

	public void takeRepairMaterial(RepairMaterial repairMaterialToTake) {
		String materialName = repairMaterialToTake.getMaterialName();
		RepairMaterial materialInWarehouse = this.repairMaterials.get(materialName);
		synchronized (materialInWarehouse) {
			materialInWarehouse.takeMaterial(repairMaterialToTake);
			Driver.LOGGER.info("Current Status of: " + materialInWarehouse.toString());
			Driver.LOGGER.info("Taken " + repairMaterialToTake.toString() +" Materials from warehouse.");
			Driver.LOGGER.info("Current Status of: " + materialInWarehouse.toString());
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getNumberOfToolInWarehouse(java.lang.String)
	 */

	public int getNumberOfToolInWarehouse(String toolName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getNumberOfMaterialInWarehouse(java.lang.String)
	 */

	public int getNumberOfMaterialInWarehouse(String materialName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public String warehouseToolstoString(){
		String ans = "Printing warehouse tools content: \n";
		Iterator it =this.repairTools.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n The number of " + pairs.getKey() + " in the warehouse is: " + ((RepairTool)pairs.getValue()).getNumberOfTools();
		}
		return ans;
	}
	
	public String warehouseMaterialsstoString(){
		String ans = "Printing warehouse material content: \n";
		Iterator it =this.repairMaterials.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n The number of " + pairs.getKey() + " in the warehouse is: " + ((RepairMaterial)pairs.getValue()).getNumberOfMaterials();
		}
		return ans;
	}
	
	public String toString(){
		String ans = "The contents of the Warehouse are:\n";
		ans = ans + this.warehouseToolstoString() + this.warehouseMaterialsstoString();
		return ans;
	}
	
}
	
