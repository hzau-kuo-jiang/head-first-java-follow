package ch06;

import java.util.ArrayList;

public class LocationList {
	private final ArrayList<Location> locations;
	
	public LocationList() {
		this.locations = new ArrayList<Location>();
		for (int rowIndex = 0; rowIndex < GameHelper.ROW_LENGTH; rowIndex++) {
			for (int colIndex = 0; colIndex < GameHelper.COLUMN_LENGTH; colIndex++) {
				locations.add(new Location(rowIndex, colIndex));
			}
		}
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}
	
	
	public void addShip(int rowIndex, int colIndex) {
		for (Location location : locations) {
			if (location.getRowIndex() == rowIndex && location.getColIndex() == colIndex) {
				location.setHasShipValue(1);
			}
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < locations.size(); i++) {
			Location gridLocation = locations.get(i);
			result.append(gridLocation);
			
			if (i < locations.size() - 1) {
				Location nextGridLocation = locations.get(i + 1);
				if (!gridLocation.getRowIndex().equals(nextGridLocation.getRowIndex())) {
					result.append("\n");
				}
			}
		}
		result.append("\n");
		
		return result.toString();
	}
}
