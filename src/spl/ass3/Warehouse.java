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
	
	/** The repair tools. */
	HashMap<String, RepairTool> 		repairToolsUsed;
	
	/** The repair materials. */
	HashMap<String, RepairMaterial> 	repairMaterialsUsed;
	
	
	
	
	
	Warehouse(){
		this.repairTools = new HashMap<String, RepairTool>();
		this.repairMaterials = new HashMap<String, RepairMaterial>();
		this.repairToolsUsed = new HashMap<String, RepairTool>();
		this.repairMaterialsUsed = new HashMap<String, RepairMaterial>();
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
	
	private void addToolTorepairToolsUsed (RepairTool toolToAdd){
		if(this.repairToolsUsed.get(toolToAdd.getToolName()) == null){
			this.repairToolsUsed.put(toolToAdd.getToolName(), toolToAdd);
		}else{
			int oldNumberOfToolsUsed = this.repairToolsUsed.get(toolToAdd).getNumberOfTools();
			this.repairToolsUsed.get(toolToAdd).setNumberOfTools(oldNumberOfToolsUsed + toolToAdd.getNumberOfTools());;
			
		}
	}
	
	
	private void addMaterialTorepairMaterialUsed (RepairMaterial materialToAdd){
		if(this.repairMaterialsUsed.get(materialToAdd.getMaterialName()) == null){
			this.repairMaterialsUsed.put(materialToAdd.getMaterialName(), materialToAdd);
		}else{
			int oldNumberOfMaterialsUsed = this.repairToolsUsed.get(materialToAdd).getNumberOfTools();
			this.repairToolsUsed.get(materialToAdd).setNumberOfTools(oldNumberOfMaterialsUsed + materialToAdd.getNumberOfMaterials());;
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairTool(java.lang.String)
	 */

	public int takeRepairTool(RepairTool toolToGet) {
		int ans = 0;
		String toolName = toolToGet.getToolName();
		RepairTool toolInWarehouse = this.repairTools.get(toolName); 
		Driver.LOGGER.info("Taking tools " + toolName +" from Warehouse");
		Driver.LOGGER.info("Current Number of " + toolName + "in Warehouse is: " + this.repairTools.get(toolName).getNumberOfTools() );
		synchronized (toolInWarehouse) {
				
			if (toolInWarehouse.getNumberOfTools() - toolToGet.getNumberOfTools() >= 0){
				toolInWarehouse.takeTools(toolToGet);
				Driver.LOGGER.info("Number of " + toolName + "in Warehouse after taking is: " + this.repairTools.get(toolName).getNumberOfTools() );
				this.addToolTorepairToolsUsed(toolToGet);
				ans = 1;
			}else{
				ans = -1;
				
			}
			
			Driver.LOGGER.fine("Current Status of: " + toolInWarehouse.toString());
			Driver.LOGGER.fine("Returned " + toolToGet.toString() +" Materials from warehouse.");
			Driver.LOGGER.fine("Current Status of: " + toolInWarehouse.toString());
			
			
		}
		return ans;
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#returnRepairTool(java.lang.String)
	 */

	public void returnRepairTool(RepairTool repairToolToReturn) {
		String toolName = repairToolToReturn.getToolName();
		RepairTool toolInWarehouse = this.repairTools.get(toolName);
		Driver.LOGGER.info("Returning tools " + toolName +" to Warehouse");
		Driver.LOGGER.info("Current Number of " + toolName + "in Warehouse is: " + this.repairTools.get(toolName).getNumberOfTools() );
		synchronized (toolInWarehouse) {
			toolInWarehouse.returnTools(repairToolToReturn);
				
		}
		Driver.LOGGER.info("Number of " + toolName + "in Warehouse after returing is: " + this.repairTools.get(toolName).getNumberOfTools() );
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairMaterial(java.lang.String)
	 */

	public void takeRepairMaterial(RepairMaterial repairMaterialToTake) {
		String materialName = repairMaterialToTake.getMaterialName();
		RepairMaterial materialInWarehouse = this.repairMaterials.get(materialName);
		synchronized (materialInWarehouse) {
			
			
			materialInWarehouse.takeMaterial(repairMaterialToTake);
			
			this.addMaterialTorepairMaterialUsed(repairMaterialToTake);
			
			Driver.LOGGER.fine("Current Status of: " + materialInWarehouse.toString());
			Driver.LOGGER.fine("Taken " + repairMaterialToTake.toString() +" Materials from warehouse.");
			Driver.LOGGER.fine("Current Status of: " + materialInWarehouse.toString());
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

	public HashMap<String, RepairTool> getRepairTools() {
		return repairTools;
	}

	public void setRepairTools(HashMap<String, RepairTool> repairTools) {
		this.repairTools = repairTools;
	}

	public HashMap<String, RepairMaterial> getRepairMaterials() {
		return repairMaterials;
	}

	public void setRepairMaterials(HashMap<String, RepairMaterial> repairMaterials) {
		this.repairMaterials = repairMaterials;
	}

	public HashMap<String, RepairTool> getRepairToolsUsed() {
		return repairToolsUsed;
	}

	public void setRepairToolsUsed(HashMap<String, RepairTool> repairToolsUsed) {
		this.repairToolsUsed = repairToolsUsed;
	}

	public HashMap<String, RepairMaterial> getRepairMaterialsUsed() {
		return repairMaterialsUsed;
	}

	public void setRepairMaterialsUsed(
			HashMap<String, RepairMaterial> repairMaterialsUsed) {
		this.repairMaterialsUsed = repairMaterialsUsed;
	}
	
	
	
	
	
}
	
