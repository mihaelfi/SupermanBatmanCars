package spl.ass3;

import java.util.Queue;

public class RunnableClerk implements Runnable {
	
	private ClerkDetails clerkDetails;
	protected Queue<RentalRequest> rentalRequestCollection;
	private RentalRequest currentlyHandeledRequest = null;
	private Assets assets;
	private long totalSleepTime;
	protected int numberOfRentalRequests;
	
	
	
	public RunnableClerk(ClerkDetails clerkDetails , Queue<RentalRequest> rentalRequestCollection , int numberOfRentalRequests , Assets assets) {
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
		
		while(true){
			
			synchronized (this.rentalRequestCollection) {
				if (this.rentalRequestCollection.size() > 0){
					this.currentlyHandeledRequest = this.rentalRequestCollection.poll();
				}
				
			}
			
			Asset avaliableAsset  = this.assets.findAvailableAset(this.currentlyHandeledRequest);
			
			synchronized (avaliableAsset) {
				avaliableAsset.setStatus("BOOKED");
			}
			
			//This simulates the walking to the asset process
			
			double distanceToAsset = this.clerkDetails.location.calculateDistance(avaliableAsset.location);
			long sleepTime = (long) (distanceToAsset*2000);
			totalSleepTime = totalSleepTime + sleepTime;
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// storing the asset in the Rental request object
			
			this.currentlyHandeledRequest.setAsset(avaliableAsset);
			
			
			
			
			
			
			
		}
		
		
	}
	
	
	
	
	
	

}
