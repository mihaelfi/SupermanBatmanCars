/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;



/**
 * The Class AssetContent.
 */
public class AssetContent implements Comparable<AssetContent> {
	
	/** The name. */
	private String name;
	
	/** The health. */
	private double health;
	
	/** The repair cost multiplier. */
	private double repairCostMultiplier;
	
	/** The repair cost time. */
	private double repairCostTime = -1;
	
	/** The max health. */
	private final double MAX_HEALTH = 100;
	
	
	/**
	 * Instantiates a new asset content.
	 *
	 * @param Asset Content Name
	 * @param Asset Content health
	 * @param the repair cost multiplier
	 */
	AssetContent (String name, double health , double repairCostMultiplier){
		this.name = name;
		this.health = health;
		this.repairCostMultiplier = repairCostMultiplier;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String ans = "\nAsset Content " + name + " is at Health: " + health + ". Repair cost multiplier is: " + repairCostMultiplier ;
		
		return ans;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the new health
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * Gets the repair cost multiplier.
	 *
	 * @return the repair cost multiplier
	 */
	public double getRepairCostMultiplier() {
		return repairCostMultiplier;
	}

	/**
	 * Sets the repair cost multiplier.
	 *
	 * @param repairCostMultiplier the new repair cost multiplier
	 */
	public void setRepairCostMultiplier(double repairCostMultiplier) {
		this.repairCostMultiplier = repairCostMultiplier;
	}
	
	/**
	 * Calculate repair cost time.
	 */
	public void calculateRepairCostTime(){
		this.repairCostTime = (MAX_HEALTH - this.health)*this.repairCostMultiplier; 
	}
	
	/**
	 * Gets the repair cost time.
	 *
	 * @return the repair cost time
	 */
	public double getRepairCostTime(){
		return this.repairCostTime;
	}
	
	/**
	 * Apply damage to asset contet
	 *
	 * @param damageToApply the damage to apply
	 */
	public void applyDamage(double damageToApply){
		this.health = this.health - damageToApply;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AssetContent o) {
		
		int ans = 0;
		if (this.name.hashCode() > o.name.hashCode()){
			ans = 1;
		}else if(this.name.hashCode() < o.name.hashCode()){
			ans = -1;
		}
		return ans;
	}
	

	
	

}
