package ch06;

public class StartLocationsToRight extends LocationList {
	public StartLocationsToRight() {
		this.getLocations().removeIf(location -> location.getStartToRightAvailableValue() == 0);
	}
	
	
}
