package homework1Sudoku;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.FileReader;

/**
 * SudokuSolver.java
 * 
 * @author Miles Friedman
 * @version 5/14/14
 */
public class SudokuSolver {

	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		//BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\Miles\\Desktop\\sudokuInput.txt"));
		
		//Create the sudoku puzzle skeleton
		Cell[][] board = new Cell[9][9];
		//Create a 2D array, each row represents a 3 by 3 block and its corresponding number.
		//each column represents the 9 cells that make up that cell block.
		Cell[][] blocks = new Cell[9][9];
		
		//Scan in the number of puzzles
		int numOfPuzzles = input.nextInt();
		
		for (int p = 0; p < numOfPuzzles; p++) {
			//Create a variable to keep track of how many empty cells (cellValue of 0) there are 
			//in each row, in each column.
			int emptyCellsR = -1, emptyCellsC = -1;
			//Records the current best or least amount of empty cells in a row, in a column.
			int bestCountR = -1, bestCountC = -1;
			//Keeps track of the row number/column number with the least amount of empty cells to fill 
			int bestRow = -1, bestColumn = -1;
			
			//System.out.println(numOfPuzzles); 
			
			fillArrays (input, board, blocks);
			
			
			AtomicInteger ecR = new AtomicInteger(emptyCellsR);
			AtomicInteger ecC = new AtomicInteger(emptyCellsC);
			AtomicInteger bcR = new AtomicInteger(bestCountR);
			AtomicInteger bcC = new AtomicInteger(bestCountC);
			AtomicInteger bR = new AtomicInteger(bestRow);
			AtomicInteger bC = new AtomicInteger(bestColumn);
			
			findBestStartingLine (board, ecR, ecC, bcR, bcC, bR, bC);
			
			emptyCellsR = ecR.intValue();
			emptyCellsC = ecC.intValue();
			bestCountR = bcR.intValue();
			bestCountC = bcC.intValue();
			bestRow = bR.intValue();
			bestColumn = bC.intValue();
			
			int x = 0;
			int y = 0;
			int index = 0;
			boolean toRetreat = false;
			boolean isSolved = false;
			int emptyCellCount = -1;
			/*boolean flag = false;
			//System.out.println("bestCountR: " + bestCountR + " bestCountC: " + bestCountC);*/
			
			narrowTheSearch (board, blocks);
			
			fillDefiniteValues (board, blocks);
			
			//This function doesn't work properly :(
			isSolved = solvePuzzle (board, blocks, x, y, index, toRetreat);
					
			if (isSolved == true)
				//displayBlocks(blocks);
				displayBoard(board);
			else
				System.out.println("No solution possible");
		}
		
	}//end main
	
	
	
	static boolean solvePuzzle (Cell[][] board, Cell[][] blocks, int x, int y, int index, boolean toRetreat) {
		//create an array list to hold empty cells.
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		
		//populate the emptyCell arraylist.
		for (x = 0; x < 9; x++) {
			for (y = 0; y < 9; y++) {
				if (board[x][y].getCellValue() == 0) {
					emptyCells.add(board[x][y]);
				}
			}
		}
		
		//System.out.println("There are this many empty cells: " + emptyCells.size());
		
		//Iterates through the emptyCell array list that was populated above.
		for (int i = 0; i < emptyCells.size(); i++) {
			    //if there are no possibleValues for that empty cell, we need to BACKTRACK.
			 	if (emptyCells.get(i).possibleValues.isEmpty()) {
					//increment index to try the next possibleValue of the previous cell.
					index = emptyCells.get(i - 1).getTestIndex() + 1;
					//Set the current and previous emptyCell's cellValue back to 0 and update the possibleValue
					//list for all emptyCells.
					emptyCells.get(i).setCellValue(0);
					emptyCells.get(i).setTestMarker(false);
					emptyCells.get(i - 1).setCellValue(0);
					emptyCells.get(i - 1).setTestMarker(false);
					//Subtract two from i so that once the for loop iterates i will point
					//to the previous empty cell.
					i = i - 2;
					narrowTheSearch (board, blocks);
					//System.out.println("hit2");
					//System.out.println("i is: " + i);
					//System.out.println("emptyCells[i] possibleValue size is: " + emptyCells.get(i).possibleValues.size());
					//System.out.println("index is: " + index);
				//else if all possible values have been tried for that emptyCell, we need to BACKTRACK
				} else if (index >= emptyCells.get(i).possibleValues.size()) {
					if (i != 0) {
						//set the index equal to the previous emptyCells index + 1 to try that cells next
						//possible value.
						index = emptyCells.get(i - 1).getTestIndex() + 1;
						//Set the current and previous emptyCell's cellValue back to 0 and update the possibleValue
						//list for all emptyCells.
						emptyCells.get(i).setCellValue(0);
						emptyCells.get(i).setTestMarker(false);
						emptyCells.get(i - 1).setCellValue(0);
						emptyCells.get(i - 1).setTestMarker(false);
						//Subtract two from i so that once the for loop iterates i will point to the previous
						//empty cell.
						i = i - 2;
						narrowTheSearch (board, blocks);
					} else
						//if the program attempts to access element -1, no solution is possible.
						return false;
				//else if the current emptyCell does have possibleValues to test.
				} else if (emptyCells.get(i).possibleValues.isEmpty() == false && index < emptyCells.get(i).possibleValues.size()) {
					//if testing, set the cellValue of the current emptyCell equal to the 
					//possibleValue at it's testIndex.
					emptyCells.get(i).setTestMarker(true);
					emptyCells.get(i).setTestIndex(index);
					emptyCells.get(i).setCellValue(emptyCells.get(i).possibleValues.get(emptyCells.get(i).getTestIndex()));
					narrowTheSearch (board, blocks);
					//reset the index for the next emptyCell
					index = 0;
					//System.out.println("hit1");
					//System.out.println("i is: " + i);
					//System.out.println("emptyCells[i] possibleValue size is: " + emptyCells.get(i).possibleValues.size());
					//System.out.println("index is: " + index);
				}
				//System.out.println("i is: " + i + "\n");
				//System.out.println("emptyCells[i] possibleValue size is: " + emptyCells.get(i).possibleValues.size());
				//System.out.println("cellValue for emptyCell[i] is: " + emptyCells.get(i).getCellValue());
				//System.out.println("index is: " + index);	
			
				
		}//end emptyCell for loop
				
		return true; //if the program got to this point the puzzle should be solved	
	}//end solvePuzzle
	
	//looks at each empty cell and if that empty cell has only one value in its possibleValues
	//list, it places that value and updates each remaining empty cells possibleValues list.
	static void fillDefiniteValues (Cell[][] board, Cell[][] blocks) {
		int x = 0;
		int y = 0;
		int emptyCellCount = -1;
		boolean flag = false;
		
		
		do {
			emptyCellCount = -1;
			//loop through each cell on the board checking if the cell is empty (has a cell value: 0)
			for (x = 0; x < 9; x++) {
				for (y = 0; y < 9; y++) {
					//if the current cell is empty, increment emptyCellCount
					if (board[x][y].getCellValue() == 0)
						emptyCellCount++;
				}
			}
		
			
			//if there remains at least one empty cell
			if (emptyCellCount+1 > 0) {
				//fill in the empty blocks who have only one possibleValue that makes a
				//correct cellValue.
				flag = fillEmptyCell (board);
				//run narrowTheSearch to update all cell's possibleValues arrayLists.
				narrowTheSearch (board, blocks);
				//recursively run solvePuzzle until the puzzle is solved.
				//solvePuzzleRec (board, blocks, x, y, emptyCellCount);
			}
			
			//if there are no empty cells left on the board then the board must be solved, therefore
			//the correct puzzle should be printed.
			if (emptyCellCount+1 == 0)
				displayBoard (board);
			
			//System.out.println("board (2,3) has this many possible values: " + board[2][3].possibleValues.size());
			//System.out.println("board (2,3) element 0 of possibleValues is: " + board[2][3].possibleValues.get(0));
			//System.out.println("emptyCellCount is: " + emptyCellCount);
			//flag = solvePuzzle (board, blocks, x, y, emptyCellCount);
			//System.out.println(flag);
		
		} while (emptyCellCount+1 != 0 && flag == true);
	}
	
	//Sets an emptyCell's cellValue equal to the starting index of it's possibleValues list if
	//its possibleValues list has only one element. (Extension of the fillDefiniteValues method)
	static boolean fillEmptyCell (Cell[][] board) {
		boolean flag = false;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				//System.out.println(board[x][y].getCellValue());
				if (board[x][y].getCellValue() == 0 && board[x][y].possibleValues.size() == 1) {
					board[x][y].setCellValue(board[x][y].possibleValues.get(0));
					//System.out.println(board[x][y].getCellValue());
					flag = true;
				}
			}
		}
		return flag;
	}

	//Loops through each cell of the bestRow/Column and fills the possibleValues arrayList for
	//each empty cell in that bestRow/Column.
	static void narrowTheSearch (Cell[][] board, Cell[][] blocks) {
		
		NumberTracker has1 = new NumberTracker(false);
		NumberTracker has2 = new NumberTracker(false);
		NumberTracker has3 = new NumberTracker(false);
		NumberTracker has4 = new NumberTracker(false);
		NumberTracker has5 = new NumberTracker(false);
		NumberTracker has6 = new NumberTracker(false);
		NumberTracker has7 = new NumberTracker(false);
		NumberTracker has8 = new NumberTracker(false);
		NumberTracker has9 = new NumberTracker(false);
		int i,j, c;
		
		//make sure all possibleValue lists for every empty cell is clear so that this method
		//may be reused to update the possibleValue lists for each cell.
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (board[i][j].getCellValue() == 0 && board[i][j].getTestValue() != true)
					board[i][j].possibleValues.clear();
			}
		}
		
		//Determine if whether to start at the best row, or the best column
		//If the best column is the best starting point, check to see what values the empty
		//spaces of each column can contain by checking the block/row for the empty cell.
		//if (bestCountC <= bestCountR) {
		
		//keeps track of what column the program is currently looking at
		for (c = 0; c < 9; c++) {
			for (i = 0; i < 9; i++) {
				//check the column for contained values
				switch (board[i][c].getCellValue()) {
					case 1: has1.setContains(true);
							break;
					case 2: has2.setContains(true);
							break;
					case 3: has3.setContains(true);
							break;
					case 4: has4.setContains(true);
							break;
					case 5: has5.setContains(true);
							break;
					case 6: has6.setContains(true);
							break;
					case 7: has7.setContains(true);
							break;
					case 8: has8.setContains(true);
							break;
					case 9: has9.setContains(true);
							break;
				}//end original switch
			}//end i for loop
			
			//System.out.println("has1: " + has1.isContained() + " has2: " + has2.isContained() + " has3: " + has3.isContained() + " has4: " + has4.isContained() + " has5: " + has5.isContained() + " has6: " + has6.isContained() + " has7: " + has7.isContained() + " has8: " + has8.isContained() + " has9: " + has9.isContained());
			
			//fill each empty cells possible values array with the newly gained boolean values.
			for (i = 0; i < 9; i++) {
				if (board[i][c].getCellValue() == 0) {
					for (j = 0; j < 9; j++) {
						//check the row for contained values, if a value hasn't already been 
						//found in the column search, mark it as changed.
						switch (board[i][j].getCellValue()) {
							case 1: has1.markChanged();
									has1.setContains(true);
									break;
							case 2: has2.markChanged();
									has2.setContains(true);
									break;
							case 3: has3.markChanged();
									has3.setContains(true);
									break;
							case 4: has4.markChanged();
									has4.setContains(true);
									break;
							case 5: has5.markChanged();
									has5.setContains(true);
									break;
							case 6: has6.markChanged();
									has6.setContains(true);
									break;
							case 7: has7.markChanged();
									has7.setContains(true);
									break;
							case 8: has8.markChanged();
									has8.setContains(true);
									break;
							case 9: has9.markChanged();
									has9.setContains(true);
									break;				
						}//end row switch
						
						//check the block for contained values, if a value hasn't already been
						//found in the column, mark it as a changed. (getBlockNumber-1 because 
						//blocks start at the 1 while the actual index starts at 0).
						switch (blocks[board[i][c].getBlockNumber()-1][j].getCellValue()) {
							case 1: has1.markChanged();
									has1.setContains(true);
									break;
							case 2: has2.markChanged();
									has2.setContains(true);
									break;
							case 3: has3.markChanged();
									has3.setContains(true);
									break;
							case 4: has4.markChanged();
									has4.setContains(true);
									break;
							case 5: has5.markChanged();
									has5.setContains(true);
									break;
							case 6: has6.markChanged();
									has6.setContains(true);
									break;
							case 7: has7.markChanged();
									has7.setContains(true);
									break;
							case 8: has8.markChanged();
									has8.setContains(true);
									break;
							case 9: has9.markChanged();
									has9.setContains(true);
									break;				
						}//end block switch
						
					}//end j for loop
		
					//fill the possibleValues array list for the empty cell adding needed values
					//so long as they are not already contained in the corresponding numberTracker or
					//possibleValues array.
					if (has1.isContained() == false && board[i][c].possibleValues.indexOf(1) == -1)
						board[i][c].possibleValues.add(1);
					if (has2.isContained() == false && board[i][c].possibleValues.indexOf(2) == -1)
						board[i][c].possibleValues.add(2);
					if (has3.isContained() == false && board[i][c].possibleValues.indexOf(3) == -1)
						board[i][c].possibleValues.add(3);
					if (has4.isContained() == false && board[i][c].possibleValues.indexOf(4) == -1)
						board[i][c].possibleValues.add(4);
					if (has5.isContained() == false && board[i][c].possibleValues.indexOf(5) == -1)
						board[i][c].possibleValues.add(5);
					if (has6.isContained() == false && board[i][c].possibleValues.indexOf(6) == -1)
						board[i][c].possibleValues.add(6);
					if (has7.isContained() == false && board[i][c].possibleValues.indexOf(7) == -1)
						board[i][c].possibleValues.add(7);
					if (has8.isContained() == false && board[i][c].possibleValues.indexOf(8) == -1)
						board[i][c].possibleValues.add(8);
					if (has9.isContained() == false && board[i][c].possibleValues.indexOf(9) == -1)
						board[i][c].possibleValues.add(9);
					
					//reset any boolean numberTracking values that weren't found in the column
					//for so that the next empty cell tested in the column doesn't carry over
					//the numberTracking values from a previous empty cells row/block.
					has1.testAndReset();
					has2.testAndReset();
					has3.testAndReset();
					has4.testAndReset();
					has5.testAndReset();
					has6.testAndReset();
					has7.testAndReset();
					has8.testAndReset();
					has9.testAndReset();
					
				}//end if empty cell is found
				
				/*System.out.printf("Cell (%d,%d)'s possible cell values are: ", i, c);
				for (Integer value: board[i][c].possibleValues)
					System.out.print(value + ", ");
				System.out.println();*/
				
			}//end for i loop
			
			//After each column is iterated through, reset all boolean NumberTracker "contains"
			//values and their corresponding "changed" values. 
			has1.setContains(false);
			has1.resetChanged();
			has2.setContains(false);
			has2.resetChanged();
			has3.setContains(false);
			has3.resetChanged();
			has4.setContains(false);
			has4.resetChanged();
			has5.setContains(false);
			has5.resetChanged();
			has6.setContains(false);
			has6.resetChanged();
			has7.setContains(false);
			has7.resetChanged();
			has8.setContains(false);
			has8.resetChanged();
			has9.setContains(false);
			has9.resetChanged();
			
			System.out.println("\n");
			
		}//end c for loop
	
	}//end narrowTheSearch
	
	//Finds the which column or row has the least amount of empty cells to fill and should be
	//the first row or column started at.
	static void findBestStartingLine (Cell[][] board, AtomicInteger ecR, AtomicInteger ecC, AtomicInteger bcR, AtomicInteger bcC, AtomicInteger bR, AtomicInteger bC) {
		int x, y;
		for (x = 0; x < 9; x++) {
			for (y = 0; y < 9; y++) {
				
				
				//(Invert the x and y variables to loop through each cell in the column/row) 
				//Increment the emptyCell variable for the column or row as empty cells are found.
				if (board[x][y].getCellValue() == 0)
					ecR.getAndIncrement();
						
				//System.out.println("\n" + board[x][y].getCellValue());
				if (board[y][x].getCellValue() == 0)
					ecC.getAndIncrement();
				
				
			}
			
			//If checking the first row OR if the amount of empty cells in the current row is less 
			//than the amount of empty cells in the current best row, set a new bestCount and 
			//update the bestRow.
			if (bcR.intValue() == -1 || (ecR.intValue() != -1 && ecR.intValue() < bcR.intValue())) {
				bcR.set(ecR.get());
				bR.set(x);
			}
			
			//reset the emptyCells counter for the next row.
			ecR.set(-1);
			
			//Similar condition as above only x and y are inverted (x now refers to column #)
			if (bcC.intValue() == -1 || (ecC.intValue() != -1 && ecC.intValue() < bcC.intValue())) {
				bcC.set(ecC.get());
				bC.set(x);
			}
			
			//reset the emptyColumns counter for the next column.
			ecC.set(-1);
			
		}
		
		//System.out.println("bestCountR: " + bestCountR + " bestCountC: " + bestCountC);
		//System.out.println("Best Column: " + bestColumn + ", Best Count Column: " + bestCountC);
	}
	
	//initially fills the board and blocks arrays based on input from the user.
	static void fillArrays (Scanner input, Cell[][] board, Cell[][] blocks) {
		int x, y;
		for (x = 0; x < 9; x++) {
			for (y = 0; y < 9; y++) {
				
				//if (input.nextLine() != "\n" && input.next() != " ") {
				int test = input.nextInt();
				//if (test > -1 && test < 10){	
				//Places cells onto the board
				board[x][y] = new Cell (x, y, test);
				//Fills the blocks array based on info gathered from each cell
				fillBlocks (x, y, board, blocks);
				//}//end check for empty line
				
			}//end row loop
			
		}//end column loop
	}
	
	//fills the blocks array. This is called inside of the fillArrays method.
	static void fillBlocks (int x, int y, Cell[][] board, Cell[][] blocks) {
		//Check the current cell's block number
		int blockNum = board[x][y].getBlockNumber();
		//System.out.print(board[x][y].getCellValue() + " ");
		//System.out.print(blockNum + " ");

		//Use the block number of the current cell to determine the row index for the block
		//array, then loop through each index of the
		for (int i = 0; i < 9; i++) {
			
			//If there is no cell already stored in the i'th index of a certain cell block group,
			//store the current cell in that cell block, then break out (no need to find
			//the next open block to store in, as it has already been found).
			if (blocks[blockNum - 1][i] == null) {
				blocks[blockNum - 1][i] = board[x][y];
				break;
				//System.out.print(blocks[x][y].getCellValue() + " ");
			}	
		}
		
			
	}
	
	static void displayBlocks (Cell[][] blocks) {
		System.out.println("This is the blocks board: \n");
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
					
					System.out.print(blocks[i][j].getCellValue() + " ");
				
			}//end row loop
			
			System.out.println();
			
		}//end column loop
	}
	
	static void displayBoard (Cell[][] board) {
		System.out.println("This is the board: \n");
				
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
						
						System.out.print(board[i][j].getCellValue() + " ");
						//System.out.print(board[i][j].getBlockNumber() + " ");
						
				}//end row loop
				
				System.out.print("\n");
				
			}//end column loop
			
	}
	
	static void fillBoardFromFile (BufferedReader input, char[][] board) throws Exception{
		
		int x = 0;
		int y = 0;
		
		//If the line read is not an empty line,
		if (input.readLine() != "\n") {
			
			//scan each consecutive character into the sudoku puzzle skeleton
			for (y = 0; y < 9; y++) {
				
				for (x = 0; x < 9; x++) {
					
					//create a flag variable
					char test = (char)input.read();
					
					//if the test char scanned in has an ASCII value that is a valid character
					//scan test into the proper board spot
					if (test > 47 && test < 58) { //The ASCII values 48 - 57 are equivalent to the numbers 0-9 respectively.
						
						board[x][y] = test;
						//System.out.print(board[x][y]);
						
					} else
						
						//decrement j so it is as if this iteration has never happened and the
						//delimiter points to the next thing to be scanned in.
						x--;	
					
				}//end x axis loop
				
			//System.out.print("\n");
			
			}//end y axis loop
			
		}//end empty line if
		
	}//end fillBoard function

}




/*//Scan in the first line of the input file
String firstLine = input.readLine();
//Create the variable to store the number of puzzles to be solved
int numberOfPuzzles = Integer.parseInt(firstLine);

//System.out.println(numberOfPuzzles + "\n\n");

//Create looping variables
int i = 0;
int j = 0;

//For each puzzle,
//for (i = 1; i <= numberOfPuzzles; i++) {
	
	//iterate through the next ten lines of the file which is the empty line, followed by the i'th puzzle.
	//for (j = 0; j < 10; j++) {
	
	//fillBoardFromFile (input, board);
	
	
			
	//}//end single puzzle loop
	
//}//end number of puzzles loop*/
