package homework1Sudoku;

import java.io.BufferedReader;
import java.util.Scanner;
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
		
		int numOfPuzzles = input.nextInt();
		
		//System.out.println(numOfPuzzles); 
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				//System.out.println("x: " + x + " y: " + y);
				//if (input.nextLine() != "\n" && input.next() != " ") {
				int test = input.nextInt();
				//if (test > -1 && test < 10){	
						board[x][y] = new Cell (x, y, test);
						fillBlocks (x, y, board, blocks);
						
				//}//end check for empty line
				
			}//end row loop
			
			System.out.println();
			
		}//end column loop
		
		displayBlocks(blocks);
		//displayBoard(board);
		
	}
	
	static void solvePuzzle (Cell[][] board, Cell[][] blocks) {
		
	}
	
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
