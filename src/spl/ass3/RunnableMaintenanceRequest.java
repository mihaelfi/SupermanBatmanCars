package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class RunnableMaintenanceRequest implements Runnable  {
	
	private HashMap<String,RepairToolInformation> repairToolInformationCollection;
	private HashMap<String,RepairMaterialInformation> repairMaterialInformationCollection;
	private Asset asset;
	private Warehouse warehouse;
	private final int UNHEALTHY_ASSET_HEALTH = 65;
	private BlockingQueue<Asset> assetsForRepairQueue;
	private String repairPersonName; 
	
	
	public RunnableMaintenanceRequest(
			HashMap<String, RepairToolInformation> repairToolInformationCollection,
			HashMap<String, RepairMaterialInformation> repairMaterialInformationCollection,
			Asset asset, Warehouse warehouse , BlockingQueue<Asset> assetsForRepair, String repairPersonName) {

		this.repairToolInformationCollection = repairToolInformationCollection;
		this.repairMaterialInformationCollection = repairMaterialInformationCollection;
		this.asset = asset;
		this.warehouse = warehouse;
		this.assetsForRepairQueue = assetsForRepair;
		this.repairPersonName = repairPersonName;
	}



	private void returnTools(ArrayList<RepairTool> toolsToReturn){
		for (int i = 0 ; i < toolsToReturn.size() ; i++){
			this.warehouse.returnRepairTool(toolsToReturn.get(i));
		}
	}
	
	



	@Override
	public void run() {
		
		while(true){
			
			
			int numberOfFixedAssetContents = 0;
			
				Driver.LOGGER.warning("Maintence worker " + this.repairPersonName +" has started working and is waiting to assets to repair.");
				try {
					
					// taking broken asset and fixing it.
					this.asset = assetsForRepairQueue.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			if (this.asset.getName().equals("POISON_PILL")){
				try {
					this.assetsForRepairQueue.put(this.asset);
					Driver.LOGGER.info("There will be no more maintence Requests !!! " + this.repairPersonName + " commits suicide !");
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Driver.LOGGER.info(this.repairPersonName +"Trying to fix Asset: " + this.asset.toString());
			ArrayList<AssetContent> assetContents = this.asset.getAssetContents();
				
			while(numberOfFixedAssetContents < assetContents.size()){
				Driver.LOGGER.warning(" The thread id is :  "+Thread.currentThread().getId() + "\t NEED FIX ::" + asset.getName() + "   FIX:  " + numberOfFixedAssetContents + "    FROM: " + assetContents.size());
//				boolean triedAllAtLeastOnce = false;
				
//				if(triedAllAtLeastOnce){
//					synchronized (this.warehouse.getRepairTools()) {
//						try {
//							Driver.LOGGER.info("Tried to fixed all asset contents at least once");
//							wait();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
				
				for (int i = 0 ; i < assetContents.size() ; i++){
						
					if (assetContents.get(i).getHealth() < UNHEALTHY_ASSET_HEALTH){
							
					RepairToolInformation toolToRepair = repairToolInformationCollection.get(assetContents.get(i).getName());
					RepairMaterialInformation materialToRepair = repairMaterialInformationCollection.get(assetContents.get(i).getName());
							
					//getting tools
							
					ArrayList<RepairTool> repairToolsNeeded = toolToRepair.getToolsNeededForRepair();
					ArrayList<RepairMaterial> repairMaterialNeeded = materialToRepair.getMaterialsNeededForRepair();
							
					ArrayList<RepairTool> repairToolsTaken = new ArrayList<RepairTool>();
					boolean canTakeAllTools = true;
					
					Driver.LOGGER.info(this.repairPersonName + " is trying to fix Asset content : " + assetContents.get(i).toString()  + " in Asset " + this.asset.getName() );
							
					for (int j = 0 ; j < repairToolsNeeded.size() && canTakeAllTools; j++){
						int didSuccsesfullyTakenTools = 0;  // -1 = fail 1 = success.
						didSuccsesfullyTakenTools =  warehouse.takeRepairTool(repairToolsNeeded.get(j));
								
						if (didSuccsesfullyTakenTools == 1){
							Driver.LOGGER.warning(this.repairPersonName + " Has succesully taken " + repairToolsNeeded.get(j).toString());
							repairToolsTaken.add(repairToolsNeeded.get(j));
						}else{
							canTakeAllTools = false;
							Driver.LOGGER.warning(this.repairPersonName + " Has failed to take " + repairToolsNeeded.get(j).toString() + " from warehouse, because there are only" + warehouse.getRepairTools().get(repairToolsNeeded.get(j).getToolName()).toString() + "tools in the warehouse ");
							returnTools(repairToolsTaken);
							
						}
								
										
					}
					
					if (canTakeAllTools){
						
						Driver.LOGGER.info(this.repairPersonName + " Succesully gather all tools needed to repair" + assetContents.get(i).toString());
						
						// Taking materials after we are sure that we have taken all the tools
						for (int m = 0 ; m < repairMaterialNeeded.size() ; m++){
						warehouse.takeRepairMaterial(repairMaterialNeeded.get(m));
						Driver.LOGGER.info(this.repairPersonName + " Has succesully taken " + repairMaterialNeeded.get(m).toString());
									
						}
						
						// Fixing AssetContent
						assetContents.get(i).calculateRepairCostTime();
									
						long sleepTime = (long)assetContents.get(i).getRepairCostTime();
									
						Driver.LOGGER.info(this.repairPersonName + " Calculated the Repair cost time of " + assetContents.get(i).toString() + " is: " + sleepTime);
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// Returnting Tools
						returnTools(repairToolsTaken);
						Driver.LOGGER.info(this.repairPersonName + "is returning all tools");
						// Set assetContent  as fixed
						assetContents.get(i).setHealth(100.0);
						Driver.LOGGER.info(this.repairPersonName + "Has set " + assetContents.get(i).toString() +"Health to: 100.0"  );
						
						numberOfFixedAssetContents++;
					
								
					}
						
				}
			}
		  }
		  // Repaired all assetContens
		synchronized (this.asset) {
			
			Driver.LOGGER.info("Setting asset"+ this.asset.getName() + " to Available");
			this.asset.setStatusAvailable();
			this.asset.setRepaired();
		}
		synchronized (this.repairMaterialInformationCollection) {
			this.repairMaterialInformationCollection.notifyAll();
		}
		Driver.LOGGER.warning(this.repairPersonName + " has finished reparing Asset " + this.asset.getName());
		Driver.LOGGER.info("Asset printout after fixing" + this.asset.toString());
		}
	}
}
