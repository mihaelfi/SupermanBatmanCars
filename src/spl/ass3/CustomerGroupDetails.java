/**
 * 
 */
package spl.ass3;

import java.util.ArrayList;

/**
 * @author Michael Fildstein Id:309161594
 *
 */
public class CustomerGroupDetails {
	
	protected ArrayList<RentalRequest> rentalRequestCollection;
	protected ArrayList<Customer> customerCollection;
	protected String groupManagerName;
	
	public CustomerGroupDetails(ArrayList<RentalRequest> rentalRequestCollection,ArrayList<Customer> customerCollection, String groupManagerName) {
		this.rentalRequestCollection = rentalRequestCollection;
		this.customerCollection = customerCollection;
		this.groupManagerName = groupManagerName;
	}

	public void addCustomer(Customer other){
		// TODO: Add customer class and addCustomer method implementation
	}
	
	public void addRentalRequest(RentalRequest other){
		// TODO: Add customer class and rentalReuquest method implementation
	}

	@Override
	public String toString() {
		return "CustomerGroupDetails Details: \nrentalRequestCollection="
				+ rentalRequestCollection.toString() + "\ncustomerCollection="
				+ customerCollection.toString() + "\ngroupManagerName=" + groupManagerName;
	}
	
	
	
	
	

}
