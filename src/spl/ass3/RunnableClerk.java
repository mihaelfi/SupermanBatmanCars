package spl.ass3;

import java.util.ArrayList;
import java.util.Queue;

public class RunnableClerk implements Runnable {
	
	private ClerkDetails clerkDetails;
	protected Queue<RentalRequest> rentalRequestCollection;
	
	protected int numberOfRentalRequests;
	
	
	
	public RunnableClerk(ClerkDetails clerkDetails , Queue<RentalRequest> rentalRequestCollection , int numberOfRentalRequests) {
		this.clerkDetails = clerkDetails;
		this.rentalRequestCollection = rentalRequestCollection;
		this.numberOfRentalRequests = this.rentalRequestCollection.size();
	}
	
	public String toString(){
		return this.clerkDetails.toString() + " Number Of rental requests is : " + this.numberOfRentalRequests;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			synchronized (this.rentalRequestCollection) {
				
			}
			
		}
		
		
	}
	
	
	
	
	
	

}
