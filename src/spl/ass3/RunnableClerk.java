package spl.ass3;

import java.util.ArrayList;

public class RunnableClerk implements Runnable {
	
	private ClerkDetails clerkDetails;
	protected ArrayList<RentalRequest> rentalRequestCollection;
	protected int numberOfRentalRequests;
	
	
	
	public RunnableClerk(ClerkDetails clerkDetails , ArrayList<RentalRequest> rentalRequestCollection , int numberOfRentalRequests) {
		this.clerkDetails = clerkDetails;
		this.rentalRequestCollection = rentalRequestCollection;
		this.numberOfRentalRequests = numberOfRentalRequests;
	}
	
	public String toString(){
		return this.clerkDetails.toString() + " Number Of rental requests is : " + this.numberOfRentalRequests;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
