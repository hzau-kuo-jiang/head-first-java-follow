package ch06;

import java.util.ArrayList;
import java.util.Random;

public class GameHelper {
	private static final String START_LETTER = "a";
	private static final int START_NUMBER = 1;
	private static final int ROW_LENGTH = 5;
	private static final int COLUMN_LENGTH = 7;
	private static final int SHIP_LENGTH = 3;
	private static final String[] DIRECTIONS = {"toRight", "toBottom"};
	private final int MaxRowToRight = ROW_LENGTH;
	private final int MaxColumnToRight = COLUMN_LENGTH - SHIP_LENGTH + 1;
	private final int MaxRowToBottom = ROW_LENGTH - SHIP_LENGTH + 1;
	private final int MaxColumnToBottom = COLUMN_LENGTH;
	
	public static void main(String[] args) {
		GameHelper helper = new GameHelper();
		ArrayList<Location> grid = helper.initGrid();
		helper.renderGrid(grid);
		
		ArrayList<Location> startLocationsToRight = new ArrayList<Location>();
		ArrayList<Location> startLocationsToBottom = new ArrayList<Location>();
		
		helper.initStartLocations(grid, startLocationsToRight, startLocationsToBottom);
		
		helper.renderGrid(startLocationsToRight);
		helper.renderGrid(startLocationsToBottom);
		
		
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
		
	}
	
	
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
	
	private ArrayList<Location> initGrid() {
		ArrayList<Location> grid = new ArrayList<Location>();
		for (int rowIndex = 0; rowIndex < ROW_LENGTH; rowIndex++) {
			for (int colIndex = 0; colIndex < COLUMN_LENGTH; colIndex++) {
				grid.add(initGridLocation(rowIndex, colIndex));
			}
		}
		
		return grid;
	}
	
	
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
	
	private void renderGrid(ArrayList<Location> grid) {
		for (int i = 0; i < grid.size(); i++) {
			Location gridLocation = grid.get(i);
			System.out.print(renderGridLocation(gridLocation));
			
			if (i < grid.size() - 1) {
				Location nextGridLocation = grid.get(i + 1);
				if (!gridLocation.getRowIndex().equals(nextGridLocation.getRowIndex())) {
					System.out.println();
				}
			}
		}
		
		System.out.println("\n");
	}
	
	private String renderGridLocation(Location gridLocation) {
		String row = String.valueOf((char) (START_LETTER.charAt(0) + gridLocation.getRowIndex()));
		String column = String.valueOf(START_NUMBER + gridLocation.getColIndex());
		String startToRightAvailable = gridLocation.getStartToRightAvailableValue() == 1 ? "👉" : "❌";
		String startToBottomAvailable = gridLocation.getStartToBottomAvailableValue() == 1 ? "⬇\uFE0F" : "❌";
		String hasShip = gridLocation.getHasShipValue() == 1 ? "🚢" : "\uD83D\uDCA7";
		
		return String.format("%s%s(%s,%s,%s)", row, column, startToRightAvailable, startToBottomAvailable, hasShip) + "  ";
		
	}
	
	
}

