package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;

public class RunnableMaintenanceRequest implements Runnable  {
	
	private HashMap<String,RepairToolInformation> repairToolInformationCollection;
	private HashMap<String,RepairMaterialInformation> repairMaterialInformationCollection;
	private Asset asset;
	private Warehouse warehouse;
	private final int UNHEALTHY_ASSET_HEALTH = 65;
	
	
	public RunnableMaintenanceRequest(
			HashMap<String, RepairToolInformation> repairToolInformationCollection,
			HashMap<String, RepairMaterialInformation> repairMaterialInformationCollection,
			Asset asset, Warehouse warehouse) {

		this.repairToolInformationCollection = repairToolInformationCollection;
		this.repairMaterialInformationCollection = repairMaterialInformationCollection;
		this.asset = asset;
		this.warehouse = warehouse;
	}



	private void returnTools(ArrayList<RepairTool> toolsToReturn){
		for (int i = 0 ; i < toolsToReturn.size() ; i++){
			this.warehouse.returnRepairTool(toolsToReturn.get(i));
		}
	}



	@Override
	public void run() {
		
		
		int numberOfFixedAssetContents = 0;
		ArrayList<AssetContent> assetContents = this.asset.getAssetContents();
		
		while(numberOfFixedAssetContents != assetContents.size()){
			
			
			
			for (int i = 0 ; i < assetContents.size() && assetContents.get(i).getHealth() < UNHEALTHY_ASSET_HEALTH ; i++){
				
				RepairToolInformation toolToRepair = repairToolInformationCollection.get(assetContents.get(i).getName());
				RepairMaterialInformation materialToRepair = repairMaterialInformationCollection.get(assetContents.get(i).getName());
				
				//getting tools
				
				ArrayList<RepairTool> repairToolsNeeded = toolToRepair.getToolsNeededForRepair();
				ArrayList<RepairMaterial> repairMaterialNeeded = materialToRepair.getMaterialsNeededForRepair();
				
				ArrayList<RepairTool> repairToolsTaken = new ArrayList<RepairTool>();
				boolean canTakeAllTools = true;
				
				for (int j = 0 ; j < repairToolsNeeded.size() && canTakeAllTools; j++){
					int succsesfullyTakenTools = 0; 
					
					succsesfullyTakenTools =  warehouse.takeRepairTool(repairToolsNeeded.get(j));
					if (succsesfullyTakenTools == 1){
						repairToolsTaken.add(repairToolsNeeded.get(i));
						Driver.LOGGER.info("Repair Tool " + repairToolsNeeded.get(j).toString() + " was taken");
						
						// Taking materials after we are sure that we have taken all the tools
						for (int m = 0 ; m < repairMaterialNeeded.size() ; m++){
							warehouse.takeRepairMaterial(repairMaterialNeeded.get(m));
							
						}
						
						// Fixing AssetContent
						assetContents.get(i).calculateRepairCostTime();
						long sleepTime = (long)assetContents.get(i).getRepairCostTime();
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Returnting Tools
						returnTools(repairToolsTaken);
						
						// Set asset as fixed
						assetContents.get(i).setHealth(100.0);
						
						numberOfFixedAssetContents++;
						
						
					}else{
						canTakeAllTools = false;
						returnTools(repairToolsTaken);
						Driver.LOGGER.info("The warhouse does not have enough tools, returning everything I took");
					}
					
					
				}
				
				
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
		}
	}

	
	
	
	
	
	

}
