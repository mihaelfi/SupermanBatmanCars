package spl.ass3;

import java.util.ArrayList;
import java.util.Collections;

public class Assets {
	
	protected ArrayList<Asset> assetCollection;
	protected ArrayList<Asset> listOfDamagedAssets;
	
	
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
		while (!assetFound){
			
			if (this.assetCollection.get(i).size ==  requestedAssetSize && this.assetCollection.get(i).type == reqestedAssetType ){
				synchronized (this.assetCollection.get(i)) {
					if (this.assetCollection.get(i).getStatus() == "AVAILABLE" ){
						ans = assetCollection.get(i);
						assetFound = true;
					}else{
					  try {
						wait(2000);
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
