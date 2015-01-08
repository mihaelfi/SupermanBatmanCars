/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

// TODO: Auto-generated Javadoc
/**
 * The Class DamageReport.
 */
public class DamageReport {
	
	/** The asset. */
	private Asset asset;
	
	/** The damage precentage. */
	private double damagePrecentage;
	
	/** The customer group done damage. */
	private String customerGroupDoneDamage;
	
	
	
	/**
	 * Instantiates a new damage report.
	 *
	 * @param asset
	 *            the asset
	 * @param damagePrecentage
	 *            the damage precentage
	 * @param customerGroupDoneDamage
	 *            the customer group done damage
	 */
	public DamageReport(Asset asset, double damagePrecentage , String customerGroupDoneDamage) {
		this.asset = asset;
		this.damagePrecentage = damagePrecentage;
		this.customerGroupDoneDamage = customerGroupDoneDamage;
	}



	/**
	 * Gets the asset.
	 *
	 * @return the asset
	 */
	public Asset getAsset() {
		return asset;
	}



	/**
	 * Sets the asset.
	 *
	 * @param asset
	 *            the new asset
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}



	/**
	 * Gets the damage precentage.
	 *
	 * @return the damage precentage
	 */
	public double getDamagePrecentage() {
		return damagePrecentage;
	}



	/**
	 * Sets the damage precentage.
	 *
	 * @param damagePrecentage
	 *            the new damage precentage
	 */
	public void setDamagePrecentage(double damagePrecentage) {
		this.damagePrecentage = damagePrecentage;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DamageReport asset= " + asset.getName() + "\n Damage Percentage: "+ damagePrecentage + "\n Done By Customer Group: " + this.customerGroupDoneDamage;
	}
	
	/**
	 * Apply damage to 'this' asset.
	 */
	public void applyDamage(){
		synchronized (this.asset) {
			for (int i = 0 ; i < this.asset.getAssetContents().size() ; i++){
				this.asset.getAssetContents().get(i).applyDamage(this.damagePrecentage);
			}
		}
	}

	
	
	
	
	
	
	
	

}
