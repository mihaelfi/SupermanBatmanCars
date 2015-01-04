package spl.ass3;

public class ClerkDetails {
	
	protected String name;
	protected Location location;

	
	public ClerkDetails(String name , Location location) {
	this.name = name;
	this.location = location;
	}
	
	public String toString(){
		String ans = "Name: " + this.name + " Location: " + this.location.toString();
		return ans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
