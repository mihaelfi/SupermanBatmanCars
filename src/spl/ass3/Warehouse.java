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
public class Warehouse implements WarehouseInterface {
	
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
	@Override
	public void insertToolFromParsing(RepairTool toolToInsert) {
		this.repairTools.put(toolToInsert.getToolName(), toolToInsert);
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#insertMaterialFromParsing(spl.ass3.RepairMaterial)
	 */
	@Override
	public void insertMaterialFromParsing(RepairMaterial materialToInsert) {
		this.repairMaterials.put(materialToInsert.getMaterialName(), materialToInsert);
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairTool(java.lang.String)
	 */
	@Override
	public void getRepairTool(String toolToGet) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#returnRepairTool(java.lang.String)
	 */
	@Override
	public void returnRepairTool(String repairToolToReturn) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getRepairMaterial(java.lang.String)
	 */
	@Override
	public void getRepairMaterial(String repairMaterialToGet) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getNumberOfToolInWarehouse(java.lang.String)
	 */
	@Override
	public int getNumberOfToolInWarehouse(String toolName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#getNumberOfMaterialInWarehouse(java.lang.String)
	 */
	@Override
	public int getNumberOfMaterialInWarehouse(String materialName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public String warehouseToolstoString(){
		String ans = "Printing warehouse tools content: \n";
		Iterator it =this.repairTools.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n The number of " + pairs.getKey() + " in the warehouse is: " + ((RepairTool)pairs.getValue()).getNumberOfToolsInWareHouse();
		}
		return ans;
	}
	
	public String warehouseMaterialsstoString(){
		String ans = "Printing warehouse material content: \n";
		Iterator it =this.repairMaterials.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			ans = ans + "\n The number of " + pairs.getKey() + " in the warehouse is: " + ((RepairMaterial)pairs.getValue()).getNumberOfMaterialInWarehouse();
		}
		return ans;
	}
	
	public String toString(){
		String ans = "The contents of the Warehouse are:\n";
		ans = ans + this.warehouseToolstoString() + this.warehouseMaterialsstoString();
		return ans;
	}
	
}
	
