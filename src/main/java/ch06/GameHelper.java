package ch06;

import java.util.ArrayList;
import java.util.Random;

public class GameHelper {
	public static final String START_LETTER = "a";
	public static final int START_NUMBER = 1;
	public static final int ROW_LENGTH = 5;
	public static final int COLUMN_LENGTH = 7;
	public static final int SHIP_LENGTH = 3;
	public static final String[] DIRECTIONS = {"toRight", "toBottom"};
	public static final int MaxColumnToRight = COLUMN_LENGTH - SHIP_LENGTH + 1;
	public static final int MaxRowToBottom = ROW_LENGTH - SHIP_LENGTH + 1;
	public final int MaxRowToRight = ROW_LENGTH;
	public final int MaxColumnToBottom = COLUMN_LENGTH;
	
	public static void main(String[] args) {
		GameHelper helper = new GameHelper();
		ArrayList<Location> grid = helper.initGrid();
		/*		helper.renderGrid(grid);*/
		
		ArrayList<Location> startLocationsToRight = new ArrayList<Location>();
		ArrayList<Location> startLocationsToBottom = new ArrayList<Location>();
		
		helper.initStartLocations(grid, startLocationsToRight, startLocationsToBottom);
		
	/*	helper.renderGrid(startLocationsToRight);
		helper.renderGrid(startLocationsToBottom);*/
		
		
		Random random = new Random();
		
		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship("poniez"));
		ships.add(new Ship("hacqi"));
		ships.add(new Ship("cabista"));
		
		for (Ship ship : ships) {
			ArrayList<Location> shipLocations = new ArrayList<Location>();
			String direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
			if (direction.equals("toRight")) {
				helper.putShipHorizontal(grid, startLocationsToRight, startLocationsToBottom, shipLocations, random);
			} else if (direction.equals("toBottom")) {
				helper.putShipVertical(grid, startLocationsToRight, startLocationsToBottom, shipLocations, random);
			}
			ship.setShipLocations(shipLocations);
		}
		
		helper.renderGrid(grid);
		helper.renderGrid(startLocationsToRight);
		helper.renderGrid(startLocationsToBottom);
		
		for (Ship ship : ships) {
			System.out.println(ship.getName());
			helper.renderGrid(ship.getShipLocations());
		}
		
		LocationList newGrid = new LocationList();
		newGrid.addShip(1, 2);
		
		StartLocationsToRight startLocationsToRight1 = new StartLocationsToRight();
		System.out.println(startLocationsToRight1);
		
