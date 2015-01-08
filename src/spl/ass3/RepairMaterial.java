/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;
// TODO: Auto-generated Javadoc

/**
 * The Class RepairMaterial.
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class RepairMaterial {
	
	/** The material name. */
	private String materialName;
	
	/** The number of material in warehouse. */
	private int    numberOfMaterialInWarehouse;	
	
	
	/**
	 * Instantiates a new repair material.
	 *
	 * @param materialName
	 *            the material name
	 * @param numberOfMaterialInWarehouse
	 *            the number of material in warehouse
	 */
	public RepairMaterial(String materialName, int numberOfMaterialInWarehouse) {
		this.materialName = materialName;
		this.numberOfMaterialInWarehouse = numberOfMaterialInWarehouse;
	}
	
	
	/**
	 * Gets the number of materials.
	 *
	 * @return the number of materials
	 */
	public int getNumberOfMaterials() {
		return numberOfMaterialInWarehouse;
	}
	
	/**
	 * Sets the number of materials.
	 *
	 * @param numberOfMaterialInWarehouse
	 *            the new number of materials
	 */
	public void setNumberOfMaterials(int numberOfMaterialInWarehouse) {
		this.numberOfMaterialInWarehouse = numberOfMaterialInWarehouse;
	}
	
	/**
	 * Gets the material name.
	 *
	 * @return the material name
	 */
	public String getMaterialName() {
		return materialName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Material Name = " + this.materialName + ", Material Quantity is: " + this.numberOfMaterialInWarehouse+ "\n";
	}
	
	/**
	 * Take material.
	 *
	 * @param repairMaterailToTake
	 *            the repair materail to take
	 */
	public void takeMaterial(RepairMaterial repairMaterailToTake){
		this.numberOfMaterialInWarehouse = this.numberOfMaterialInWarehouse - repairMaterailToTake.getNumberOfMaterials();
	}
	

	
	
	
	

}
