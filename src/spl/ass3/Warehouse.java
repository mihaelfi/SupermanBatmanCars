package spl.ass3;

import java.util.HashMap;

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
	
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#insertToolFromParsing(spl.ass3.RepairTool)
	 */
	@Override
	public void insertToolFromParsing(RepairTool toolToInsert) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see spl.ass3.WarehouseInterface#insertMaterialFromParsing(spl.ass3.RepairMaterial)
	 */
	@Override
	public void insertMaterialFromParsing(RepairMaterial materialToInsert) {
		// TODO Auto-generated method stub
		
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
	
	


}
