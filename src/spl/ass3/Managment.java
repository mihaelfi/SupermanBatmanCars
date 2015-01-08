package spl.ass3;

import java.io.IOException;
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
	private		AtomicInteger totalNumberOfRentalRequests;
	private 	BlockingQueue<Asset> assetsForRepair;
	private     Object maintenceFinished;
	private final Asset POISON_PILL = new Asset("POISON_PILL", "poison", null, null, "poison", 66.6, 666);
	private 	ArrayList<Asset> assetsAwaitingRepair = new ArrayList<Asset>();
	private 	Double profit = new Double(0.0);
	private     Statistics statistics = new Statistics(this.profit);
	
	
	
	
	Managment(){
		this.warehouse = new Warehouse();
		this.clerkDetailsCollection = new ArrayList<ClerkDetails>();
		this.repairMaterialInformationCollection = new HashMap  <String, RepairMaterialInformation>();
		this.repairToolInformationCollection = new HashMap  <String, RepairToolInformation>();
		this.customerGroupDetailsCollection = new ArrayList<CustomerGroupDetails>();
		this.rentalRequestCollection = new ArrayBlockingQueue<RentalRequest>(10, true);
		this.damageReportCollection = new ArrayList<DamageReport>();
		this.assetsForRepair = new ArrayBlockingQueue<Asset>(10, true);
//		this.clerksFinishedShift = new CyclicBarrier(this.clerkDetailsCollection.size() + 1);
		
		
	}
	

private boolean areAllAsetsFixed(){
	boolean ans = true;
//	ArrayList<Asset> assetCollection = this.assets.getAssetCollection();
	
	
	for (int i = 0 ; i < this.assetsAwaitingRepair.size() && ans ; i++){
		if (assetsAwaitingRepair.get(i).isBroken() == true){
			ans = false;
		}
	}
	
	
	return ans;
}

