/*
 * Spl Assingment 3 
 * Michael Fildstien Id: 309161594 
 * Maxim Rusinksi Id: 316803931
 */
package spl.ass3;

// TODO: Auto-generated Javadoc
/**
 * The Class RentalRequest.
 */
public class RentalRequest {

	

	/** The id. */
	private int id;
	
	/** The asset type. */
	private String assetType;
	
	/** The asset size. */
	private int assetSize;
	
	/** The duration of stay. */
	private int durationOfStay;
	
	/** The asset. */
	private Asset asset;
	
	/** The request status. */
	private String requestStatus;  //Incomplete Fufulied Inprogress Complete
	
	/** The status incomplete. */
	private final String STATUS_INCOMPLETE = "INCOMPLETE";
	
	/** The status fufulied. */
	private final String STATUS_FUFULIED = "FUFULIED";
	
	/** The status inprogress. */
	private final String STATUS_INPROGRESS = "INPROGRESS";
	
	/** The status complete. */
	private final String STATUS_COMPLETE = "COMPLETE";
	
	

	
	/**
	 * Instantiates a new rental request.
	 *
	 * @param id
	 *            the id
	 * @param assetType
	 *            the asset type
	 * @param assetSize
	 *            the asset size
	 * @param durationOfStay
	 *            the duration of stay
	 * @param requestStatus
	 *            the request status
	 */
	public RentalRequest(int id, String assetType, int assetSize,
			int durationOfStay, String requestStatus) {
		this.id = id;
		this.assetType = assetType;
		this.assetSize = assetSize;
		this.durationOfStay = durationOfStay;
		this.requestStatus = requestStatus;
		
		
	}
	
	



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nRentalRequest id is =" + id + "\nassetType=" + assetType
				+ "\nassetSize=" + assetSize + "\ndurationOfStay="
				+ durationOfStay + "\nrequestStatus="
				+ requestStatus;
	}
	
	/**
	 * To string with asset.
	 *
	 * @return the string
	 */
	public String toStringWithAsset() {
		return "RentalRequest id is =" + id + "\nassetType=" + assetType
				+ "\nassetSize=" + assetSize + "\ndurationOfStay="
				+ durationOfStay + "\nasset=" + asset.toString() + "\nrequestStatus="
				+ requestStatus;
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
	 * Gets the request status.
	 *
	 * @return the request status
	 */
	public String getRequestStatus() {
		return requestStatus;
	}

	/**
 * Sets the request status incomplete.
 */
public void setRequestStatusIncomplete(){
		this.requestStatus = this.STATUS_INCOMPLETE;
	}
	
	/**
	 * Sets the request status fufulied.
	 */
	public void setRequestStatusFufulied(){
		this.requestStatus = this.STATUS_FUFULIED;
	}
	
	/**
	 * Sets the request status in progress.
	 */
	public void setRequestStatusInProgress(){
		this.requestStatus = this.STATUS_INPROGRESS;
	}
	
	/**
	 * Sets the request status complete.
	 */
	public void setRequestStatusComplete(){
		this.requestStatus = this.STATUS_COMPLETE;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the asset type.
	 *
	 * @return the asset type
	 */
	public String getAssetType() {
		return assetType;
	}



	/**
	 * Gets the asset size.
	 *
	 * @return the asset size
	 */
	public int getAssetSize() {
		return assetSize;
	}





	/**
	 * Gets the duration of stay.
	 *
	 * @return the duration of stay
	 */
	public int getDurationOfStay() {
		return durationOfStay;
	}
	
	
	
}
