/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */

package spl.ass3;

import java.util.ArrayList;


public class CustomerGroupDetails {
	
	/** The rental request collection. */
	private ArrayList<RentalRequest> rentalRequestCollection;
	
	/** The customer collection. */
	private ArrayList<Customer> customerCollection;
	
	/** The group manager name. */
	private String groupManagerName;
	
	/**
	 * Instantiates a new customer group details.
	 *
	 * @param rentalRequestCollection
	 *            the rental request collection
	 * @param customerCollection
	 *            the customer collection
	 * @param groupManagerName
	 *            the group manager name
	 */
	public CustomerGroupDetails(ArrayList<RentalRequest> rentalRequestCollection,ArrayList<Customer> customerCollection, String groupManagerName) {
		this.rentalRequestCollection = rentalRequestCollection;
		this.customerCollection = customerCollection;
		this.groupManagerName = groupManagerName;
	}

	/**
	 * Adds the customer.
	 *
	 * @param other
	 *            the other
	 */
	public void addCustomer(Customer other){
		// TODO: Add customer class and addCustomer method implementation
	}
	
	/**
	 * Adds the rental request.
	 *
	 * @param other
	 *            the other
	 */
	public void addRentalRequest(RentalRequest other){
		// TODO: Add customer class and rentalReuquest method implementation
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerGroupDetails Details: \nrentalRequestCollection="
				+ rentalRequestCollection.toString() + "\ncustomerCollection="
				+ customerCollection.toString() + "\ngroupManagerName=" + groupManagerName;
	}

	/**
	 * Gets the rental request collection.
	 *
	 * @return the rental request collection
	 */
	public ArrayList<RentalRequest> getRentalRequestCollection() {
		return rentalRequestCollection;
	}

	/**
	 * Gets the customer collection.
	 *
	 * @return the customer collection
	 */
	public ArrayList<Customer> getCustomerCollection() {
		return customerCollection;
	}

	/**
	 * Gets the group manager name.
	 *
	 * @return the group manager name
	 */
	public String getGroupManagerName() {
		return groupManagerName;
	}

	/**
	 * Sets the rental request collection.
	 *
	 * @param rentalRequestCollection
	 *            the new rental request collection
	 */
	public void setRentalRequestCollection(
			ArrayList<RentalRequest> rentalRequestCollection) {
		this.rentalRequestCollection = rentalRequestCollection;
	}

	/**
	 * Sets the customer collection.
	 *
	 * @param customerCollection
	 *            the new customer collection
	 */
	public void setCustomerCollection(ArrayList<Customer> customerCollection) {
		this.customerCollection = customerCollection;
	}

	/**
	 * Sets the group manager name.
	 *
	 * @param groupManagerName
	 *            the new group manager name
	 */
	public void setGroupManagerName(String groupManagerName) {
		this.groupManagerName = groupManagerName;
	}
	
	
	
	
	
	
	
	
	

}
