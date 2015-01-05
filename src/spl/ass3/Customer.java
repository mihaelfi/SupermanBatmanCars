package spl.ass3;

public class Customer {
	
	protected String name;
	
	// 1=arbitrary 2=fixed 3=none
	protected String vandalismType;
	protected int minimumDamage;
	protected int maximumDamage;
	
	
	public Customer(String name, String vandalismType, int minimumDamage,int maximumDamage) {
	
		this.name = name;
		this.vandalismType = vandalismType;
		this.minimumDamage = minimumDamage;
		this.maximumDamage = maximumDamage;
	}


	@Override
	public String toString() {
		return "Customer name=" + name + "\nvandalismType=" + vandalismType
				+ "\nminimumDamage=" + minimumDamage + "\nmaximumDamage="
				+ maximumDamage ;
	}


	public String getName() {
		return name;
	}


	public String getVandalismType() {
		return vandalismType;
	}


	public int getMinimumDamage() {
		return minimumDamage;
	}


	public int getMaximumDamage() {
		return maximumDamage;
	}
	
	
	
	
	
	

}
