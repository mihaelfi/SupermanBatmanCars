/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

/**
 * The Class Customer.
 */
public class Customer {
	
	/** The name. */
	protected String name;
	
	// 1=arbitrary 2=fixed 3=none
	/** The vandalism type. */
	protected String vandalismType;
	
	/** The minimum damage. */
	protected int minimumDamage;
	
	/** The maximum damage. */
	protected int maximumDamage;
	
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param name
	 *            the name
	 * @param vandalismType
	 *            the vandalism type (can be: 1=arbitrary 2=fixed 3=none)
	 * @param minimumDamage
	 *            the minimum damage
	 * @param maximumDamage
	 *            the maximum damage
	 */
	public Customer(String name, String vandalismType, int minimumDamage,int maximumDamage) {
	
		this.name = name;
		this.vandalismType = vandalismType;
		this.minimumDamage = minimumDamage;
		this.maximumDamage = maximumDamage;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer name=" + name + "\nvandalismType=" + vandalismType
				+ "\nminimumDamage=" + minimumDamage + "\nmaximumDamage="
				+ maximumDamage ;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets the vandalism type.
	 *
	 * @return the vandalism type
	 */
	public String getVandalismType() {
		return vandalismType;
	}


	/**
	 * Gets the minimum damage.
	 *
	 * @return the minimum damage
	 */
	public int getMinimumDamage() {
		return minimumDamage;
	}


	/**
	 * Gets the maximum damage.
	 *
	 * @return the maximum damage
	 */
	public int getMaximumDamage() {
		return maximumDamage;
	}
	
	
	
	
	
	

}
