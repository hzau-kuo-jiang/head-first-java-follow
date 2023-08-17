package ch06;

import java.util.ArrayList;
import java.util.HashMap;
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
		ArrayList<HashMap<String, Integer>> grid = helper.initGrid();
		helper.renderGrid(grid);
		
		ArrayList<HashMap<String, Integer>> startLocationsToRight = new ArrayList<HashMap<String, Integer>>();
		ArrayList<HashMap<String, Integer>> startLocationsToBottom = new ArrayList<HashMap<String, Integer>>();
		
		helper.initStartLocations(grid, startLocationsToRight, startLocationsToBottom);
		
		helper.renderGrid(startLocationsToRight);
		helper.renderGrid(startLocationsToBottom);
		
		
		Random random = new Random();
		
	/*	String[] ships = {
			"poniez",
			"hacqi",
			"cabista"
		};
		ArrayList<HashMap<String, ArrayList<HashMap<String, Integer>>>> shipsLocations = new ArrayList<HashMap<String, ArrayList<HashMap<String, Integer>>>>();
		
		for (String ship : ships) {
			ArrayList<HashMap<String, Integer>> shipLocations = new ArrayList<HashMap<String, Integer>>();
			HashMap<String, ArrayList<HashMap<String, Integer>>> shipLocationsMap = new HashMap<String, ArrayList<HashMap<String, Integer>>>();
			
			String direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
			if (direction.equals("toRight")) {
				helper.putShipHorizontal(grid, startLocationsToRight, startLocationsToBottom, shipLocations, random);
			} else if (direction.equals("toBottom")) {
				helper.putShipVertical(grid, startLocationsToRight, startLocationsToBottom, shipLocations, random);
			}
			
			shipLocationsMap.put(ship, shipLocations);
			shipsLocations.add(shipLocationsMap);
		}
		
		helper.renderGrid(grid);
		helper.renderGrid(startLocationsToRight);
		helper.renderGrid(startLocationsToBottom);
		
		for (HashMap<String, ArrayList<HashMap<String, Integer>>> shipLocationsMap : shipsLocations) {
			for (String ship : shipLocationsMap.keySet()) {
				ArrayList<HashMap<String, Integer>> shipLocations = shipLocationsMap.get(ship);
				System.out.println(ship);
				helper.renderGrid(shipLocations);
			}
		}*/
		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship("poniez"));
		ships.add(new Ship("hacqi"));
		ships.add(new Ship("cabista"));
		
		for (Ship ship : ships) {
			ArrayList<HashMap<String, Integer>> shipLocations = new ArrayList<HashMap<String, Integer>>();
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
	
	private void initStartLocations(ArrayList<HashMap<String, Integer>> grid, ArrayList<HashMap<String, Integer>> startLocationsToRight, ArrayList<HashMap<String, Integer>> startLocationsToBottom) {
		for (HashMap<String, Integer> gridLocation : grid) {
			if (gridLocation.get("startToRightAvailable") == 1) {
				startLocationsToRight.add(gridLocation);
			}
			if (gridLocation.get("startToBottomAvailable") == 1) {
				startLocationsToBottom.add(gridLocation);
			}
		}
	}
	
	private void putShipHorizontal(ArrayList<HashMap<String, Integer>> grid, ArrayList<HashMap<String, Integer>> startLocationsToRight, ArrayList<HashMap<String, Integer>> startLocationsToBottom, ArrayList<HashMap<String, Integer>> shipLocations, Random random) {
		int index = random.nextInt(startLocationsToRight.size());
		HashMap<String, Integer> startLocation = startLocationsToRight.get(index);
		
		int startRowIndex = startLocation.get("row");
		int startColIndex = startLocation.get("column");
		
		for (int i = 0; i < SHIP_LENGTH; i++) {
			int rowIndex = startRowIndex;
			int colIndex = startColIndex + i;
			
			shipLocations.addAll(addShipToGrid(grid, rowIndex, colIndex));
		}
		
		reduceLocationsToRight(startLocationsToRight, startLocationsToBottom, startRowIndex, startColIndex);
	}
	
	private void reduceLocationsToRight(ArrayList<HashMap<String, Integer>> startLocationsToRight, ArrayList<HashMap<String, Integer>> startLocationsToBottom, int startRowIndex, int startColIndex) {
		startLocationsToRight.removeIf(location -> location.get("row") == startRowIndex && (location.get("column") >= startColIndex - SHIP_LENGTH + 1 && location.get("column") <= startColIndex + SHIP_LENGTH - 1));
		startLocationsToBottom.removeIf(location -> (location.get("row") >= startRowIndex - SHIP_LENGTH + 1 && location.get("row") <= startRowIndex) && (location.get("column") >= startColIndex && location.get("column") <= startColIndex + SHIP_LENGTH - 1));
	}
	
	private void putShipVertical(ArrayList<HashMap<String, Integer>> grid, ArrayList<HashMap<String, Integer>> startLocationsToRight, ArrayList<HashMap<String, Integer>> startLocationsToBottom, ArrayList<HashMap<String, Integer>> shipLocations, Random random) {
		int index = random.nextInt(startLocationsToBottom.size());
		HashMap<String, Integer> startLocation = startLocationsToBottom.get(index);
		
		int startRowIndex = startLocation.get("row");
		int startColIndex = startLocation.get("column");
		
		for (int i = 0; i < SHIP_LENGTH; i++) {
			int rowIndex = startRowIndex + i;
			int colIndex = startColIndex;
			
			shipLocations.addAll(addShipToGrid(grid, rowIndex, colIndex));
		}
		
		reduceLocationsToBottom(startLocationsToRight, startLocationsToBottom, startRowIndex, startColIndex);
	}
	
	private void reduceLocationsToBottom(ArrayList<HashMap<String, Integer>> startLocationsToRight, ArrayList<HashMap<String, Integer>> startLocationsToBottom, int startRowIndex, int startColIndex) {
		startLocationsToRight.removeIf(location -> (location.get("row") >= startRowIndex && location.get("row") <= startRowIndex + SHIP_LENGTH - 1) && (location.get("column") >= startColIndex - SHIP_LENGTH + 1 && location.get("column") <= startColIndex));
		startLocationsToBottom.removeIf(location -> (location.get("row") >= startRowIndex - SHIP_LENGTH + 1 && location.get("row") <= startRowIndex + SHIP_LENGTH - 1) && location.get("column") == startColIndex);
	}
	
	private ArrayList<HashMap<String, Integer>> addShipToGrid(ArrayList<HashMap<String, Integer>> grid, int rowIndex, int colIndex) {
		ArrayList<HashMap<String, Integer>> shipLocations = new ArrayList<HashMap<String, Integer>>();
		
		for (HashMap<String, Integer> gridLocation : grid) {
			if (gridLocation.get("row") == rowIndex && gridLocation.get("column") == colIndex) {
				gridLocation.put("hasShip", 1);
				shipLocations.add(gridLocation);
			}
		}
		
		return shipLocations;
	}
	
	
	private ArrayList<HashMap<String, Integer>> initGrid() {
		ArrayList<HashMap<String, Integer>> grid = new ArrayList<HashMap<String, Integer>>();
		for (int rowIndex = 0; rowIndex < ROW_LENGTH; rowIndex++) {
			for (int colIndex = 0; colIndex < COLUMN_LENGTH; colIndex++) {
				grid.add(initGridLocation(rowIndex, colIndex));
			}
		}
		
		return grid;
	}
	
	private HashMap<String, Integer> initGridLocation(int rowIndex, int colIndex) {
		HashMap<String, Integer> gridLocation = new HashMap<String, Integer>();
		
		gridLocation.put("row", rowIndex);
		gridLocation.put("column", colIndex);
		
		int startToRightAvailable = colIndex < MaxColumnToRight ? 1 : 0;
		int startToBottomAvailable = rowIndex < MaxRowToBottom ? 1 : 0;
		gridLocation.put("startToRightAvailable", startToRightAvailable);
		gridLocation.put("startToBottomAvailable", startToBottomAvailable);
		
		gridLocation.put("hasShip", 0);
		
		return gridLocation;
	}
	
	private void renderGrid(ArrayList<HashMap<String, Integer>> grid) {
		for (int i = 0; i < grid.size(); i++) {
			HashMap<String, Integer> gridLocation = grid.get(i);
			System.out.print(renderGridLocation(gridLocation));
			
			if (i < grid.size() - 1) {
				HashMap<String, Integer> nextGridLocation = grid.get(i + 1);
				if (!gridLocation.get("row").equals(nextGridLocation.get("row"))) {
					System.out.println();
				}
			}
		}
		
		System.out.println("\n");
	}
	
	private String renderGridLocation(HashMap<String, Integer> gridLocation) {
		String row = String.valueOf((char) (START_LETTER.charAt(0) + gridLocation.get("row")));
		String column = String.valueOf(START_NUMBER + gridLocation.get("column"));
		String startToRightAvailable = gridLocation.get("startToRightAvailable") == 1 ? "👉" : "❌";
		String startToBottomAvailable = gridLocation.get("startToBottomAvailable") == 1 ? "⬇\uFE0F" : "❌";
		String hasShip = gridLocation.get("hasShip") == 1 ? "🚢" : "\uD83D\uDCA7";
		
		return String.format("%s%s(%s,%s,%s)", row, column, startToRightAvailable, startToBottomAvailable, hasShip) + "  ";
	}
	
}

