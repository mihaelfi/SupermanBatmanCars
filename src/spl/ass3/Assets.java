package spl.ass3;

import java.util.ArrayList;
import java.util.Collections;

public class Assets {
	
	private ArrayList<Asset> assetCollection;
	private ArrayList<Asset> listOfDamagedAssets;
	
	
	public Assets(){
		this.assetCollection = new ArrayList<Asset>();
		this.listOfDamagedAssets = new ArrayList<Asset>();
		
	}
	
	public void addAsset(Asset assetToAdd){
		this.assetCollection.add(assetToAdd);
	}
	
	public String toString(){
		return this.assetCollection.toString();
	}
	
	public ArrayList<Asset> getListOfDamagedAssets(){
		return this.listOfDamagedAssets;
		
	}
	
	public void sortAssetsBySize(){
		Collections.sort(this.assetCollection); 
	}
	
	/**
	 * This method returns an Asset that fits the size and type requirements of the request
	 * @param requestToFindAssetFor
	 * @return an Asset that fits the size and type of the request.
	 */
	public Asset findAvailableAset(RentalRequest requestToFindAssetFor){
		Asset ans = null;
		String reqestedAssetType = requestToFindAssetFor.getAssetType();
		int    requestedAssetSize = requestToFindAssetFor.getAssetSize();
		boolean assetFound = false;
//		ArrayList<Asset> potentailAssets = new ArrayList<Asset>();
		int i = 0;
		Driver.LOGGER.info("Trying to find asset for the request : " + requestToFindAssetFor.getAssetType());
		while (!assetFound){
			if (i == this.assetCollection.size()){
				i = 0 ;
			}
//			String assetType = this.assetCollection.get(i).getType();
//			String requestType = reqestedAssetType;
//			boolean typeMatch = (assetType.equals(requestType));
//			int assetSize = this.assetCollection.get(i).getSize();
//			int requestSize =requestedAssetSize; 
//			Driver.LOGGER.info("The asset type is " + assetType + " and the request type is " + requestType);
//			Driver.LOGGER.info("The asset size is " + assetSize + " and the request type is " + requestSize);
//			boolean sizeMatch = (this.assetCollection.get(i).getSize() ==(requestedAssetSize));
//			Driver.LOGGER.info("The size matches:" +sizeMatch + "\nThe Type matches:" + typeMatch);
//			Driver.LOGGER.info("inside of while loop");
//			Driver.LOGGER.info("checking if " + this.assetCollection.get(i).getSize() +" size equals " + requestedAssetSize);
//			Driver.LOGGER.info("checking if " + this.assetCollection.get(i).getType() +" type equals " + reqestedAssetType);
			// && 
			if ((this.assetCollection.get(i).getSize() ==  requestedAssetSize) && this.assetCollection.get(i).getType().equals(reqestedAssetType) ){
				Driver.LOGGER.info("Trying to see if Asset " + this.assetCollection.get(i).getName() + " is acceptable.");
				synchronized (this.assetCollection.get(i)) {
					if (this.assetCollection.get(i).getStatus() == "AVAILABLE" ){
						ans = assetCollection.get(i);
						assetFound = true;
						this.assetCollection.get(i).setStatusBooked();
						Driver.LOGGER.info("Asset was indeed acceptable");
					}else{
					  try {
						wait(2000);
						Driver.LOGGER.info("Asset wasn't found..  waiting 2 seconds and trying again");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
				}
			}
			i++;
		}
		
			
		
		return ans;
		
	}

}
