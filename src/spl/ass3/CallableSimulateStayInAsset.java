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
		Driver.LOGGER.info("The Customer " + this.stayingCustomer.getName() + "Is starting it's say in:" + this.currentlyHandeledRentalRequest.getAsset().getName() );
		Double ans = (double) 0;
		
		if (this.stayingCustomer.getVandalismType() == "ARBITRARY"){
			double diffrentce = this.stayingCustomer.getMaximumDamage() - this.stayingCustomer.getMinimumDamage();
			ans = diffrentce*Math.random();
		}else if (this.stayingCustomer.getVandalismType() == "FIXED"){
			ans = ((double)this.stayingCustomer.getMaximumDamage() + (double)this.stayingCustomer.getMinimumDamage()) / 2;
		}else{
			ans = DEFUALT_DAMAGE;
		}
		
		Driver.LOGGER.info("The Customer acumulated damage is: " + ans+"%");
		
		long sleepTime = this.currentlyHandeledRentalRequest.getDurationOfStay()*24000;
		Thread.sleep(sleepTime);
		return ans;
		
	}
	
	
	
	

}