		StartLocationsToBottom startLocationsToBottom1 = new StartLocationsToBottom();
		System.out.println(startLocationsToBottom1);
	}
	
	//	finish reconstructing this method
	private void initStartLocations(ArrayList<Location> grid, ArrayList<Location> startLocationsToRight, ArrayList<Location> startLocationsToBottom) {
		for (Location gridLocation : grid) {
			if (gridLocation.getStartToRightAvailableValue() == 1) {
				startLocationsToRight.add(gridLocation);
			}
			if (gridLocation.getStartToBottomAvailableValue() == 1) {
				startLocationsToBottom.add(gridLocation);
			}
		}
	}
	
	
	private void putShipHorizontal(ArrayList<Location> grid, ArrayList<Location> startLocationsToRight, ArrayList<Location> startLocationsToBottom, ArrayList<Location> shipLocations, Random random) {
		int index = random.nextInt(startLocationsToRight.size());
		Location startLocation = startLocationsToRight.get(index);
		
		
		int startRowIndex = startLocation.getRowIndex();
		int startColIndex = startLocation.getColIndex();
		
		for (int i = 0; i < SHIP_LENGTH; i++) {
			int rowIndex = startRowIndex;
			int colIndex = startColIndex + i;
			
			shipLocations.addAll(addShipToGrid(grid, rowIndex, colIndex));
		}
		
		reduceLocationsToRight(startLocationsToRight, startLocationsToBottom, startRowIndex, startColIndex);
	}
	
	private void reduceLocationsToRight(ArrayList<Location> startLocationsToRight, ArrayList<Location> startLocationsToBottom, int startRowIndex, int startColIndex) {
		
		startLocationsToRight.removeIf(location -> location.getRowIndex() == startRowIndex && (location.getColIndex() >= startColIndex - SHIP_LENGTH + 1 && location.getColIndex() <= startColIndex + SHIP_LENGTH - 1));
		startLocationsToBottom.removeIf(location -> (location.getRowIndex() >= startRowIndex - SHIP_LENGTH + 1 && location.getRowIndex() <= startRowIndex) && (location.getColIndex() >= startColIndex && location.getColIndex() <= startColIndex + SHIP_LENGTH - 1));
	}
	
	
	private void putShipVertical(ArrayList<Location> grid, ArrayList<Location> startLocationsToRight, ArrayList<Location> startLocationsToBottom, ArrayList<Location> shipLocations, Random random) {
		int index = random.nextInt(startLocationsToBottom.size());
		Location startLocation = startLocationsToBottom.get(index);
		
		int startRowIndex = startLocation.getRowIndex();
		int startColIndex = startLocation.getColIndex();
		
		for (int i = 0; i < SHIP_LENGTH; i++) {
			int rowIndex = startRowIndex + i;
			int colIndex = startColIndex;
			
			shipLocations.addAll(addShipToGrid(grid, rowIndex, colIndex));
		}
		
		reduceLocationsToBottom(startLocationsToRight, startLocationsToBottom, startRowIndex, startColIndex);
	}
	
	private void reduceLocationsToBottom(ArrayList<Location> startLocationsToRight, ArrayList<Location> startLocationsToBottom, int startRowIndex, int startColIndex) {
		
		startLocationsToRight.removeIf(location -> (location.getRowIndex() >= startRowIndex && location.getRowIndex() <= startRowIndex + SHIP_LENGTH - 1) && (location.getColIndex() >= startColIndex - SHIP_LENGTH + 1 && location.getColIndex() <= startColIndex));
		startLocationsToBottom.removeIf(location -> (location.getRowIndex() >= startRowIndex - SHIP_LENGTH + 1 && location.getRowIndex() <= startRowIndex + SHIP_LENGTH - 1) && location.getColIndex() == startColIndex);
	}
	
	
	//	finish reconstructing this method
	private ArrayList<Location> addShipToGrid(ArrayList<Location> grid, int rowIndex, int colIndex) {
		ArrayList<Location> shipLocations = new ArrayList<Location>();
		
		for (Location gridLocation : grid) {
			if (gridLocation.getRowIndex() == rowIndex && gridLocation.getColIndex() == colIndex) {
				gridLocation.setHasShipValue(1);
				shipLocations.add(gridLocation);
			}
		}
		
		return shipLocations;
	}
	
	//	finish reconstructing this method
	private ArrayList<Location> initGrid() {
		ArrayList<Location> grid = new ArrayList<Location>();
		for (int rowIndex = 0; rowIndex < ROW_LENGTH; rowIndex++) {
			for (int colIndex = 0; colIndex < COLUMN_LENGTH; colIndex++) {
				grid.add(new Location(rowIndex, colIndex));
			}
		}
		
		return grid;
	}
	
	//	finish reconstructing this method
	private void renderGrid(ArrayList<Location> grid) {
		for (int i = 0; i < grid.size(); i++) {
			Location gridLocation = grid.get(i);
			
			System.out.print(gridLocation);
			
			if (i < grid.size() - 1) {
				Location nextGridLocation = grid.get(i + 1);
				if (!gridLocation.getRowIndex().equals(nextGridLocation.getRowIndex())) {
					System.out.println();
				}
			}
		}
		
		System.out.println("\n");
	}
	
	
}

