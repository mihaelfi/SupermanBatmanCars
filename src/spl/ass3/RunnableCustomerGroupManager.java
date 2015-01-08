package spl.ass3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableCustomerGroupManager implements Runnable{
	
	private CustomerGroupDetails customerGroupDetails;
	private ArrayList<RentalRequest> rentalRequestCollection;
	private Managment managment;
	private RentalRequest currentlyHandeledRentalRequest;
	private Double damagePrecetnage = (double) 0;
	private Assets assets;
	private Double profit;
	private ArrayList<RentalRequest> handeledrentalRequestCollection;
	


	public RunnableCustomerGroupManager(CustomerGroupDetails customerGroupDetails, Managment managment , Assets assets , Double profit , ArrayList<RentalRequest> handeledrentalRequestCollection) {
		this.customerGroupDetails = customerGroupDetails;
		this.rentalRequestCollection = customerGroupDetails.getRentalRequestCollection();
		this.managment = managment;
		this.assets = assets;
		this.profit = profit;
		this.handeledrentalRequestCollection = handeledrentalRequestCollection;
	}
	
	private void applyDamageToAssetContents(){
		for (int i = 0 ; i < this.currentlyHandeledRentalRequest.getAsset().getAssetContents().size() ; i++){
			synchronized (this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i)) {
				this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).applyDamage(this.damagePrecetnage);
				
			}
			
		}
	}
	
	private double calculateCostOfStay(){
		return (this.currentlyHandeledRentalRequest.getAsset().getCostPerNight())*(this.currentlyHandeledRentalRequest.getDurationOfStay())*(this.customerGroupDetails.getCustomerCollection().size());
	}
	
	

	@Override
	public void run() {
		
		Driver.LOGGER.warning("Customer manager *"+ this.customerGroupDetails.getGroupManagerName() + "* Has started working.");
		
		while (this.rentalRequestCollection.size() > 0){
			Driver.LOGGER.fine("The rental request collection size is: " +  this.rentalRequestCollection.size());
			synchronized (this.rentalRequestCollection.get(0)) {
				this.currentlyHandeledRentalRequest = this.rentalRequestCollection.get(0);
				this.handeledrentalRequestCollection.add(this.rentalRequestCollection.get(0));
				this.managment.addRentalRequestToBlockingQueue(this.currentlyHandeledRentalRequest);
				Driver.LOGGER.info("The Customer Group Manager " + this.customerGroupDetails.getGroupManagerName() +
						"\n Submitted the Rental Request" + this.currentlyHandeledRentalRequest.toString());
				this.rentalRequestCollection.remove(0);
			}
			
			if (this.rentalRequestCollection.size() > 0){
				Driver.LOGGER.fine("\nNow The first Rental Request is:" + this.rentalRequestCollection.get(0).toString());
			}else{
				Driver.LOGGER.info("\nNumber of Rentel Request for this group Has reached 0 , the Customer Group manager" +  this.customerGroupDetails.getGroupManagerName() +"Should exit now.");
			}
			
			
			synchronized (this.currentlyHandeledRentalRequest) {
				try {
					Driver.LOGGER.info("\nThe Customer Group Manager " + this.customerGroupDetails.getGroupManagerName() + " is now waiting for someone to find an asset for his RentalRequest ");
					
					// guraded block
					while (!(this.currentlyHandeledRentalRequest.getRequestStatus().equals("FUFULIED"))){
						
						this.currentlyHandeledRentalRequest.wait();
						Driver.LOGGER.warning("Group manager *"+ this.customerGroupDetails.getGroupManagerName() +"* got notification from a clerk that his request was fufulied, we can stay in the asset now.");
					}
					Driver.LOGGER.info("The request "+ this.currentlyHandeledRentalRequest.getAssetType() + " ID: " + this.currentlyHandeledRentalRequest.getId() + " for "+ this.customerGroupDetails.getGroupManagerName() + " is FUFULIED!!!, we can continue !!");
					
					Driver.LOGGER.info("\nThe Customer Group Manager " + this.customerGroupDetails.getGroupManagerName() + "got wakeup call on request t " + this.currentlyHandeledRentalRequest.getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			ExecutorService executorForCustomers = Executors.newCachedThreadPool();
			CompletionService<Double> completionService = new ExecutorCompletionService<Double>(executorForCustomers);
			
			Driver.LOGGER.severe("The number of customer treads in group " + this.customerGroupDetails.getGroupManagerName() + " that is: " + this.customerGroupDetails.getCustomerCollection().size());
			
			for (int i = 0 ; i < this.customerGroupDetails.getCustomerCollection().size(); i++){
				
				
				Callable<Double> simulateStayInAsset = new CallableSimulateStayInAsset(currentlyHandeledRentalRequest, this.customerGroupDetails.getCustomerCollection().get(i),this.currentlyHandeledRentalRequest.getAsset());
				completionService.submit(simulateStayInAsset);
				
			}
			
			this.damagePrecetnage = 0.0;
			
			for (int i = 0 ; i < this.customerGroupDetails.getCustomerCollection().size(); ++i){
				try {
					this.damagePrecetnage = this.damagePrecetnage + completionService.take().get();
					Driver.LOGGER.fine("Generated Damage By Staying in Asset is now: " + this.damagePrecetnage);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			executorForCustomers.shutdown();
			
			if (this.damagePrecetnage > 100.0){
				this.damagePrecetnage = 100.0;
			}
			
//			this.applyDamageToAssetContents();
			
			this.currentlyHandeledRentalRequest.getAsset().setStatusAvailable();
			
			synchronized (this.profit) {
				this.profit = this.profit + calculateCostOfStay();
			}
			
			synchronized (this.assets) {
				Driver.LOGGER.warning("Customer group *" + this.customerGroupDetails.getGroupManagerName()+ "* has finished it's stay in Asset:" + this.currentlyHandeledRentalRequest.getAsset().getName());
				this.assets.notifyAll();
			}
			
			Driver.LOGGER.info("The group manager *" + this.customerGroupDetails.getGroupManagerName() +"* is realeasing the asset " + this.currentlyHandeledRentalRequest.getAssetType()+ " Asset ID: " + this.currentlyHandeledRentalRequest.getId() + " and marking it as available.");
			DamageReport damageReport = new DamageReport(this.currentlyHandeledRentalRequest.getAsset(), this.damagePrecetnage, this.customerGroupDetails.getGroupManagerName());
			
			this.managment.addDamageReport(damageReport);
			Driver.LOGGER.severe("The group manager of group: " + this.customerGroupDetails.getGroupManagerName() +" is sending the damage report to managment About Asset" + this.currentlyHandeledRentalRequest.getAsset().getName());
		}
		
		Driver.LOGGER.warning("The Runabble Custom Group Manager *" + this.customerGroupDetails.getGroupManagerName() +"* has exited ...");
		
	}
	
	
	
	

}
