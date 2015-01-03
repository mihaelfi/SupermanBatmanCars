package spl.ass3;

public class RentalRequest {

	

	protected int id;
	protected String assetType;
	protected int assetSize;
	protected int durationOfStay;
	protected Asset asset;
	protected String requestStatus;
	

	
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
	
	
	
}
