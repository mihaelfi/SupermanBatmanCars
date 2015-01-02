package spl.ass3;

import java.util.ArrayList;

public class Managment {
	
	protected ArrayList<ClerkDetails> clerkDetailsCollection;
	protected ArrayList<CustomerGroupDetails> customerGroupDetailsCollection;
	protected Assets assets;
	public    Warehouse warehouse;
	protected ArrayList<RepairToolInformation> repairToolInformationCollection;
	protected ArrayList<RepairMaterialInformation> repairMaterialInformationCollection;
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		clerkDetailsCollection = new ArrayList<ClerkDetails>();
	}
	
	public void addClerkDetails(ClerkDetails clerkDetailsToAdd){
		this.clerkDetailsCollection.add(clerkDetailsToAdd);
		Driver.LOGGER.info("Clerk Added.");
	}
	
	
	public String warhouseClerkDetailsToString(){
		String ans = "Printing clerk Details in warehouse ...\n";
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i++){
			ans = ans + this.clerkDetailsCollection.get(i).toString() + "\n";
		}
		return ans;
	}
	

}
