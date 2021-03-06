package homework1Sudoku;

import java.util.ArrayList;

/**
 * Cell.java
 * 
 * @author Miles Friedman
 * @version 5/22/14
 */
public class Cell {

	private int xCoordinate;
	private int yCoordinate;
	private int cellValue;
	private int membOfBlock;
	private int testIndex;
	private boolean isTestValue;
	ArrayList<Integer> possibleValues;
	
	public Cell (int x, int y, int value) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.cellValue = value;
		this.membOfBlock = setBlockNumber();
		this.isTestValue = false;
		this.possibleValues = new ArrayList<Integer>();
	}//constructor
	
	
	
	//Used to get the number of the 3 by 3 block that this cell is a part of. It begins by
	//checking which of the three columns of blocks the cell lies within and then checks which
	//row of 3 blocks to figure out which block the cell is a part of.
	public int setBlockNumber () {
		int blockNumber = 0;
		
		if (this.xCoordinate >= 0 && this.xCoordinate <= 2) {
			if (this.yCoordinate >= 0 && this.yCoordinate <= 2)
				blockNumber = 1;
			else if (this.yCoordinate >= 3 && this.yCoordinate <= 5)
				blockNumber = 2;
			else if (this.yCoordinate >= 6 && this.yCoordinate <= 8)
				blockNumber = 3;
		} else if (this.xCoordinate >= 3 && this.xCoordinate <= 5) {
			if (this.yCoordinate >= 0 && this.yCoordinate <= 2)
				blockNumber = 4;
			else if (this.yCoordinate >= 3 && this.yCoordinate <= 5)
				blockNumber = 5;
			else if (this.yCoordinate >= 6 && this.yCoordinate <= 8)
				blockNumber = 6;
		} else if (this.xCoordinate >= 6 && this.xCoordinate <= 8) {
			if (this.yCoordinate >= 0 && this.yCoordinate <= 2)
				blockNumber = 7;
			else if (this.yCoordinate >= 3 && this.yCoordinate <= 5)
				blockNumber = 8;
			else if (this.yCoordinate >= 6 && this.yCoordinate <= 8)
				blockNumber = 9;
		}
		
		return blockNumber;
			
	}

	public void setCellValue (int value) {
		this.cellValue = value;
	}

	public void setTestMarker (boolean value) {
		this.isTestValue = value;
	}
	
	public void setTestIndex (int value) {
		this.testIndex = value;
	}
	
	public boolean getTestValue () {
		return this.isTestValue;
	}
	
	public int getTestIndex () {
		return this.testIndex;
	}
	
	//returns the integer value stored within the cell
	public int getCellValue () {
		return this.cellValue;
	}
	
	//returns the 3 by 3 block the this cell belongs to
	public int getBlockNumber () {
		return this.membOfBlock;
	}

	//returns the size of the possibleValues arrayList for this cell
	public int getPossibleValuesSize () {
		return this.possibleValues.size();
	}
	
}
