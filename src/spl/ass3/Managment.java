package spl.ass3;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Managment {
	
	protected ArrayList<ClerkDetails> clerkDetailsCollection;
	protected ArrayList<CustomerGroupDetails> customerGroupDetailsCollection;
	protected Assets assets;
	public    Warehouse warehouse;
	public 	  ArrayList<RepairToolInformation> repairToolInformationCollection;
	public 	  ArrayList<RepairMaterialInformation> repairMaterialInformationCollection;
	protected BlockingQueue<RentalRequest> rentalRequestCollection;
	
	
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		this.clerkDetailsCollection = new ArrayList<ClerkDetails>();
		this.repairMaterialInformationCollection = new ArrayList<RepairMaterialInformation>();
		this.repairToolInformationCollection = new ArrayList<RepairToolInformation>();
		this.customerGroupDetailsCollection = new ArrayList<CustomerGroupDetails>();
		this.rentalRequestCollection = new ArrayBlockingQueue<RentalRequest>(10, true);
		
	}
	
	public void addClerkDetails(ClerkDetails clerkDetailsToAdd){
		this.clerkDetailsCollection.add(clerkDetailsToAdd);
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
	
	public void addCustomerGroupDetailsToCollection(CustomerGroupDetails customerGroupDetailsToAdd){
		this.customerGroupDetailsCollection.add(customerGroupDetailsToAdd);
	}
	
	
	public String clerkDetailsToString(){
		String ans = "Printing clerk Details in warehouse ...\n";
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i++){
			ans = ans + this.clerkDetailsCollection.get(i).toString() + "\n";
		}
		return ans;
	}
	
	public String assetsToString(){
		return this.assets.toString();
	}
	
	public String customerGroupDetailsToString(){
		return this.customerGroupDetailsCollection.toString();
	}
	
	public String warehouseToString(){
		return this.warehouse.toString();
	}
	
	public String repairToolInformationToString(){
		return this.repairToolInformationCollection.toString();
	}
	
	public String repairMaterialInformationToString(){
		return this.repairMaterialInformationCollection.toString();
	}
	
	public void addRentalRequestToBlockingQueue (RentalRequest retnalRequestToAdd){
		try {
			this.rentalRequestCollection.put(retnalRequestToAdd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
