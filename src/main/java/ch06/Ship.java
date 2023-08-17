package ch06;

import java.util.ArrayList;

public class Ship {
	private final String name;
	private ArrayList<Location> shipLocations;
	
	public Ship(String name) {
		this.name = name;
		this.shipLocations = new ArrayList<Location>();
	}
	
	
	public ArrayList<Location> getShipLocations() {
		return shipLocations;
	}
	
	public void setShipLocations(ArrayList<Location> shipLocations) {
		this.shipLocations = shipLocations;
	}
	
	public String getName() {
		return name;
	}
}
