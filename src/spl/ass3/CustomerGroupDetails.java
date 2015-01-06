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
	
	private ArrayList<RentalRequest> rentalRequestCollection;
	private ArrayList<Customer> customerCollection;
	private String groupManagerName;
	
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

	public ArrayList<RentalRequest> getRentalRequestCollection() {
		return rentalRequestCollection;
	}

	public ArrayList<Customer> getCustomerCollection() {
		return customerCollection;
	}

	public String getGroupManagerName() {
		return groupManagerName;
	}

	public void setRentalRequestCollection(
			ArrayList<RentalRequest> rentalRequestCollection) {
		this.rentalRequestCollection = rentalRequestCollection;
	}

	public void setCustomerCollection(ArrayList<Customer> customerCollection) {
		this.customerCollection = customerCollection;
	}

	public void setGroupManagerName(String groupManagerName) {
		this.groupManagerName = groupManagerName;
	}
	
	
	
	
	
	
	
	
	

}
