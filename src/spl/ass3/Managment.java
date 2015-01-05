package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Managment {
	
	private 	ArrayList<ClerkDetails> clerkDetailsCollection;
	private	    ArrayList<CustomerGroupDetails> customerGroupDetailsCollection;
	private 	Assets assets;
	private    	Warehouse warehouse;
	private     HashMap  <String, RepairToolInformation> repairToolInformationCollection;
	private     HashMap  <String, RepairMaterialInformation> repairMaterialInformationCollection;
	private 	BlockingQueue<RentalRequest> rentalRequestCollection;
	private 	ArrayList<DamageReport> damageReportCollection;
	
	
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		this.clerkDetailsCollection = new ArrayList<ClerkDetails>();
		this.repairMaterialInformationCollection = new HashMap  <String, RepairMaterialInformation>();
		this.repairToolInformationCollection = new HashMap  <String, RepairToolInformation>();
		this.customerGroupDetailsCollection = new ArrayList<CustomerGroupDetails>();
		this.rentalRequestCollection = new ArrayBlockingQueue<RentalRequest>(10, true);
		this.damageReportCollection = new ArrayList<DamageReport>();
		
	}
	
	public void addClerkDetails(ClerkDetails clerkDetailsToAdd){
		this.clerkDetailsCollection.add(clerkDetailsToAdd);
	}
	
	public void addRepairToolInformation (RepairToolInformation repairToolInformationToAdd){
//		this.repairToolInformationCollection.put(repairToolInformationToAdd.getNameOfItemToBeRepaired(), repairToolInformationToAdd);
		this.repairToolInformationCollection.put(repairToolInformationToAdd.getNameOfItemToBeRepaired(), repairToolInformationToAdd);
	}
	
	public void addRepairMaterialInformation (RepairMaterialInformation repairMaterialInformationToAdd){
		this.repairMaterialInformationCollection.put(repairMaterialInformationToAdd.getNameOfItemToBeRepaired(), repairMaterialInformationToAdd);
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
	
	public void addDamageReport(DamageReport damageReportToAdd){
		this.damageReportCollection.add(damageReportToAdd);
	}

	public ArrayList<ClerkDetails> getClerkDetailsCollection() {
		return clerkDetailsCollection;
	}

	public void setClerkDetailsCollection(
			ArrayList<ClerkDetails> clerkDetailsCollection) {
		this.clerkDetailsCollection = clerkDetailsCollection;
	}

	public ArrayList<CustomerGroupDetails> getCustomerGroupDetailsCollection() {
		return customerGroupDetailsCollection;
	}

	public void setCustomerGroupDetailsCollection(
			ArrayList<CustomerGroupDetails> customerGroupDetailsCollection) {
		this.customerGroupDetailsCollection = customerGroupDetailsCollection;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public HashMap<String, RepairToolInformation> getRepairToolInformationCollection() {
		return repairToolInformationCollection;
	}

	public void setRepairToolInformationCollection(
			HashMap<String, RepairToolInformation> repairToolInformationCollection) {
		this.repairToolInformationCollection = repairToolInformationCollection;
	}

	public HashMap<String, RepairMaterialInformation> getRepairMaterialInformationCollection() {
		return repairMaterialInformationCollection;
	}

	public void setRepairMaterialInformationCollection(
			HashMap<String, RepairMaterialInformation> repairMaterialInformationCollection) {
		this.repairMaterialInformationCollection = repairMaterialInformationCollection;
	}

	public BlockingQueue<RentalRequest> getRentalRequestCollection() {
		return rentalRequestCollection;
	}

	public void setRentalRequestCollection(
			BlockingQueue<RentalRequest> rentalRequestCollection) {
		this.rentalRequestCollection = rentalRequestCollection;
	}

	public ArrayList<DamageReport> getDamageReportCollection() {
		return damageReportCollection;
	}

	public void setDamageReportCollection(
			ArrayList<DamageReport> damageReportCollection) {
		this.damageReportCollection = damageReportCollection;
	}
	
	
	
	
	
	
	

}
