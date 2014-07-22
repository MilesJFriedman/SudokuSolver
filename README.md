This program is designed to solve Sudoku puzzles.

Input: The first line of the input should be the amount of puzzles you will be entering
that need to be solved. The next line a space, and the next 9 lines will contain 9 
integers from 0 to 9 (each followed by a space) which represent the n'th row of the 
grid as it is currently composed. The 0's are used to represent cells in the Sudoku
row that are unfilled while the other numbers represent already filled cells.

Output: The output will present one of two outputs. The first possible output will be 
the number of the test case followed by an empty line and then the solved puzzle on the
next 9 lines. The second possible output will be that no solution is possible, which
will be printed if the program could not find a valid solution to the puzzle.

Sample Input:
2

0 6 0 1 0 4 0 5 0
0 0 8 3 0 5 6 0 0
2 0 0 0 0 0 0 0 1
8 0 0 4 0 7 0 0 6
0 0 6 0 0 0 3 0 0
7 0 0 9 0 1 0 0 4
5 0 0 0 0 0 0 0 2
0 0 7 2 0 6 9 0 0
0 4 0 5 0 8 0 7 0

0 6 0 1 0 4 0 5 0
0 0 8 3 0 5 6 0 0
2 0 0 0 0 0 0 0 1
8 0 0 4 0 7 0 0 6
0 0 6 0 0 0 3 0 0
7 0 0 9 0 1 0 0 4
5 0 0 0 0 0 0 0 2
0 0 7 2 0 6 9 0 0
0 4 0 5 0 8 0 7 9

Sample Output:
Test case 1:

9 6 3 1 7 4 2 5 8
1 7 8 3 2 5 6 4 9
2 5 4 6 8 9 7 3 1
8 2 1 4 3 7 5 9 6
4 9 6 8 5 2 3 1 7
7 3 5 9 6 1 8 2 4
5 8 9 7 1 3 4 6 2
3 1 7 2 4 6 9 8 5
6 4 2 5 9 8 1 7 3

Test case 2:
No solution possible.
