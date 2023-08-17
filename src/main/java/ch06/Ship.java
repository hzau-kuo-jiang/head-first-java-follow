package ch06;

import java.util.ArrayList;
import java.util.HashMap;

public class Ship {
	private final String name;
	private ArrayList<HashMap<String, Integer>> shipLocations;
	
	public Ship(String name) {
		this.name = name;
		this.shipLocations = new ArrayList<HashMap<String, Integer>>();
	}
	
	public ArrayList<HashMap<String, Integer>> getShipLocations() {
		return shipLocations;
	}
	
	public void setShipLocations(ArrayList<HashMap<String, Integer>> shipLocations) {
		this.shipLocations = shipLocations;
	}
	
	public String getName() {
		return name;
	}
}
