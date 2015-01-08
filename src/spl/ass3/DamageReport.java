package spl.ass3;

public class DamageReport {
	
	protected Asset asset;
	protected double damagePrecentage;
	
	
	
	public DamageReport(Asset asset, double damagePrecentage) {
		this.asset = asset;
		this.damagePrecentage = damagePrecentage;
	}



	public Asset getAsset() {
		return asset;
	}



	public void setAsset(Asset asset) {
		this.asset = asset;
	}



	public double getDamagePrecentage() {
		return damagePrecentage;
	}



	public void setDamagePrecentage(double damagePrecentage) {
		this.damagePrecentage = damagePrecentage;
	}



	@Override
	public String toString() {
		return "DamageReport [asset=" + asset.getName() + ", damagePrecentage="
				+ damagePrecentage + "]";
	}
	
	
	
	
	
	
	
	

}
