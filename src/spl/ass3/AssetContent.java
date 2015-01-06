package spl.ass3;


public class AssetContent implements Comparable<AssetContent> {
	
	private String name;
	private double health;
	private double repairCostMultiplier;
	private double repairCostTime = -1;
	private final double MAX_HEALTH = 100;
	
	
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
	
	public void calculateRepairCostTime(){
		this.repairCostTime = (MAX_HEALTH - this.health)*this.repairCostMultiplier; 
	}
	
	public double getRepairCostTime(){
		return this.repairCostTime;
	}

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
