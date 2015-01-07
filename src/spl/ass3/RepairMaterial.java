package spl.ass3;
/**
 * 
 */

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class RepairMaterial {
	
	private String materialName;
	private int    numberOfMaterialInWarehouse;	
	
	
	public RepairMaterial(String materialName, int numberOfMaterialInWarehouse) {
		this.materialName = materialName;
		this.numberOfMaterialInWarehouse = numberOfMaterialInWarehouse;
	}
	
	
	public int getNumberOfMaterials() {
		return numberOfMaterialInWarehouse;
	}
	public void setNumberOfMaterials(int numberOfMaterialInWarehouse) {
		this.numberOfMaterialInWarehouse = numberOfMaterialInWarehouse;
	}
	public String getMaterialName() {
		return materialName;
	}
	
	public String toString(){
		return "Material Name = " + this.materialName + ", Material Quantity is: " + this.numberOfMaterialInWarehouse+ "\n";
	}
	
	public void takeMaterial(RepairMaterial repairMaterailToTake){
		this.numberOfMaterialInWarehouse = this.numberOfMaterialInWarehouse - repairMaterailToTake.getNumberOfMaterials();
	}
	

	
	
	
	

}
