package spl.ass3;

import java.util.ArrayList;

public class Assets {
	
	protected ArrayList<Asset> assetCollection;
	
	
	public Assets(){
		this.assetCollection = new ArrayList<Asset>();
	}
	
	public void addAsset(Asset assetToAdd){
		this.assetCollection.add(assetToAdd);
	}
	
	public String toString(){
		return this.assetCollection.toString();
	}

}
