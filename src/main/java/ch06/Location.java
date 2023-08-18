package ch06;

import java.util.HashMap;

public class Location {
	
	private final HashMap<String, Integer> location;
	
	public Location() {
		this.location = new HashMap<String, Integer>();
	}
	
	public Location(int rowIndex, int colIndex) {
		this.location = new HashMap<String, Integer>();
		setRowIndex(rowIndex);
		setColIndex(colIndex);
		
		int startToRightAvailable = colIndex < GameHelper.MaxColumnToRight ? 1 : 0;
		int startToBottomAvailable = rowIndex < GameHelper.MaxRowToBottom ? 1 : 0;
		setStartToRightAvailableValue(startToRightAvailable);
		setStartToBottomAvailableValue(startToBottomAvailable);
		
		setHasShipValue(0);
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
	
	/*
	private Location initGridLocation(int rowIndex, int colIndex) {
		Location gridLocation = new Location();
		
		gridLocation.setRowIndex(rowIndex);
		gridLocation.setColIndex(colIndex);
		
		int startToRightAvailable = colIndex < MaxColumnToRight ? 1 : 0;
		int startToBottomAvailable = rowIndex < MaxRowToBottom ? 1 : 0;
		gridLocation.setStartToRightAvailableValue(startToRightAvailable);
		gridLocation.setStartToBottomAvailableValue(startToBottomAvailable);
		
		gridLocation.setHasShipValue(0);
		
		return gridLocation;
	}
	* */
	
	public void setHasShipValue(Integer hasShipValue) {
		location.put("hasShip", hasShipValue);
	}
	
	public String toString() {
		String row = String.valueOf((char) (GameHelper.START_LETTER.charAt(0) + getRowIndex()));
		String column = String.valueOf(GameHelper.START_NUMBER + getColIndex());
		String startToRightAvailable = getStartToRightAvailableValue() == 1 ? "👉" : "❌";
		String startToBottomAvailable = getStartToBottomAvailableValue() == 1 ? "⬇\uFE0F" : "❌";
		String hasShip = getHasShipValue() == 1 ? "🚢" : "\uD83D\uDCA7";
		
		return String.format("%s%s(%s,%s,%s)", row, column, startToRightAvailable, startToBottomAvailable, hasShip) + "  ";
	}
}
