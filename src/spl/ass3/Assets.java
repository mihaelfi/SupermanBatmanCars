/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class Assets.
 */
public class Assets {
	
	/** The asset collection. */
	private ArrayList<Asset> assetCollection;
	
	/** The list of damaged assets. */
	private ArrayList<Asset> listOfDamagedAssets;
	
	
	/**
	 * Instantiates a new assets.
	 */
	public Assets(){
		this.assetCollection = new ArrayList<Asset>();
		this.listOfDamagedAssets = new ArrayList<Asset>();
		
	}
	
	/**
	 * Adds the asset.
	 *
	 * @param assetToAdd the asset to add
	 */
	public void addAsset(Asset assetToAdd){
		this.assetCollection.add(assetToAdd);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.assetCollection.toString();
	}
	
	/**
	 * Gets the list of damaged assets.
	 *
	 * @return the list of damaged assets
	 */
	public ArrayList<Asset> getListOfDamagedAssets(){
		return this.listOfDamagedAssets;
		
	}
	
	/**
	 * Sort assets by size.
	 */
	public void sortAssetsBySize(){
		Collections.sort(this.assetCollection); 
	}
	
	
	
	/**
	 * Gets the asset collection.
	 *
	 * @return the asset collection
	 */
	public ArrayList<Asset> getAssetCollection() {
		return assetCollection;
	}

	/**
	 * Sets the asset collection.
	 *
	 * @param assetCollection the new asset collection
	 */
	public void setAssetCollection(ArrayList<Asset> assetCollection) {
		this.assetCollection = assetCollection;
	}

	/**
	 * Sets the list of damaged assets.
	 *
	 * @param listOfDamagedAssets the new list of damaged assets
	 */
	public void setListOfDamagedAssets(ArrayList<Asset> listOfDamagedAssets) {
		this.listOfDamagedAssets = listOfDamagedAssets;
	}

	/**
	 * This method returns an Asset that fits the size and type requirements of the request.
	 *
	 * @param requestToFindAssetFor the request to find asset for
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
			
			if (i == this.assetCollection.size()){
				try {
					Driver.LOGGER.info("Didn't find available Asset, Waiting for change in Asset status and trying agian");
					
					// waiting for any change in assets and trying the search again.
					synchronized (this) {
						this.wait();
					}
					Driver.LOGGER.info("Got Notify from CustomerGroupManager");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				i = 0;
			}
			
			
			Asset  assetToTest = this.assetCollection.get(i);
			
				
				Driver.LOGGER.fine("Value of i is : " + i);
				if ( (reqestedAssetType.equals(assetToTest.getType())) && requstedAssetSize <= assetToTest.getSize() ){
					
					Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the request id: *"+requestToFindAssetFor.getId()+"* . Now will check if it's available..." );
					if ( assetToTest.getStatus().equals("AVAILABLE")){
						Driver.LOGGER.info("The Asset :" + assetToTest.getName() + " of type: " + assetToTest.getType() +" is fitting the requestid: *"+requestToFindAssetFor.getId()+"* And Available!" );
						foundAsset = true;
						ans = assetToTest;
						ans.setStatusBooked();
					}

				}
			i++;
			}

		return ans;
	}

}
