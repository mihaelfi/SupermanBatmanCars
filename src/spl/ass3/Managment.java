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
	}
	

}
