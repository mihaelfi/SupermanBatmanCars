package spl.ass3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class RunnableClerk implements Runnable {
	
	private ClerkDetails clerkDetails;
	private BlockingQueue<RentalRequest> rentalRequestCollection;
	private RentalRequest currentlyHandeledRequest = null;
	private Assets assets;
	private long totalSleepTime;
	private AtomicInteger numberOfRentalRequestsYetHandeled;
	private final RentalRequest POISON_PILL = new RentalRequest(-666, "POISON_PILL", 666, 666, "INCOMPLETE");
	private CyclicBarrier clerksFinishedShift;
	
	
	public RunnableClerk(ClerkDetails clerkDetails , BlockingQueue<RentalRequest> rentalRequestCollection ,
			AtomicInteger numberOfRentalRequestsYetHandeled , Assets assets, CyclicBarrier clerksFinishedShift) {
		this.clerkDetails = clerkDetails;
		this.rentalRequestCollection = rentalRequestCollection;
		this.numberOfRentalRequestsYetHandeled  = numberOfRentalRequestsYetHandeled;
		this.assets = assets;
		this.totalSleepTime = 0;
		this.clerksFinishedShift = clerksFinishedShift;
	}
	
	public String toString(){
		return this.clerkDetails.toString() + " Number Of rental requests is : " + this.numberOfRentalRequestsYetHandeled;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			if (this.numberOfRentalRequestsYetHandeled.get() == 0){
				this.rentalRequestCollection.add(POISON_PILL);
			}
			
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Has started his shift.");
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Totall sleep time is: " + this.totalSleepTime);
//			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Has " + this.numberOfRentalRequestsYetHandeled +" much unhandeld requests.");
			
			try {
				this.currentlyHandeledRequest = this.rentalRequestCollection.take();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Has succesfully pulled a request form the queue");
			if (this.currentlyHandeledRequest.getAssetType().equals("POISON_PILL")){
				this.rentalRequestCollection.add(this.currentlyHandeledRequest);
				Driver.LOGGER.info("Clerk *" + this.clerkDetails.getName() + "* Has finished his work! Forever! and Ever! He will be missed !");
				break;
			}

			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Took the rental request " + this.currentlyHandeledRequest.toString());

				
			
			Asset avaliableAsset  = this.assets.findAvailableAset(this.currentlyHandeledRequest);
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* found available asset: \n" + avaliableAsset.toString());
//			synchronized (avaliableAsset) {
//				avaliableAsset.setStatusBooked();
//				Driver.LOGGER.info("The clerk " + this.clerkDetails.getName() + "marked asset as booked: \n" + avaliableAsset.toString());
//			}
			
			//This simulates the walking to the asset process
			
			double distanceToAsset = this.clerkDetails.location.calculateDistance(avaliableAsset.getLocation());
			long sleepTime = (long) (distanceToAsset*2000);
			totalSleepTime = totalSleepTime + sleepTime;
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* is going to the asset..." + "walking time is: " + sleepTime);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// storing the asset in the Rental request object
			
			
			
			synchronized (this.currentlyHandeledRequest) {
				
				
				this.currentlyHandeledRequest.setAsset(avaliableAsset);
				Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* stores the asset" + avaliableAsset.getName() +" Of the Type " + avaliableAsset.getType() +  " in the rental request.");
				this.currentlyHandeledRequest.setRequestStatusFufulied();
				Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* set request status to fulfilled.");
				// This will notify the Customer Manager that the request has been found.
				Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* is notifying all the Managers waiting on request Id: " + this.currentlyHandeledRequest.getId());
//				this.currentlyHandeledRequest.notifyAll();
				
			}
			
			this.numberOfRentalRequestsYetHandeled.decrementAndGet();
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Has " + this.numberOfRentalRequestsYetHandeled +" much unhandeld requests.");
			
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* checks if the shift should be over.\n" );
			
			if (this.totalSleepTime > 8000){
				synchronized (this.clerkDetails) {
					try {
						Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Has ended his shift. waiting for next shift \n*************************************************************\n*******************************************************" );
						this.totalSleepTime = 0;
						Driver.LOGGER.info("Number of clerks how ended their shift is: " + this.clerksFinishedShift.getNumberWaiting());
						this.clerksFinishedShift.await();
//						this.clerkDetails.wait();
						Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* Waking up for new shift! \n*************************************************************\n*******************************************************" );
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			Driver.LOGGER.info("The clerk *" + this.clerkDetails.getName() + "* shift isn't over..."+"His total sleep time is :" + this.totalSleepTime +" Starting New shift" );
			
		}
		
		
	}
	
	
	
	
	
	

}
