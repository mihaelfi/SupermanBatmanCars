package spl.ass3;

import java.util.ArrayList;

public class RunnableCustomerGroupManager implements Runnable{
	
	protected CustomerGroupDetails customerGroupDetails;
	protected ArrayList<RentalRequest> rentalRequestCollection;
	protected Managment managment;
	protected RentalRequest currentlyHandeledRentalRequest;
	


	public RunnableCustomerGroupManager(CustomerGroupDetails customerGroupDetails, Managment managment) {
		this.customerGroupDetails = customerGroupDetails;
		this.rentalRequestCollection = customerGroupDetails.getRentalRequestCollection();
		this.managment = managment;
	}




	@Override
	public void run() {
		
		while (this.rentalRequestCollection.size() > 0){
			
			this.currentlyHandeledRentalRequest = this.rentalRequestCollection.get(0);
			this.managment.addRentalRequestToBlockingQueue(this.currentlyHandeledRentalRequest);
			Driver.LOGGER.info("The Customer Group Manager " + this.customerGroupDetails.groupManagerName +
					"\n Submitted the Rental Request" + this.currentlyHandeledRentalRequest.toString());
			this.rentalRequestCollection.remove(0);
			if (this.rentalRequestCollection.size() > 0){
				Driver.LOGGER.info("\nNow The first Rental Request is:" + this.rentalRequestCollection.get(0).toString());
			}else{
				Driver.LOGGER.info("\nNumber of Rentel Request for this group Has reached 0 , the Customer Group manager" +  this.customerGroupDetails.groupManagerName +"Should exit now.");
			}
			
			synchronized (this.currentlyHandeledRentalRequest) {
				try {
					Driver.LOGGER.info("\nThe Customer Group Manager " + this.customerGroupDetails.groupManagerName + " is now waiting for someone to find an asset for his RentalRequest ");
					this.currentlyHandeledRentalRequest.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			// TODO Continue do Customer Manager Work cycle.
			
			
			
			
		}
		
	}
	
	
	
	

}
