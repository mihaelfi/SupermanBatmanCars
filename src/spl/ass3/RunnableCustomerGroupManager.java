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
	
	protected CustomerGroupDetails customerGroupDetails;
	protected ArrayList<RentalRequest> rentalRequestCollection;
	protected Managment managment;
	protected RentalRequest currentlyHandeledRentalRequest;
	protected Double damagePrecetnage = (double) 0;
	


	public RunnableCustomerGroupManager(CustomerGroupDetails customerGroupDetails, Managment managment) {
		this.customerGroupDetails = customerGroupDetails;
		this.rentalRequestCollection = customerGroupDetails.getRentalRequestCollection();
		this.managment = managment;
	}
	
	

	@Override
	public void run() {
		
		while (this.rentalRequestCollection.size() > 0){
			Driver.LOGGER.fine("The rental request collection size is: " +  this.rentalRequestCollection.size());
			synchronized (this.rentalRequestCollection.get(0)) {
				this.currentlyHandeledRentalRequest = this.rentalRequestCollection.get(0);
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
						Driver.LOGGER.info("The request "+ this.currentlyHandeledRentalRequest.getAssetType() + " ID: " + this.currentlyHandeledRentalRequest.getId() + " for "+ this.customerGroupDetails.getGroupManagerName() + " is not yet fufulied ... waiting ...");
						this.currentlyHandeledRentalRequest.wait(1000);
					}
					Driver.LOGGER.info("The request "+ this.currentlyHandeledRentalRequest.getAssetType() + " ID: " + this.currentlyHandeledRentalRequest.getId() + " for "+ this.customerGroupDetails.getGroupManagerName() + " is FUFULIED!!!, we can continue !!");
					
					Driver.LOGGER.info("\nThe Customer Group Manager " + this.customerGroupDetails.getGroupManagerName() + "got wakeup call on request t " + this.currentlyHandeledRentalRequest.getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			ExecutorService executor = Executors.newCachedThreadPool();
			CompletionService<Double> completionService = new ExecutorCompletionService<Double>(executor);
			
			for (int i = 0 ; i < this.customerGroupDetails.getCustomerCollection().size(); i++){
				Callable<Double> simulateStayInAsset = new CallableSimulateStayInAsset(currentlyHandeledRentalRequest, this.customerGroupDetails.getCustomerCollection().get(i));
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
			
			
			
			this.currentlyHandeledRentalRequest.getAsset().setStatusAvailable();
			Driver.LOGGER.info("The group manager *" + this.customerGroupDetails.getGroupManagerName() +"* is realeasing the asset " + this.currentlyHandeledRentalRequest.getAssetType()+ " Asset ID: " + this.currentlyHandeledRentalRequest.getId() + " and marking it as available.");
			DamageReport damageReport = new DamageReport(this.currentlyHandeledRentalRequest.getAsset(), this.damagePrecetnage);
			
			this.managment.addDamageReport(damageReport);
			Driver.LOGGER.info("The group manager is sending the damage report to managment");
		}
		
		Driver.LOGGER.info("The Runabble Custom Group Manager *" + this.customerGroupDetails.getGroupManagerName() +"* has exited ...");
		
	}
	
	
	
	

}
