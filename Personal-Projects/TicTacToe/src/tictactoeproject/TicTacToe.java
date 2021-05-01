
package tictactoeproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * A simple tic-tac-toe game
 * The program prints a 3 x 3 board on the screen
 * User(s) are prompted to select a numbered space to mark with either X or O
 * If three contiguous spaces are marked with the same symbol in a row, column, or diagonally, player wins
 * @author Daniel Pereira
 */
public class TicTacToe {

	//A 2-D array which creates the 3 x 3 game board
	public static char[][] board = new char[3][3];

	/**
	 * This method prints the positions of the symbols on the board
	 */
	public static void printPosition(){
		int position = 1;
		System.out.println("Please select a space to mark on the board");
		System.out.println("The spaces marked with X or O are already taken");
		for (int i= 0; i<board.length; i++){
			for (int j=0; j<board.length; j++){
				if (board[i][j] == 'X' || board[i][j] == 'O')
					System.out.print( " " + board[i][j]);
				else
					System.out.print( " " + position);                    
				position++;
			}
			System.out.println();
		}  
	}
	
	/**
	 * This method returns a T/F value of whether or not the space on the board is free
	 * @param position is the numbered space on the board
	 * @return whether or not the space on the board is free
	 */
	public static boolean freeSpaces(int position){
		switch (position){
		case 1: return board[0][0]!=' ';
		case 2: return board[0][1]!=' ';
		case 3: return board[0][2]!=' ';
		case 4: return board[1][0]!=' ';
		case 5: return board[1][1]!=' ';
		case 6: return board[1][2]!=' ';
		case 7: return board[2][0]!=' ';
		case 8: return board[2][1]!=' ';
		case 9: return board[2][2]!=' ';
		default: return false;
		}
	}

	/**
	 * This method records a move entered by the user
	 * @param symbol represent either X or 0 
	 * @throws IOException if there are any input or output errors
	 */
	public static void recordMove(char symbol) throws IOException {
		boolean turnFinished = false;
		String playerMove;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
			int position;
			do{
				printPosition();
				//User is prompted to type the number of a space they'd like to mark
				System.out.println("Type the number of the space you'd like to mark: ");
				//User's input is recorded
				playerMove = br.readLine();
				//User's input is resolved to an integer value
				position = Integer.parseInt(playerMove);
				//User's space selection is recorded on the board if the space is free
				if ( freeSpaces(position)){
					switch (position){
					case 1: board[0][0] = symbol;
					break;
					case 2: board[0][1] = symbol;
					break;
					case 3: board[0][2] = symbol;
					break;
					case 4: board[1][0] = symbol;
					break;
					case 5: board[1][1] = symbol;
					break;         
					case 6: board[1][2] = symbol;
					break;
					case 7: board[2][0] = symbol;
					break;
					case 8: board[2][1] = symbol;
					break;
					case 9: board[2][2] = symbol;
					break;
					}
					turnFinished = true;
				}
				else
					//If user's input is invalid, error message is given
					System.out.println("Invalid entry, please select a valid space"); 
			} 
			while (!turnFinished);
		
		
	}

	/**
	 * This method prints the game board with all updated changes and player markings
	 */
	public static void printCurrentBoard(){
		System.out.println("The current board: ");
		for (char[] board1 : board) {
			for (int j = 0; j<board.length; j++) {
				System.out.print("    " + board1[j]);
			}
			System.out.println();
		}

	}

	/**
	 * This method returns true if the user wins with 3 contiguous symbols by row
	 * @param symbol refers to either X or O
	 * @return true if the player has 3 contiguous symbols in a row
	 */
	public static boolean winByRow(char symbol){

		for (char[] board1 : board) {
			if (board1[0] == symbol && board1[1] == symbol && board1[2] == symbol) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns true if the user wins with 3 contiguous symbols by column
	 * @param symbol refers to either X or O
	 * @return true if the player has 3 contiguous symbols in a column
	 */
	public static boolean winByColumn(char symbol){

		for (int i=0; i<board.length; i++){
			if (board[0][i] == symbol && board[1][i]==symbol && board[2][i] == symbol)
				return true;
		}
		return false;
	}  

	/**
	 * This method returns true if the user wins with 3 contiguous symbols diagonally
	 * @param symbol refers to either X or O
	 * @return true if the player has 3 contiguous symbols diagonally
	 */
	public static boolean winDiagonally(char symbol){    
		//finds a winner from left to right and right to left
		if (board[0][0] == symbol && board[1][1]==symbol && board[2][2] == symbol)
			return true;
		if (board[0][2] == symbol && board[1][1]==symbol && board[2][0] == symbol)
			return true; 

		return false;
	} 

	/**
	 * If a winning row, column, or diagonal line is found, method returns true
	 * @param symbol refers to either X or O
	 * @return true if a winner is found
	 */
	public static boolean winnerDeclared(char symbol){
		if ( winByRow(symbol) )
			return true;
		if ( winByColumn(symbol))
			return true;
		if ( winDiagonally(symbol))
			return true;
		return false;    
	}

	/**
	 * This method returns true if there are any free spaces left on the board
	 * @return true if there are any spaces left
	 */
	public static boolean freeSpaceLeft(){
		for (char[] board1 : board) {
			for (int j = 0; j<board.length; j++) {
				if (board1[j] == '-') { 
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method prints the game board onto the screen
	 */
	public static void initializeBoard(){
		for(int i=0;i<board.length; i++)
			for(int j=0;j<board.length; j++)
				board[i][j] = '-';
	}

	/**
	 * The main method used to run the program
	 * @param args is the name of the String array
	 * @throws IOException if there are any input or output errors
	 */
	public static void main(String[] args) throws IOException {
		
		char currentPlayer = 'X';
		boolean gameEnd = false;
		initializeBoard();
		do{
			recordMove(currentPlayer);
			printCurrentBoard();
			if ( winnerDeclared(currentPlayer)){
				//Winner found, and declared
				System.out.println("Player " + currentPlayer + " has won the game.");
				gameEnd = true;
			}
			else {
				if (!freeSpaceLeft()){
					//No free spaces left and no clear winner, the game is tied
					System.out.println("Players have tied");
					gameEnd = true;
				}
				else
					if (currentPlayer == 'X')
						currentPlayer = 'O';
					else
						currentPlayer = 'X';
			}
		} while (!gameEnd);

	}

}