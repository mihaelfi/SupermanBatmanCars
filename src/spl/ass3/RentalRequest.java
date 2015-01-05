package spl.ass3;

public class RentalRequest {

	

	private int id;
	private String assetType;
	private int assetSize;
	private int durationOfStay;
	private Asset asset;
	private String requestStatus;  //Incomplete Fufulied Inprogress Complete
	private final String STATUS_INCOMPLETE = "INCOMPLETE";
	private final String STATUS_FUFULIED = "FUFULIED";
	private final String STATUS_INPROGRESS = "INPROGRESS";
	private final String STATUS_COMPLETE = "COMPLETE";
	
	

	
	public RentalRequest(int id, String assetType, int assetSize,
			int durationOfStay, String requestStatus) {
		this.id = id;
		this.assetType = assetType;
		this.assetSize = assetSize;
		this.durationOfStay = durationOfStay;
		this.requestStatus = requestStatus;
		
		
	}
	
	



	@Override
	public String toString() {
		return "RentalRequest id is =" + id + "\nassetType=" + assetType
				+ "\nassetSize=" + assetSize + "\ndurationOfStay="
				+ durationOfStay + "\nrequestStatus="
				+ requestStatus;
	}
	
	public String toStringWithAsset() {
		return "RentalRequest id is =" + id + "\nassetType=" + assetType
				+ "\nassetSize=" + assetSize + "\ndurationOfStay="
				+ durationOfStay + "\nasset=" + asset.toString() + "\nrequestStatus="
				+ requestStatus;
	}





	public Asset getAsset() {
		return asset;
	}





	public void setAsset(Asset asset) {
		this.asset = asset;
	}





	public String getRequestStatus() {
		return requestStatus;
	}





//	public void setRequestStatus(String requestStatus) {
//		this.requestStatus = requestStatus;
//	}


	public void setRequestStatusIncomplete(){
		this.requestStatus = this.STATUS_INCOMPLETE;
	}
	
	public void setRequestStatusFufulied(){
		this.requestStatus = this.STATUS_FUFULIED;
	}
	
	public void setRequestStatusInProgress(){
		this.requestStatus = this.STATUS_INPROGRESS;
	}
	
	public void setRequestStatusComplete(){
		this.requestStatus = this.STATUS_COMPLETE;
	}


	public int getId() {
		return id;
	}





	public String getAssetType() {
		return assetType;
	}





	public int getAssetSize() {
		return assetSize;
	}





	public int getDurationOfStay() {
		return durationOfStay;
	}
	
	
	
}
