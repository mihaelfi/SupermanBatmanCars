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
	
	
	
	public ArrayList<Asset> getAssetCollection() {
		return assetCollection;
	}

	public void setAssetCollection(ArrayList<Asset> assetCollection) {
		this.assetCollection = assetCollection;
	}

	public void setListOfDamagedAssets(ArrayList<Asset> listOfDamagedAssets) {
		this.listOfDamagedAssets = listOfDamagedAssets;
	}

	/**
	 * This method returns an Asset that fits the size and type requirements of the request
	 * @param requestToFindAssetFor
	 * @return an Asset that fits the size and type of the request.
	 */
	public synchronized Asset findAvailableAset(RentalRequest requestToFindAssetFor){
		Asset ans = null;
		String reqestedAssetType = requestToFindAssetFor.getAssetType();
		int	   requstedAssetSize = requestToFindAssetFor.getAssetSize();
		
		Driver.LOGGER.info("Trying to find an Asset that furfiles the request id: " + requestToFindAssetFor.getId() + " Asset Type: " + requestToFindAssetFor.getAssetType());
		
		boolean foundAsset = false;
		
		int i = 0;
		
		Driver.LOGGER.info("The length of Asset is: " + this.assetCollection.size());
		
		while(!foundAsset){
//			String assetTypeToTest = this.assetCollection.get(i).getType();
//			int    assetSizeToTest = this.assetCollection.get(i).getSize();
			if (i == this.assetCollection.size()){
				try {
					Driver.LOGGER.info("Didn't find available Asset, Waiting for change in Asset status and trying agian");
					wait(2000);
					Driver.LOGGER.info("Got Notify from CustomerGroupManager");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i = 0;
			}
			Asset  assetToTest = this.assetCollection.get(i);
			
//			synchronized (assetToTest) {
				
				
				Driver.LOGGER.fine("Value of i is : " + i);
				if ( (reqestedAssetType.equals(assetToTest.getType())) && requstedAssetSize <= assetToTest.getSize() ){
					
					Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the request id: *"+requestToFindAssetFor.getId()+"* . Now will check if it's available..." );
					if ( assetToTest.getStatus().equals("AVAILABLE")){
						Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the requestid: *"+requestToFindAssetFor.getId()+"* And Available!" );
						foundAsset = true;
						ans = assetToTest;
						ans.setStatusBooked();
					}else{
						try {
							Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the requestid: *"+requestToFindAssetFor.getId()+"* And **NOT** Available! waiting to seconds ..." );
							wait(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}else{
					Driver.LOGGER.fine("The Asset :" + assetToTest.getName() + "of type: " + assetToTest.getType() + "is **NOT** fitting the request id: *"+requestToFindAssetFor.getId()+"*");
				}
			i++;
			}
			
			
			
			
//		}
		
		
		return ans;
	}

}
