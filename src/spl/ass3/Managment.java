package spl.ass3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Managment {
	
	private 	ArrayList<ClerkDetails> clerkDetailsCollection;
	private	    ArrayList<CustomerGroupDetails> customerGroupDetailsCollection;
	private 	Assets assets;
	private    	Warehouse warehouse;
	private     HashMap  <String, RepairToolInformation> repairToolInformationCollection;
	private     HashMap  <String, RepairMaterialInformation> repairMaterialInformationCollection;
	private 	BlockingQueue<RentalRequest> rentalRequestCollection;
	private 	ArrayList<DamageReport> damageReportCollection;
	private     CyclicBarrier clerksFinishedShift;
	private     int NUMBER_OF_MAINTENANCE_PERSONS;
	private 	BlockingQueue<Asset> assetsForRepair;
	
	
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		this.clerkDetailsCollection = new ArrayList<ClerkDetails>();
		this.repairMaterialInformationCollection = new HashMap  <String, RepairMaterialInformation>();
		this.repairToolInformationCollection = new HashMap  <String, RepairToolInformation>();
		this.customerGroupDetailsCollection = new ArrayList<CustomerGroupDetails>();
		this.rentalRequestCollection = new ArrayBlockingQueue<RentalRequest>(10, true);
		this.damageReportCollection = new ArrayList<DamageReport>();
//		this.clerksFinishedShift = new CyclicBarrier(this.clerkDetailsCollection.size() + 1);
		
		
	}
	
public void putDamagedAssetsInRepairQueue(){
	for (int i = 0 ; i < this.damageReportCollection.size() ; i++){
		if (100.0 - this.damageReportCollection.get(i).getDamagePrecentage() < 65.0 ){
			try {
				this.assetsForRepair.put(this.damageReportCollection.get(i).getAsset());
				this.damageReportCollection.get(i).getAsset().setStatusUnavailable();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
	
public void setNumberOfMaintencePersons(int numOfMaintencePersons){
	this.NUMBER_OF_MAINTENANCE_PERSONS = numOfMaintencePersons;
}

public int getNumberOfMaintencePerons(){
	return this.NUMBER_OF_MAINTENANCE_PERSONS;
}


	
private AtomicInteger totalNumberOfRentalRequests(){
		
		int numberOfRentalRequests = 0;
		
		for ( int i = 0 ; i < customerGroupDetailsCollection.size() ; i++){
			
			for ( int j = 0 ; j < this.customerGroupDetailsCollection.get(i).getRentalRequestCollection().size(); j++){
				numberOfRentalRequests++;
			}
		}
		
		AtomicInteger ans = new AtomicInteger(numberOfRentalRequests);
		
		return ans;
}

	
	public void startClerks(){
		Driver.LOGGER.info("Totall Number of rental requests = " + totalNumberOfRentalRequests());
		
		AtomicInteger numberOfRentalRequests = totalNumberOfRentalRequests();
		
//		this.rentalRequestCollection.add(this.customerGroupDetailsCollection.get(0).getRentalRequestCollection().get(0));
		
		ExecutorService clerkExecutor = Executors.newFixedThreadPool(this.clerkDetailsCollection.size());
		
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i ++){
			
			clerkExecutor.submit(new RunnableClerk(this.clerkDetailsCollection.get(i), rentalRequestCollection, numberOfRentalRequests, assets , this.clerksFinishedShift));
			
		}
		
		
		clerkExecutor.shutdown();
		
		
//		executor.awaitTermination(200, TimeUnit)
		
		
		
		
	}
	
	public void newShiftForClearks(){
		try {
			Driver.LOGGER.info("Number of clerks how ended their shift is: " + this.clerksFinishedShift.getNumberWaiting());
			Driver.LOGGER.info("Waiting for clerks to finish their shift");
			this.clerksFinishedShift.await();
			Driver.LOGGER.info("Clerks shift finished");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.clerksFinishedShift.reset();
//		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i++){
//			this.clerkDetailsCollection.get(i).notify();
//		}
	}
	
	
	
	public void startGroupManager(){
		
		ExecutorService groupManagerExecutor = Executors.newFixedThreadPool(this.customerGroupDetailsCollection.size());
		
		for (int i = 0 ; i < this.customerGroupDetailsCollection.size() ; i ++){
			
			groupManagerExecutor.submit(new RunnableCustomerGroupManager(this.customerGroupDetailsCollection.get(i), this,assets));
			
		}
		
		
		groupManagerExecutor.shutdown();
		
		
		
	}
	
	public void addClerkDetails(ClerkDetails clerkDetailsToAdd){
		this.clerkDetailsCollection.add(clerkDetailsToAdd);
		this.clerksFinishedShift = new CyclicBarrier(this.clerkDetailsCollection.size() + 1);
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
