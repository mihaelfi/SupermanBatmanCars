package spl.ass3;

import java.util.concurrent.Callable;

public class CallableSimulateStayInAsset implements Callable<Double>{
	
	private RentalRequest currentlyHandeledRentalRequest;
	private Customer stayingCustomer;
	private Asset asset;
	private final double DEFUALT_DAMAGE = 0.5;
	
	
	
	
	
	public CallableSimulateStayInAsset(RentalRequest currentlyHandeledRentalRequest,Customer stayingCustomer,Asset asset) {
		this.currentlyHandeledRentalRequest = currentlyHandeledRentalRequest;
		this.stayingCustomer = stayingCustomer;
		this.asset = asset;
	}




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
		
		
//		for (int i = 0 ; i < this.currentlyHandeledRentalRequest.getAsset().getAssetContents().size() ; i ++){
//			synchronized (this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i)) {
//				double currentHealth = this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getHealth();
//				double newHealth = currentHealth - damageAmount;
//				if (newHealth < 0){
//					newHealth = 0.0;
//				}
//				this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).setHealth(newHealth);
//				Driver.LOGGER.info("Thread id is " +Thread.currentThread().getName()+" In Asset *"+this.asset.getName()+"* The " + this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getName() + " Health was " +currentHealth + " And now its " + this.currentlyHandeledRentalRequest.getAsset().getAssetContents().get(i).getHealth());
//			}
//			
//		}
		
		long sleepTime = this.currentlyHandeledRentalRequest.getDurationOfStay()*2400;
		Driver.LOGGER.warning("\n\t\t The customer *" + this.stayingCustomer.getName() + "* is starting to simulate his stay in the asset:" +
		this.asset.getName() +"\n And is going to stay in the asset for " + this.currentlyHandeledRentalRequest.getDurationOfStay() + " days" + "\n and will cause "+  damageAmount + " of damage.\n");
		Thread.sleep(sleepTime);
		return damageAmount;
		
	}
	
	
	
	

}
