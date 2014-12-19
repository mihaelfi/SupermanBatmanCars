package spl.ass3;


/**
 * @author Michael Fildstein Id:309161594
 *
 */

public interface WarehouseInterface {

	/**
	 * This method inserts the tool 'toolToInsert' to the Warehouse.
	 * @param toolToInsert - The tool to insert to the warehouse.
	 * @pre: none.
     * @post: at least one element exists in the warehouse tool collection.
	 */
	public void insertToolFromParsing(RepairTool toolToInsert);

	/**
	 * This method inserts the Material 'MaterialToInsert' to the Warehouse.
	 * @param materialToInsert - The Material to insert to the warehouse.
	 * @pre: none.
     * @post: at least one element exists in the warehouse material collection.
     *
	 */
	public void insertMaterialFromParsing(RepairMaterial materialToInsert);

	/**
	 * Simulates the taking of a tool from the warehouse. In reality reduces the number of 'toolToGet'
	 * by one.
	 * @param toolToGet
	 * @pre at least one tool exists in the collection
	 * @post The number of current tools decreases by 1.
	 * 
	 */
	public void getRepairTool(String toolToGet);

	/**
	 * Simulates the returning of a tool to the warehouse. In reality increases the number of 'repairToolToReturn' in the warehouse
	 * by one.
	 * @param repairToolToReturn
	 * 
	 */
	public void returnRepairTool(String repairToolToReturn);

	/**
	 * Simulates the taking of a RepairMaterial from the warehouse. In reality reduces the number of 'repairMaterial'
	 * by one.
	 * @param toolToGet
	 * @pre at least one tool exists in the collection
	 * @post The number of current tools decreases by 1.
	 */
	public void getRepairMaterial(String repairMaterialToGet);
	
	

	/**
	 * Returns the number of 'toolName' currently in the warehouse.
	 * @param toolName
	 * @return number of 'toolName' currently in the warehouse.
	 */

	
	public int getNumberOfToolInWarehouse(String toolName);
	
	
	
	/**
	 * Returns the number of 'materialName' currently in the warehouse.
	 * @param materialName
	 * @return number of 'materialName' currently in the warehouse.
	 */

	public int getNumberOfMaterialInWarehouse(String materialName);
	
	

}
