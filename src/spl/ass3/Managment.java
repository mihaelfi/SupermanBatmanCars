package spl.ass3;

import java.util.ArrayList;

public class Managment {
	
	protected ArrayList<ClerkDetails> clerkDetailsCollection;
	protected ArrayList<CustomerGroupDetails> customerGroupDetailsCollection;
	protected Assets assets;
	public    Warehouse warehouse;
	public 	  ArrayList<RepairToolInformation> repairToolInformationCollection;
	public 	  ArrayList<RepairMaterialInformation> repairMaterialInformationCollection;
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		this.clerkDetailsCollection = new ArrayList<ClerkDetails>();
		this.repairMaterialInformationCollection = new ArrayList<RepairMaterialInformation>();
		this.repairToolInformationCollection = new ArrayList<RepairToolInformation>();
		
	}
	
	public void addClerkDetails(ClerkDetails clerkDetailsToAdd){
		this.clerkDetailsCollection.add(clerkDetailsToAdd);
		Driver.LOGGER.info("Clerk Added.");
	}
	
	public void addRepairToolInformation (RepairToolInformation repairToolInformationToAdd){
		this.repairToolInformationCollection.add(repairToolInformationToAdd);
	}
	
	public void addRepairMaterialInformation (RepairMaterialInformation repairMaterialInformationToAdd){
		this.repairMaterialInformationCollection.add(repairMaterialInformationToAdd);
	}
	
	public void addAssets(Assets assetsToAdd){
		this.assets = assetsToAdd;
	}
	
	
	public String warhouseClerkDetailsToString(){
		String ans = "Printing clerk Details in warehouse ...\n";
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i++){
			ans = ans + this.clerkDetailsCollection.get(i).toString() + "\n";
		}
		return ans;
	}
	
	public String assetsToString(){
		return this.assets.toString();
	}
	
	
	

}
