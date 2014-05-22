package homework1Sudoku;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;


///**
// * SudokuSolver.java
// * 
// * @author Miles
// * @version 5/14/14
// */
public class SudokuSolver {

	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		//BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\Miles\\Desktop\\sudokuInput.txt"));
		
		//Create the sudoku puzzle skeleton
		Cell board[][] = new Cell[9][9];
		
		int numberOfPuzzles = input.nextInt();
		
		//System.out.println(numberOfPuzzles); 
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				
				//if (input.nextLine() != "\n" && input.next() != " ") {
				int test = input.nextInt();
				if (test > -1 || input.nextInt() < 10){	
						board[x][y] = new Cell (x, y, test);
						
				}//end check for empty line
				
			}//end row loop
			
			System.out.print("\n");
			
		}//end column loop
		
		//displayBoard(board);
		
		
	}
	
	static void solvePuzzle (int[][] board) {
		
	}
	
	static void displayBoard (int[][] board) {
		System.out.println("This is the board: \n");
				
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
						
						System.out.print(board[i][j]);
					
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
