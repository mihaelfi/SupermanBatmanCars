package spl.ass3;
// TODO: Auto-generated Javadoc

/**
 * The Class RepairTool.
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class RepairTool {
	
	/** The tool name. */
	private String toolName;
	
	/** The number of tool in warehouse. */
	private int    numberOfToolInWarehouse;
	
	
	
	/**
	 * Instantiates a new repair tool.
	 *
	 * @param toolName the tool name
	 * @param numberOfToolInWareHouse the number of tool in ware house
	 */
	public RepairTool(String toolName, int numberOfToolInWareHouse) {
		this.toolName = toolName;
		this.numberOfToolInWarehouse = numberOfToolInWareHouse;
	}
	
	
	
	/**
	 * Gets the number of tools in ware house.
	 *
	 * @return the number of tools in ware house
	 */
	public int getNumberOfToolsInWareHouse() {
		return numberOfToolInWarehouse;
	}
	
	/**
	 * Sets the number of tools in ware house.
	 *
	 * @param numberOfToolsInWareHouse the new number of tools in ware house
	 */
	public void setNumberOfToolsInWareHouse(int numberOfToolsInWareHouse) {
		this.numberOfToolInWarehouse = numberOfToolsInWareHouse;
	}
	
	/**
	 * Gets the tool name.
	 *
	 * @return the tool name
	 */
	public String getToolName() {
		return toolName;
	}
	
	public String toString(){
		return "Tool Name = " + this.toolName + ", Tool Quantity is: " + this.numberOfToolInWarehouse+"\n";
	}
	
	

}
