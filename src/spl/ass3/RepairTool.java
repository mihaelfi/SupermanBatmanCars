package spl.ass3;
/**
 * 
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class RepairTool {
	
	private String toolName;
	private int    numberOfToolInWarehouse;
	
	
	
	public RepairTool(String toolName, int numberOfToolInWareHouse) {
		this.toolName = toolName;
		this.numberOfToolInWarehouse = numberOfToolInWareHouse;
	}
	
	
	
	public int getNumberOfToolsInWareHouse() {
		return numberOfToolInWarehouse;
	}
	public void setNumberOfToolsInWareHouse(int numberOfToolsInWareHouse) {
		this.numberOfToolInWarehouse = numberOfToolsInWareHouse;
	}
	public String getToolName() {
		return toolName;
	}
	
	

}