private void waitForMaintenceToFinish(){
		synchronized (this.repairMaterialInformationCollection) {
			try {
				while(!areAllAsetsFixed()){
					Driver.LOGGER.info("All assets are not yet fixed ... waiting for maintence to finish");
					this.repairMaterialInformationCollection.wait();
				}
				
			} catch (InterruptedException e) {
				System.out.println("Fucking Exception !!");
				e.printStackTrace();
			}
		}
}

	
public void startSimulation(){
	

	this.startClerks();
	this.startGroupManager();
	this.startMaintencesWorkers();

	while( this.totalNumberOfRentalRequests.get() > 0){
		
		Driver.LOGGER.warning("Total number of rental requests is: " + this.totalNumberOfRentalRequests);
		Driver.LOGGER.warning("************************************************************************");
		this.waitForClerksToFinishShift();
		
		Driver.LOGGER.warning("Clerks Finished Shift.");
		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("\n\n\n\n\n\n\n\n\n");
		
		
		Driver.LOGGER.warning("Printing Assets before maintences : \n" + this.assets.toString());
		Driver.LOGGER.warning("************************************************************************");

		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("Printing Damage Report"  + this.damageReportCollection.toString());
		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("Pushing damaged Assets into queue");
		Driver.LOGGER.warning("************************************************************************");
		this.putDamagedAssetsInRepairQueue();
		
		
		
		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("Waiting For maintence to finish!");
		Driver.LOGGER.warning("************************************************************************");
		this.waitForMaintenceToFinish();
		
		Driver.LOGGER.warning("Maintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\n"
				+ "Maintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\n"
				+ "Maintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\n"
				+ "Maintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\n"
				+ "Maintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\nMaintence Finished, Clerks should resume !\n");
		
		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("Printing Assets *After* maintences : \n" + this.assets.toString());
		Driver.LOGGER.warning("************************************************************************");
		
		
		Driver.LOGGER.warning("************************************************************************");
		Driver.LOGGER.warning("Clerks Starting New Shift");
		Driver.LOGGER.warning("************************************************************************");
		this.newShiftForClerks();

		
	}
	
	// Kill maintains people
	try {
		this.assetsForRepair.put(POISON_PILL);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	Driver.LOGGER.severe("Simulation should shut down...");
	this.statistics.setRepairMaterialUsedCollection(this.warehouse.getRepairMaterialsUsed());
	this.statistics.setRepairToolUsedCollection(this.warehouse.getRepairToolsUsed());
	
	Driver.LOGGER.severe(this.statistics.toString());
	
}


	
public void startMaintencesWorkers(){
	
	ExecutorService maintenceExecutor = Executors.newFixedThreadPool(this.NUMBER_OF_MAINTENANCE_PERSONS);
	
//	ExecutorService maintenceExecutor = Executors.newFixedThreadPool(1);
	
	for (int i = 0 ; i < this.NUMBER_OF_MAINTENANCE_PERSONS ; i ++){
		
		maintenceExecutor.submit(new RunnableMaintenanceRequest(this.repairToolInformationCollection, this.repairMaterialInformationCollection, null, this.warehouse, this.assetsForRepair, "**RepairMan "+ i+"*"));
		
	}
	
	
	maintenceExecutor.shutdown();
	
}
	
public void putDamagedAssetsInRepairQueue(){
	Driver.LOGGER.warning("Damage report before appliyg damage " + this.damageReportCollection.toString());
	int numberOfAssetsPutForRepair = 0;
	
	for (int i = 0 ; i < this.damageReportCollection.size() ; i ++){
		this.damageReportCollection.get(i).applyDamage();
	}
	
	Driver.LOGGER.warning("Damage report after appliyg damage " + this.damageReportCollection.toString());
	
	for (int i = 0 ; i < this.assets.getAssetCollection().size() ; i++){
		synchronized (this.assets.getAssetCollection().get(i)) {
			if (this.assets.getAssetCollection().get(i).getAssetContents().get(0).getHealth() < 65.0 && this.assets.getAssetCollection().get(i).getStatus().equals("AVAILABLE")){
				try {
					this.assetsForRepair.put(this.assets.getAssetCollection().get(i));
					numberOfAssetsPutForRepair++;
				} catch (InterruptedException e) {
					System.out.println("Got Excepition!");
					Driver.LOGGER.severe("Got Exception !");
					e.printStackTrace();
				}
			}
		}
	}
	
	Driver.LOGGER.warning("Number of assets put for repair is : " + numberOfAssetsPutForRepair);
	
	this.damageReportCollection.clear();
	
	
//	int numOfAssetsSentToRepair = 0;
//	ArrayList<DamageReport> tempDamageReportCollection = new ArrayList<DamageReport>();
//	this.assetsAwaitingRepair.clear();
//	
//	for (int i = 0 ; i < this.damageReportCollection.size() ; i++){
//		synchronized (this.damageReportCollection.get(i).getAsset()) {
//			if (this.damageReportCollection.get(i).getAsset().getAssetContents().get(0).getHealth() < 65.0 ){
//				if (this.damageReportCollection.get(i).getAsset().getStatus().equals("AVAILABLE")){
//					
//					
//					Driver.LOGGER.severe("Found enough damaged damage report: \n" + this.damageReportCollection.get(i).toString() );
//					Driver.LOGGER.severe("Asset contents health is supposed to be: " + (100.0 - this.damageReportCollection.get(i).getDamagePrecentage()));
//					Driver.LOGGER.severe("Found damaged asset: the damaged asset is: \n" + this.damageReportCollection.get(i).getAsset().toString() );
//					
//					
//					Driver.LOGGER.info("The Asset " + this.damageReportCollection.get(i).getAsset().getName() + " is Damaged and need to be repaired. sending maintence people in ...");
//					try {
//						this.damageReportCollection.get(i).getAsset().setBroken();
//						this.assetsForRepair.put(this.damageReportCollection.get(i).getAsset());
//						Driver.LOGGER.warning("Managment has put the asset for repair " + this.damageReportCollection.get(i).getAsset().toString());
//						assetsAwaitingRepair.add(this.damageReportCollection.get(i).getAsset());
//						numOfAssetsSentToRepair++;
//						this.damageReportCollection.get(i).getAsset().setStatusUnavailable();
//					} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}else{
//					tempDamageReportCollection.add(this.damageReportCollection.get(i));
//				}
//				
//				
//			}
//			
//		}
//		
//	}
//	Driver.LOGGER.severe("The number of Damage reports is: " + this.damageReportCollection.size());
//	Driver.LOGGER.severe("number of ingored reportss is: " + tempDamageReportCollection.size());
//	this.damageReportCollection.clear();
//	for (int k = 0 ; k < tempDamageReportCollection.size(); k ++){
//		this.damageReportCollection.add(tempDamageReportCollection.get(k));
//	}
//	
//	Driver.LOGGER.warning("The number of broken Assets is: "  + numOfAssetsSentToRepair);
}
	
public void setNumberOfMaintencePersons(int numOfMaintencePersons){
	this.NUMBER_OF_MAINTENANCE_PERSONS = numOfMaintencePersons;
}

public void setTotalNumberOfRentalRequestsInt(int totalNumberOfRentalRequestsInt){
	this.totalNumberOfRentalRequests = new AtomicInteger(totalNumberOfRentalRequestsInt);
}

public int getNumberOfMaintencePerons(){
	return this.NUMBER_OF_MAINTENANCE_PERSONS;
}


	
//private AtomicInteger totalNumberOfRentalRequests(){
//		
//		int numberOfRentalRequests = 0;
//		
//		for ( int i = 0 ; i < customerGroupDetailsCollection.size() ; i++){
//			
//			for ( int j = 0 ; j < this.customerGroupDetailsCollection.get(i).getRentalRequestCollection().size(); j++){
//				numberOfRentalRequests++;
//			}
//		}
//		
//		AtomicInteger ans = new AtomicInteger(numberOfRentalRequests);
//		
//		return ans;
//}

	
	public void startClerks(){
		Driver.LOGGER.info("Totall Number of rental requests = " + this.totalNumberOfRentalRequests);
		
//		AtomicInteger numberOfRentalRequests = totalNumberOfRentalRequests();
		
//		this.rentalRequestCollection.add(this.customerGroupDetailsCollection.get(0).getRentalRequestCollection().get(0));
		
		ExecutorService clerkExecutor = Executors.newFixedThreadPool(this.clerkDetailsCollection.size());
		
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i ++){
			
			clerkExecutor.submit(new RunnableClerk(this.clerkDetailsCollection.get(i), rentalRequestCollection, this.totalNumberOfRentalRequests, assets , this.clerksFinishedShift));
			
		}
		
		
		clerkExecutor.shutdown();
		
		
//		executor.awaitTermination(200, TimeUnit)
		
	}
	
	public void waitForClerksToFinishShift(){
		Driver.LOGGER.warning("Managment is waiting for clerks to finish thier shift!");
		try {
			this.clerksFinishedShift.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Driver.LOGGER.warning("Managment now knows that all clerks finished their shift, proceeding to maintance.");
		this.clerksFinishedShift.reset();
		
	}
	
	public void newShiftForClerks(){
		
		for (int i = 0 ; i < this.clerkDetailsCollection.size() ; i++){
			synchronized (clerkDetailsCollection.get(i)) {
				this.clerkDetailsCollection.get(i).notifyAll();
			}
			Driver.LOGGER.info("Notifying Clerk *" + this.clerkDetailsCollection.get(i).getName() + "* To get up from his lazy ass and go back to work!");
		}
	}
	
	
	
	public void startGroupManager(){
		
		ExecutorService groupManagerExecutor = Executors.newFixedThreadPool(this.customerGroupDetailsCollection.size());
		
		for (int i = 0 ; i < this.customerGroupDetailsCollection.size() ; i ++){
			
			groupManagerExecutor.submit(new RunnableCustomerGroupManager(this.customerGroupDetailsCollection.get(i), this, assets , this.profit,statistics.getRentalRequestCollection()));
			
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
//		boolean damageReportIsNotAlreadyInCollection = true;
		synchronized (this.damageReportCollection) {
//			for (int i = 0 ; i < this.damageReportCollection.size() && damageReportIsNotAlreadyInCollection; i++){
//				if (this.damageReportCollection.get(i).getAsset().getName().equals(damageReportToAdd.getAsset().getName())){
//					double tempDamage = this.damageReportCollection.get(i).getDamagePrecentage();
//					this.damageReportCollection.get(i).setDamagePrecentage(tempDamage - damageReportToAdd.getDamagePrecentage());
//					damageReportIsNotAlreadyInCollection = false;
//				}
//			}
//			if (damageReportIsNotAlreadyInCollection){
				this.damageReportCollection.add(damageReportToAdd);
//			}
			
		}
		
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
