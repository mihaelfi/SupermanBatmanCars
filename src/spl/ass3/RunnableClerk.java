package spl.ass3;

import java.util.ArrayList;
import java.util.Queue;

public class RunnableClerk implements Runnable {
	
	private ClerkDetails clerkDetails;
	protected ArrayList<RentalRequest> rentalRequestCollection;
	private RentalRequest currentlyHandeledRequest = null;
	private Assets assets;
	private long totalSleepTime;
	protected int numberOfRentalRequests;
	
	
	
	public RunnableClerk(ClerkDetails clerkDetails , ArrayList<RentalRequest> rentalRequestCollection , int numberOfRentalRequests , Assets assets) {
		this.clerkDetails = clerkDetails;
		this.rentalRequestCollection = rentalRequestCollection;
		this.numberOfRentalRequests = this.rentalRequestCollection.size();
		this.assets = assets;
		this.totalSleepTime = 0;
	}
	
	public String toString(){
		return this.clerkDetails.toString() + " Number Of rental requests is : " + this.numberOfRentalRequests;
	}
	
	@Override
	public void run() {
		
		while(this.rentalRequestCollection.size() > 0){
			
			Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "Has started his shift.");
			
			synchronized (this.rentalRequestCollection) {
				if (this.rentalRequestCollection.size() > 0){
					this.currentlyHandeledRequest = this.rentalRequestCollection.get(0);
					this.rentalRequestCollection.remove(0);
					Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "Took the rental request " + this.currentlyHandeledRequest.toString());
				}
				
			}
			
			Asset avaliableAsset  = this.assets.findAvailableAset(this.currentlyHandeledRequest);
			Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "found available asset: \n" + avaliableAsset.toString());
			synchronized (avaliableAsset) {
				avaliableAsset.setStatusBooked();
				Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "marked asset as booked: \n" + avaliableAsset.toString());
			}
			
			//This simulates the walking to the asset process
			
			double distanceToAsset = this.clerkDetails.location.calculateDistance(avaliableAsset.location);
			long sleepTime = (long) (distanceToAsset*2000);
			totalSleepTime = totalSleepTime + sleepTime;
			Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "is going to the asset..." + "walking time is: " + sleepTime);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// storing the asset in the Rental request object
			
			Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "stores the asset in the rental request.");
			
			synchronized (this.currentlyHandeledRequest) {
				
				this.currentlyHandeledRequest.setAsset(avaliableAsset);
				
				this.currentlyHandeledRequest.setRequestStatusFufulied();
				Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "set request status to fulfilled.");
				// This will notify the Customer Manager that the request has been found.
				this.currentlyHandeledRequest.notifyAll();
			}
			
			
			Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "checks if the shift should be over.\n" );
			
			if (this.totalSleepTime > 8000){
				synchronized (this.clerkDetails) {
					try {
						Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "Has ended his shift. waiting for next shift" );
						this.totalSleepTime = 0;
						this.clerkDetails.wait();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	
	
	
	
	

}
