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
	public synchronized Asset findAvailableAset(RentalRequest requestToFindAssetFor){
		Asset ans = null;
		String reqestedAssetType = requestToFindAssetFor.getAsset().getType();
		int	   requstedAssetSize = requestToFindAssetFor.getAsset().getSize();
		
		Driver.LOGGER.info("Trying to find an Asset that furfiles the request id: " + requestToFindAssetFor.getId() + " Asset Type: " + requestToFindAssetFor.getAssetType());
		
		boolean foundAsset = false;
		
		int i = 0;
		Driver.LOGGER.info("The length of Asset is: " + this.assetCollection.size());
		Driver.LOGGER.info("Value of i is : " + i);
		
		while(!foundAsset){
//			String assetTypeToTest = this.assetCollection.get(i).getType();
//			int    assetSizeToTest = this.assetCollection.get(i).getSize();
			Asset  assetToTest = this.assetCollection.get(i);
			
			if (i == this.assetCollection.size()){
				i = 0;
			}
			
			if ( (reqestedAssetType.equals(assetToTest.getType())) && requstedAssetSize <= assetToTest.getSize() ){
				
				Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the request. Now will check if it's available..." );
				if ( assetToTest.getStatus().equals("AVAILABLE")){
					Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the request. And Available!" );
					foundAsset = true;
					ans = assetToTest;
					ans.setStatusBooked();
				}else{
					try {
						Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the request. And **NOT** Available! waiting to seconds ..." );
						wait(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			i++;
			
		}
		
		
		return ans;
	}
//		Asset ans = null;
//		String reqestedAssetType = requestToFindAssetFor.getAssetType();
//		int    requestedAssetSize = requestToFindAssetFor.getAssetSize();
//		boolean assetFound = false;
//		int i = 0;
//		
//		Driver.LOGGER.info("Trying to find asset for the request : " + requestToFindAssetFor.getAssetType());
//		
//		while (!assetFound){
//			if (i == this.assetCollection.size()){
//				i = 0 ;
//			}
//
//			if ((this.assetCollection.get(i).getSize() ==  requestedAssetSize) && this.assetCollection.get(i).getType().equals(reqestedAssetType) ){
//				Driver.LOGGER.info("Trying to see if Asset " + this.assetCollection.get(i).getType() + "Where asset name is : *"+ this.assetCollection.get(i).getName() +"* is acceptable.");
//				synchronized (this.assetCollection.get(i)) {
//					if (this.assetCollection.get(i).getStatus() == "AVAILABLE" ){
//						ans = assetCollection.get(i);
//						assetFound = true;
//						this.assetCollection.get(i).setStatusBooked();
//						Driver.LOGGER.info("Asset was indeed acceptable");
//					}else{
//					  try {
//						wait(2000);
//						Driver.LOGGER.info("Asset wasn't found..  waiting 2 seconds and trying again");
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					}
//					
//				}
//			}
//			i++;
//		}
//		
//			
//		
//		return ans;
//		
//	}

}
