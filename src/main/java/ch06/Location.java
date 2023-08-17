package ch06;

import java.util.HashMap;

public class Location {
	/*private String row;
	private String column;
	private String startToRightAvailable;
	private String startToBottomAvailable;
	private String hasShip;
	private final Integer rowIndex;
	private final Integer colIndex;
	private final Integer startToRightAvailableValue;
	private final Integer startToBottomAvailableValue;
	private final Integer hasShipValue;
	private final HashMap<String, Integer> location;
	
	public Location(Integer rowIndex, Integer colIndex, Integer startToRightAvailableValue, Integer startToBottomAvailableValue, Integer hasShipValue) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.startToRightAvailableValue = startToRightAvailableValue;
		this.startToBottomAvailableValue = startToBottomAvailableValue;
		this.hasShipValue = hasShipValue;
		this.location = new HashMap<String, Integer>();
		this.location.put("row", rowIndex);
		this.location.put("column", colIndex);
		this.location.put("startToRightAvailable", startToRightAvailableValue);
		this.location.put("startToBottomAvailable", startToBottomAvailableValue);
		this.location.put("hasShip", hasShipValue);
	}*/
	
	private final HashMap<String, Integer> location;
	
	public Location() {
		this.location = new HashMap<String, Integer>();
	}
	
	public Integer getRowIndex() {
		return location.get("row");
	}
	
	public void setRowIndex(Integer rowIndex) {
		location.put("row", rowIndex);
	}
	
	public Integer getColIndex() {
		return location.get("column");
	}
	
	public void setColIndex(Integer colIndex) {
		location.put("column", colIndex);
	}
	
	public Integer getStartToRightAvailableValue() {
		return location.get("startToRightAvailable");
	}
	
	public void setStartToRightAvailableValue(Integer startToRightAvailableValue) {
		location.put("startToRightAvailable", startToRightAvailableValue);
	}
	
	public Integer getStartToBottomAvailableValue() {
		return location.get("startToBottomAvailable");
	}
	
	public void setStartToBottomAvailableValue(Integer startToBottomAvailableValue) {
		location.put("startToBottomAvailable", startToBottomAvailableValue);
	}
	
	public Integer getHasShipValue() {
		return location.get("hasShip");
	}
	
	public void setHasShipValue(Integer hasShipValue) {
		location.put("hasShip", hasShipValue);
	}
	
}
