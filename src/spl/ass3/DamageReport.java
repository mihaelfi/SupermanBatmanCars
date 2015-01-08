package spl.ass3;

public class DamageReport {
	
	private Asset asset;
	private double damagePrecentage;
	private String customerGroupDoneDamage;
	
	
	
	public DamageReport(Asset asset, double damagePrecentage , String customerGroupDoneDamage) {
		this.asset = asset;
		this.damagePrecentage = damagePrecentage;
		this.customerGroupDoneDamage = customerGroupDoneDamage;
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
		return "DamageReport asset= " + asset.getName() + "\n Damage Percentage: "+ damagePrecentage + "\n Done By Customer Group: " + this.customerGroupDoneDamage;
	}
	
	public void applyDamage(){
		synchronized (this.asset) {
			for (int i = 0 ; i < this.asset.getAssetContents().size() ; i++){
				this.asset.getAssetContents().get(i).applyDamage(this.damagePrecentage);
			}
		}
	}

	
	
	
	
	
	
	
	

}
