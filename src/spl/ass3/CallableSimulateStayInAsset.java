package spl.ass3;

import java.util.concurrent.Callable;

public class CallableSimulateStayInAsset implements Callable<Double>{
	
	protected RentalRequest currentlyHandeledRentalRequest;
	protected Customer stayingCustomer;
	protected final double DEFUALT_DAMAGE = 0.5;
	
	
	
	
	
	public CallableSimulateStayInAsset(RentalRequest currentlyHandeledRentalRequest,Customer stayingCustomer) {
		this.currentlyHandeledRentalRequest = currentlyHandeledRentalRequest;
		this.stayingCustomer = stayingCustomer;
	}




	@Override
	public Double call() throws Exception {
		
		this.currentlyHandeledRentalRequest.getAsset().setStatusOccupied();
		
		Driver.LOGGER.info("The Customer " + this.stayingCustomer.getName() + "Is starting it's say in:" + this.currentlyHandeledRentalRequest.getAsset().getName() );
		Double damageAmount = (double) 0;
		
		
		if (this.stayingCustomer.getVandalismType() == "ARBITRARY"){
			double diffrentce = this.stayingCustomer.getMaximumDamage() - this.stayingCustomer.getMinimumDamage();
			damageAmount = diffrentce*Math.random();
		}else if (this.stayingCustomer.getVandalismType() == "FIXED"){
			damageAmount = ((double)this.stayingCustomer.getMaximumDamage() + (double)this.stayingCustomer.getMinimumDamage()) / 2;
		}else{
			damageAmount = DEFUALT_DAMAGE;
		}
		
		Driver.LOGGER.info("The Customer acumulated damage is: " + damageAmount+"%");
		
		
		for (int i = 0 ; i < this.currentlyHandeledRentalRequest.getAsset().getAssetContents().size() ; i ++){
			double currentHealth = this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getHealth();
			double newHealth = currentHealth - damageAmount;
			this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).setHealth(newHealth);
			Driver.LOGGER.info("The " + this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getName() + " Health was " +currentHealth + " And now its " + this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getHealth());
		}
		
		long sleepTime = this.currentlyHandeledRentalRequest.getDurationOfStay()*24000;
		Thread.sleep(sleepTime);
		return damageAmount;
		
	}
	
	
	
	

}
