package spl.ass3;


public class AssetContent {
	
	protected String name;
	protected double health;
	protected double repairCostMultiplier;
	
	
	AssetContent (String name, double health , double repairCostMultiplier){
		this.name = name;
		this.health = health;
		this.repairCostMultiplier = repairCostMultiplier;
	}
	
	public String toString(){
		String ans = "Asset Content " + name + " is at Health: " + health + ". Repair cost multiplier is: " + repairCostMultiplier + "\n";
		
		return ans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getRepairCostMultiplier() {
		return repairCostMultiplier;
	}

	public void setRepairCostMultiplier(double repairCostMultiplier) {
		this.repairCostMultiplier = repairCostMultiplier;
	}
	
	

}
