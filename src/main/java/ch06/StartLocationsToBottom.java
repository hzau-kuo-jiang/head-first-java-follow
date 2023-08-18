package ch06;

public class StartLocationsToBottom extends LocationList {
	public StartLocationsToBottom() {
		this.getLocations().removeIf(location -> location.getStartToBottomAvailableValue() == 0);
	}
}
