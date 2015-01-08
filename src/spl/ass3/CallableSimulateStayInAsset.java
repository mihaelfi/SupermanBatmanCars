/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

import java.util.concurrent.Callable;

// TODO: Auto-generated Javadoc
/**
 * The Class CallableSimulateStayInAsset.
 */
public class CallableSimulateStayInAsset implements Callable<Double>{
	
	/** The currently handeled rental request. */
	private RentalRequest currentlyHandeledRentalRequest;
	
	/** The staying customer. */
	private Customer stayingCustomer;
	
	/** The asset. */
	private Asset asset;
	
	/** The defualt damage. */
	private final double DEFUALT_DAMAGE = 0.5;
	
	
	
	
	
	/**
	 * Instantiates a new callable simulate stay in asset.
	 *
	 * @param currentlyHandeledRentalRequest
	 *            the currently handeled rental request
	 * @param stayingCustomer
	 *            the staying customer
	 * @param asset
	 *            the asset
	 */
	public CallableSimulateStayInAsset(RentalRequest currentlyHandeledRentalRequest,Customer stayingCustomer,Asset asset) {
		this.currentlyHandeledRentalRequest = currentlyHandeledRentalRequest;
		this.stayingCustomer = stayingCustomer;
		this.asset = asset;
	}




	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Double call() throws Exception {
		
		this.currentlyHandeledRentalRequest.getAsset().setStatusOccupied();
		
		Driver.LOGGER.fine("The Customer " + this.stayingCustomer.getName() + "Is starting it's say in:" + this.currentlyHandeledRentalRequest.getAsset().getName() );
		Double damageAmount = (double) 0;
		
		
		if (this.stayingCustomer.getVandalismType().equals("Arbitrary")){
			double diffrentce = this.stayingCustomer.getMaximumDamage() - this.stayingCustomer.getMinimumDamage();
			damageAmount = diffrentce*Math.random();
			Driver.LOGGER.fine("The damage amount is: " + damageAmount + "and damge type is: ARBITRARY");
		}else if (this.stayingCustomer.getVandalismType().equals("Fixed")){
			damageAmount = ((double)this.stayingCustomer.getMaximumDamage() + (double)this.stayingCustomer.getMinimumDamage()) / 2;
			Driver.LOGGER.fine("The damage amount is: " + damageAmount + "and damge type is: Fixed");
		}else{
			damageAmount = DEFUALT_DAMAGE;
		}
		
		Driver.LOGGER.fine("The Customer *"+ this.stayingCustomer.getName() +  "* acumulated damage is: " + damageAmount+"%");
		
		
		long sleepTime = this.currentlyHandeledRentalRequest.getDurationOfStay()*24000;
		Driver.LOGGER.warning("\n\t\t The customer *" + this.stayingCustomer.getName() + "* is starting to simulate his stay in the asset:" +
		this.asset.getName() +"\n And is going to stay in the asset for " + this.currentlyHandeledRentalRequest.getDurationOfStay() + " days" + "\n and will cause "+  damageAmount + " of damage.\n");
		Thread.sleep(sleepTime);
		return damageAmount;
		
	}
	
	
	
	

}
