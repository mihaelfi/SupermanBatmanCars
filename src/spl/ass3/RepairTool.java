/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;
// TODO: Auto-generated Javadoc

/**
 * The Class RepairTool.
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class RepairTool implements Comparable<RepairTool> {
	
	/** The tool name. */
	private String toolName;
	
	/** The number of tool in warehouse. */
	private int    numberOfTools;
	
	
	
	/**
	 * Instantiates a new repair tool.
	 *
	 * @param toolName the tool name
	 * @param numberOfToolInWareHouse the number of tool in ware house
	 */
	public RepairTool(String toolName, int numberOfToolInWareHouse) {
		this.toolName = toolName;
		this.numberOfTools = numberOfToolInWareHouse;
	}
	
	
	
	/**
	 * Gets the number of tools in ware house.
	 *
	 * @return the number of tools in ware house
	 */
	public int getNumberOfTools() {
		return numberOfTools;
	}
	
	/**
	 * Sets the number of tools in ware house.
	 *
	 * @param numberOfToolsInWareHouse the new number of tools in ware house
	 */
	public void setNumberOfTools(int numberOfToolsInWareHouse) {
		this.numberOfTools = numberOfToolsInWareHouse;
	}
	
	/**
	 * Gets the tool name.
	 *
	 * @return the tool name
	 */
	public String getToolName() {
		return toolName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Tool Name = " + this.toolName + ", Tool Quantity is: " + this.numberOfTools+"\n";
	}
	
	/**
	 * Take tools.
	 *
	 * @param toolToTake
	 *            the tool to take
	 */
	public void takeTools(RepairTool toolToTake){
		this.numberOfTools = this.numberOfTools - toolToTake.getNumberOfTools();
	}
	
	/**
	 * Return tools.
	 *
	 * @param toolToReturn
	 *            the tool to return
	 */
	public void returnTools(RepairTool toolToReturn){
		this.numberOfTools = this.numberOfTools + toolToReturn.getNumberOfTools();
	}



	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RepairTool o) {
		int ans = 0;
		if (this.toolName.hashCode() > o.toolName.hashCode()){
			ans = 1;
		}else if(this.toolName.hashCode() < o.toolName.hashCode()){
			ans = -1;
		}
		return ans;
	}
	
	
	

}
