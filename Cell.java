package homework1Sudoku;

import java.util.ArrayList;

public class Cell {

	int xCoordinate;
	int yCoordinate;
	int cellValue;
	int memberOfBlock;
	ArrayList<Integer> possibleValues;
	
	public Cell (int x, int y, int value) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.cellValue = value;
		this.possibleValues = new ArrayList<Integer>();
	}//constructor
	
	 

}
